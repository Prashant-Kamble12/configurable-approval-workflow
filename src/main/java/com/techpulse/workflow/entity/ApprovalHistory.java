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
    @JoinColumn(name = "request_id") // Matches PDF: request_id[span_11](end_span)
    private Request request;

    @Column(name = "action")         // Matches PDF: action[span_12](end_span)
    private String action;           // e.g., "APPROVE", "REJECT"

    @Column(name = "action_by")      // Matches PDF: action_by[span_13](end_span)
    private Long actionBy;           // User ID

    @Column(name = "action_at")      // Matches PDF: action_at[span_14](end_span)
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