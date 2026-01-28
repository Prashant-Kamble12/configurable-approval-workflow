package com.techpulse.workflow.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role; // e.g., "REQUESTER", "APPROVER", "ADMIN"
	public void setUsername(String name) {
		username = name;
	}
	public void setPassword(String pass) {
		password = pass;
	}
	public void setRole(String role) {
		this.role = role;
		
	}
	public String getRole() {
		return role;
	}
	public Long getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
}