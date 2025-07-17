# Business Use Cases (BUCs) - Student Management System

## Project Overview
This is a **Student Housing Management System** built with Spring MVC, Spring Security, and Hibernate. The system manages student applications for housing/dormitory accommodations with role-based access control.

## Core Business Use Cases

### 1. **Student Registration & Authentication**
- **BUC-001**: Student Registration
  - Students can register with personal information (name, email, phone, department, etc.)
  - Password encryption using BCrypt
  - Account activation/enablement process
  - Username/password authentication

- **BUC-002**: User Authentication & Authorization
  - Role-based access control (Students, Department Staff, Admin)
  - Secure login with encrypted passwords
  - Session management

### 2. **Housing Application Management**
- **BUC-003**: Submit Housing Application
  - Students can submit applications for housing/dormitory
  - Application includes financial information:
    - Student income
    - Family income
    - Number of unemployed parents
    - Number of studying siblings
    - Whether student is from another city
  - Score calculation based on criteria
  - File upload support for supporting documents

- **BUC-004**: Application Processing & Approval
  - Applications have three states: Pending (0), Approved (1), Rejected (-1)
  - Scoring system to rank applications
  - Department-specific application processing
  - Track housing allocation status (`gotFreeHousing` flag)

### 3. **Administrative Functions**
- **BUC-005**: Student Management
  - View student lists (all students or by department)
  - Add/Edit/Delete student records
  - Department-based student filtering
  - Student profile management

- **BUC-006**: Application Review & Management
  - View all applications or filter by department
  - Application approval/rejection workflow
  - Sort applications by score for fair allocation
  - Track application status and housing allocation

- **BUC-007**: User Management
  - Manage system users (students, staff, admins)
  - Role assignment and permissions
  - User account activation/deactivation

### 4. **System Configuration**
- **BUC-008**: Application Limits Management
  - Set application limits per department per year
  - Control the number of housing slots available
  - Year-based configuration

- **BUC-009**: Application Period Management
  - Configure application date ranges
  - Set start and end dates for application submissions
  - Year-based application periods

- **BUC-010**: Department Management
  - Manage different academic departments
  - Department-specific user assignments
  - Department-based access control

### 5. **API & Mobile Support**
- **BUC-011**: Mobile API Integration
  - REST API for mobile application login
  - API endpoint for application submission
  - JSON-based communication for mobile apps

## Key Business Rules

### Application Scoring System
- Applications are scored based on financial need criteria
- Higher scores indicate greater need for housing assistance
- Applications are sorted by score for fair allocation

### Access Control Rules
- **Students**: Can view and submit their own applications
- **Department Staff**: Can view applications from their department only
- **Admin (Department ID = 0)**: Can view all applications and students across departments

### Application Workflow
1. Student submits application during open period
2. System calculates score based on criteria
3. Applications are reviewed by department staff
4. Approval/rejection decision is made
5. Housing allocation is tracked

### Time-based Controls
- Application periods are configurable by year
- Application limits are set per department per year
- System tracks current year for application processing

## Technical Architecture
- **Framework**: Spring MVC with Spring Security
- **Database**: MySQL with Hibernate ORM
- **Security**: BCrypt password encryption, role-based access
- **UI**: JSP pages with Bootstrap styling
- **API**: RESTful endpoints for mobile integration

## User Roles & Permissions
1. **Student**: Submit applications, view own status
2. **Department Staff**: Review department applications, manage department students
3. **System Admin**: Full system access, user management, configuration

This system effectively manages the complex process of student housing allocation with proper security, workflow management, and administrative controls.