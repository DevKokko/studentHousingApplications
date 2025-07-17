# Student Management System

A web-based student housing management system built with Spring MVC, Spring Security, and Hibernate.

## ⚠️ Legacy Project Notice

This project was developed as a learning exercise and demonstrates early work with Spring framework technologies. While functional, it may not reflect current best practices in all areas. See the [Code Quality Assessment](Code_Quality_Assessment.md) for detailed analysis and improvement suggestions.

## 🎯 Project Overview

This system manages student applications for university housing/dormitory accommodations. It includes:

- **Student Registration & Authentication**
- **Housing Application Submission**
- **Application Review & Approval Workflow**
- **Role-based Access Control**
- **Department Management**
- **REST API for Mobile Integration**

## 🏗️ Architecture

### Technology Stack
- **Backend**: Spring MVC 5.0.4, Spring Security 5.0.3
- **Database**: MySQL with Hibernate 5.2.12
- **Frontend**: JSP pages with Bootstrap 4
- **Build Tool**: Maven
- **Security**: BCrypt password encryption

### System Architecture
```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Controllers   │ -> │    Services     │ -> │      DAOs       │
│                 │    │                 │    │                 │
│ - Student       │    │ - Student       │    │ - Student       │
│ - Application   │    │ - Application   │    │ - Application   │
│ - User          │    │ - User          │    │ - User          │
│ - API           │    │ - Department    │    │ - Department    │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

## 🚀 Getting Started

### Prerequisites
- Java 8+
- MySQL 5.7+
- Maven 3.6+
- Apache Tomcat 9+

### Database Setup
1. Create MySQL database:
```sql
CREATE DATABASE student_management;
```

2. Configure database connection in `src/main/resources/application.properties`:
```properties
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/student_management
jdbc.user=your_username
jdbc.password=your_password
```

3. Create required tables (SQL scripts should be provided separately)

### Running the Application
1. Clone the repository
2. Configure database connection
3. Build the project:
```bash
mvn clean compile
```
4. Deploy to Tomcat server
5. Access the application at `http://localhost:8080/spring-crm-with-security`

## 👥 User Roles & Permissions

### Student Role
- Register and login
- Submit housing applications
- View application status
- Update personal information

### Department Staff (EMPLOYEE)
- View applications from their department
- Review and approve/reject applications
- Manage department students
- Configure application limits and periods

### System Administrator (ADMIN)
- Full system access
- User management
- Cross-department operations
- System configuration

## 📋 Key Features

### Application Management
- **Scoring System**: Applications ranked by financial need criteria
- **Workflow**: Pending → Approved/Rejected status
- **Time-based Controls**: Configurable application periods
- **Department Filtering**: Department-specific application processing

### Security Features
- **Authentication**: Database-based with BCrypt encryption
- **Authorization**: Role-based access control
- **Session Management**: Spring Security integration
- **CSRF Protection**: Configured (currently disabled)

### API Endpoints
- `POST /api/login` - Student authentication
- `POST /api/submitApplication` - Application submission
- `POST /api/isInSubmissionPeriod` - Check application period
- `GET /api/generateStudents` - Bulk student generation (disabled)

## 🔧 Configuration

### Application Limits
Configure maximum applications per department per year via the admin interface.

### Date Ranges
Set application submission periods through the date range management system.

### Scoring Criteria
Applications are scored based on:
- Family income level
- Number of studying siblings
- Student location (out of city bonus)
- Previous housing history
- Special circumstances (unemployment)

## 📊 Known Issues & Limitations

### Code Quality Issues
- Generic exception handling without proper logging
- Some hardcoded values in scoring logic
- Raw types usage in collections
- Missing comprehensive unit tests

### Security Considerations
- Input validation could be improved
- CSRF protection currently disabled
- Some potential SQL injection risks

### Performance Issues
- N+1 query problems in some operations
- Inefficient database filtering
- Missing proper indexing recommendations

## 🛠️ Future Improvements

### Immediate Priorities
1. Implement proper logging framework (SLF4J)
2. Add comprehensive input validation
3. Write unit and integration tests
4. Fix type safety issues

### Long-term Goals
1. Migrate to Spring Boot
2. Implement REST API with proper DTOs
3. Add caching layer
4. Implement proper monitoring and metrics

## 📚 Learning Outcomes

This project demonstrates understanding of:
- Spring MVC architecture and configuration
- Spring Security implementation
- Hibernate ORM and database operations
- JSP and web development
- Maven build management
- Role-based security systems

## 🤝 Contributing

This is a legacy/learning project. For educational purposes, feel free to:
1. Fork the repository
2. Create feature branches
3. Submit pull requests with improvements
4. Report issues or suggestions

## 📄 License

This project is for educational purposes. Please respect any institutional policies if adapting for actual use.

## 🔗 Related Documentation

- [Business Use Cases (BUCs)](BUCs_Summary.md)
- [Code Quality Assessment](Code_Quality_Assessment.md)
- [API Documentation](docs/api.md) *(to be created)*

---

**Note**: This project serves as a portfolio piece demonstrating progression in software development skills. While functional, it represents early-stage learning and should be viewed in that context.