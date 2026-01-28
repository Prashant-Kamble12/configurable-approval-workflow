package com.techpulse.workflow.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")        // Matches PDF: type[span_6](end_span)
    private String type;          // e.g., "LEAVE", "EXPENSE"

    @Column(name = "status")      // Matches PDF: status[span_7](end_span)
    private String status;        // e.g., "PENDING", "APPROVED"

    @Column(name = "current_step")
    private Integer currentStep;  // Tracks which step of the workflow we are on

    @Column(name = "created_by")  // Matches PDF: created_by[span_8](end_span)
    private Long createdBy;       // User ID

    @Column(name = "created_at")  // Matches PDF: created_at[span_9](end_span)
    private LocalDateTime createdAt;

	public void setCreatedBy(Long userId) {
		id = userId;
		
	}

	public void setType(String requestType) {
		type = requestType;
		
	}

	public void setStatus(String string) {
		status = string;
		
	}

	public void setCurrentStep(int i) {
		currentStep = i;
		
	}

	public void setCreatedAt(LocalDateTime now) {
		createdAt = now;
		
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public String getType() {
		return type;
	}

	public Integer getCurrentStep() {
		return currentStep;
	}
}