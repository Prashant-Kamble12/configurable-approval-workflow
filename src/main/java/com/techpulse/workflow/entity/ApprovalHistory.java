package com.techpulse.workflow.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "approval_history")
public class ApprovalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "request_id") 
    private Request request;

    @Column(name = "action")         
    private String action;           

    @Column(name = "action_by")   
    private Long actionBy;          

    @Column(name = "action_at")     
    private LocalDateTime actionAt;

	public void setRequest(Request req) {
		request = req;
	}

	public void setAction(String string) {
		action = string;
	}

	public void setActionBy(Long approverId) {
		actionBy = approverId;
	}

	public void setActionAt(LocalDateTime now) {
		actionAt = now;
		
	}
}