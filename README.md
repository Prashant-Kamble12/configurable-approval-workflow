# Configurable Approval Workflow Engine

## Objective
A backend engine to manage approval workflows (e.g., Leave, Expense) where the rules are *configurable via database* rather than hardcoded in Java.

## 🚀 Design Explanation

### 1. Database-Driven Logic (Core Principle)
Instead of using if-else blocks for approval rules, I used an entity ApprovalStep.
- *Table:* APPROVAL_STEPS
- *Logic:* The system queries this table to find "Who is allowed to approve Step X of Workflow Y?"
- *Benefit:* New workflows can be added by inserting SQL rows without changing Java code.

### 2. Architecture
- *Controller:* REST endpoints (/requests).
- *Service:* Contains the business logic (validations, state transitions).
- *Repository:* JPA interfaces for H2 Database access.
- *Security:* Spring Security with UserDetailsService to authenticate users against the DB.

### 3. Entities
- *User:* Stores credentials and Roles (REQUESTER, MANAGER, ADMIN).
- *Request:* Tracks status (PENDING, APPROVED) and current_step.
- *ApprovalHistory:* Audit trail of who approved what and when.

---

## 🛠️ Tech Stack
- *Java 17*
- *Spring Boot 3* (Web, Data JPA, Security)
- *H2 Database* (In-memory storage)
- *Maven*

---

## 🏃 Run Instructions

1. *Clone the repository:*
   ```bash
   git clone <your-repo-url>