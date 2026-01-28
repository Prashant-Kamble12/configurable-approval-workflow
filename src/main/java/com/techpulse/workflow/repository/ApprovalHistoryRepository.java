package com.techpulse.workflow.repository;

import com.techpulse.workflow.entity.ApprovalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovalHistoryRepository extends JpaRepository<ApprovalHistory, Long> {
}