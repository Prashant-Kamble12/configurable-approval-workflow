package com.techpulse.workflow.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "approval_steps")
public class ApprovalStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "request_type") // Matches PDF: request_type[span_3](end_span)
    private String requestType; 

    @Column(name = "step_order")   // Matches PDF: step_order[span_4](end_span)
    private Integer stepOrder;

    @Column(name = "role_required") // Matches PDF: role[span_5](end_span)
    private String role;

	public void setRequestType(String type) {
		requestType = type;
	}

	public void setStepOrder(int order) {
		stepOrder = order;
		
	}

	public void setRole(String role) {
		this.role = role;
		
	}

	public String getRole() {
		return role;
	} 
}