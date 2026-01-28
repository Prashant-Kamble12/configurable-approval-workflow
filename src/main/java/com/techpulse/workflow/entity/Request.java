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

    @Column(name = "type")       
    private String type;         

    @Column(name = "status")    
    private String status;      

    @Column(name = "current_step")
    private Integer currentStep; 

    @Column(name = "created_by")  
    private Long createdBy;     

    @Column(name = "created_at") 
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