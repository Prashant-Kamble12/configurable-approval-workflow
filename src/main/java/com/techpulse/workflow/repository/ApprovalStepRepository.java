package com.techpulse.workflow.repository;

import com.techpulse.workflow.entity.ApprovalStep;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ApprovalStepRepository extends JpaRepository<ApprovalStep, Long> {
    
    Optional<ApprovalStep> findByRequestTypeAndStepOrder(String requestType, Integer stepOrder);
}