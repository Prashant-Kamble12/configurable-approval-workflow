package com.techpulse.workflow.service;

import com.techpulse.workflow.entity.*;
import com.techpulse.workflow.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RequestService {

    @Autowired private RequestRepository requestRepo;
    @Autowired private ApprovalStepRepository stepRepo;
    @Autowired private ApprovalHistoryRepository historyRepo;
    @Autowired private UserRepository userRepo;

   
    @Transactional
    public Request createRequest(Long userId, String requestType) {
       
        if (stepRepo.findByRequestTypeAndStepOrder(requestType, 1).isEmpty()) {
            throw new RuntimeException("Workflow not defined for type: " + requestType);
        }

        Request req = new Request();
        req.setCreatedBy(userId);
        req.setType(requestType);
        req.setStatus("PENDING");
        req.setCurrentStep(1);
        req.setCreatedAt(LocalDateTime.now());
        
        return requestRepo.save(req);
    }

 
    @Transactional
    public void approveRequest(Long requestId, Long approverId) {
        Request req = requestRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        User approver = userRepo.findById(approverId)
                .orElseThrow(() -> new RuntimeException("User not found"));

       
        if (req.getCreatedBy().equals(approverId)) {
            throw new RuntimeException("You cannot approve your own request!");
        }

        
        ApprovalStep currentRule = stepRepo.findByRequestTypeAndStepOrder(req.getType(), req.getCurrentStep())
                .orElseThrow(() -> new RuntimeException("Configuration error: Step not found"));

      
        boolean isAuthorized = approver.getRole().equals(currentRule.getRole()) 
                               || approver.getRole().equals("ROLE_ADMIN");

        if (!isAuthorized) {
            throw new RuntimeException("You are not authorized to approve this step.");
        }

      
        ApprovalHistory history = new ApprovalHistory();
        history.setRequest(req);
        history.setAction("APPROVE");
        history.setActionBy(approverId);
        history.setActionAt(LocalDateTime.now());
        historyRepo.save(history);

       
        boolean hasNextStep = stepRepo.findByRequestTypeAndStepOrder(req.getType(), req.getCurrentStep() + 1).isPresent();

        if (hasNextStep) {
            req.setCurrentStep(req.getCurrentStep() + 1); 
        } else {
            req.setStatus("APPROVED");
        }

        requestRepo.save(req);
    }
    
   
    public Request getRequest(Long id) {
        return requestRepo.findById(id).orElse(null);
    }
    

    public List<Request> getAllRequests() {
        return requestRepo.findAll();
    }
}