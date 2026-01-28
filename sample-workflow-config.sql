-- SAMPLE CONFIGURATION FOR WORKFLOWS
-- This file demonstrates how to configure approval rules via SQL
-- rather than hardcoding them in Java.

-- ==========================================
-- 1. LEAVE WORKFLOW (2 Steps)
-- ==========================================
-- Step 1: Manager must approve
INSERT INTO APPROVAL_STEPS (REQUEST_TYPE, STEP_ORDER, ROLE_REQUIRED) 
VALUES ('LEAVE', 1, 'ROLE_MANAGER');

-- Step 2: HR must approve
INSERT INTO APPROVAL_STEPS (REQUEST_TYPE, STEP_ORDER, ROLE_REQUIRED) 
VALUES ('LEAVE', 2, 'ROLE_HR');

-- ==========================================
-- 2. EXPENSE WORKFLOW (1 Step)
-- ==========================================
-- Step 1: Manager must approve
INSERT INTO APPROVAL_STEPS (REQUEST_TYPE, STEP_ORDER, ROLE_REQUIRED) 
VALUES ('EXPENSE', 1, 'ROLE_MANAGER');

-- ==========================================
-- 3. USERS (For Testing)
-- ==========================================
-- Password for all is 'password'
INSERT INTO USERS (USERNAME, PASSWORD, ROLE) VALUES ('alice', 'password', 'ROLE_REQUESTER');
INSERT INTO USERS (USERNAME, PASSWORD, ROLE) VALUES ('bob', 'password', 'ROLE_MANAGER');
INSERT INTO USERS (USERNAME, PASSWORD, ROLE) VALUES ('charlie', 'password', 'ROLE_HR');
INSERT INTO USERS (USERNAME, PASSWORD, ROLE) VALUES ('admin', 'password', 'ROLE_ADMIN');