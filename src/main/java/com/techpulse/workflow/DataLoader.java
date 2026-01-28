package com.techpulse.workflow;

import com.techpulse.workflow.entity.ApprovalStep;
import com.techpulse.workflow.entity.User;
import com.techpulse.workflow.repository.ApprovalStepRepository;
import com.techpulse.workflow.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepo, ApprovalStepRepository stepRepo) {
        return args -> {
            // 1. Create Users
            // Ideally passwords should be encoded, but for now we store plain text for simplicity
            userRepo.save(createUser("alice", "password", "ROLE_REQUESTER"));
            userRepo.save(createUser("bob", "password", "ROLE_MANAGER"));
            userRepo.save(createUser("charlie", "password", "ROLE_HR"));
            userRepo.save(createUser("admin", "password", "ROLE_ADMIN"));

            // 2. Define Workflow Rules (The "Configurable" Requirement)
            
            // --- LEAVE Workflow ---
            // Step 1: Manager must approve
            stepRepo.save(createStep("LEAVE", 1, "ROLE_MANAGER"));
            // Step 2: HR must approve
            stepRepo.save(createStep("LEAVE", 2, "ROLE_HR"));

            // --- EXPENSE Workflow ---
            // Step 1: Manager must approve (1-step process)
            stepRepo.save(createStep("EXPENSE", 1, "ROLE_MANAGER"));

            System.out.println("✅ Database initialized with Users and Workflow Rules!");
        };
    }

    private User createUser(String name, String pass, String role) {
        User u = new User();
        u.setUsername(name);
        u.setPassword(pass);
        u.setRole(role);
        return u;
    }

    private ApprovalStep createStep(String type, int order, String role) {
        ApprovalStep s = new ApprovalStep();
        s.setRequestType(type);
        s.setStepOrder(order);
        s.setRole(role);
        return s;
    }
}