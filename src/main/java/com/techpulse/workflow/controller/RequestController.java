package com.techpulse.workflow.controller;

import com.techpulse.workflow.entity.Request;
import com.techpulse.workflow.entity.User;
import com.techpulse.workflow.repository.UserRepository;
import com.techpulse.workflow.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/requests")
public class RequestController {

    @Autowired private RequestService requestService;
    @Autowired private UserRepository userRepo;

    private Long getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getId();
    }

    // --- 1. Create Request ---
    @PostMapping
    public Request createRequest(@RequestBody Map<String, String> payload) {
        String type = payload.get("type");
        return requestService.createRequest(getCurrentUserId(), type);
    }

    // --- 2. Approve Request ---
    @PostMapping("/{id}/approve")
    public String approveRequest(@PathVariable Long id) {
        requestService.approveRequest(id, getCurrentUserId());
        return "Request " + id + " approved successfully!";
    }

    // --- 3. GET ALL Requests (This fixes the 405 Error!) ---
    @GetMapping
    public List<Request> getAllRequests() {
        return requestService.getAllRequests();
    }

    // --- 4. Get Single Request ---
    @GetMapping("/{id}")
    public Request getRequest(@PathVariable Long id) {
        return requestService.getRequest(id);
    }
}