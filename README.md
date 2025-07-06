# 🏥 Prescripto -  Spring Boot Healthcare Management System

A comprehensive healthcare management platform built with **Spring Boot** that connects patients with healthcare providers, enabling seamless appointment booking, profile management, and secure payment processing.

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17+-orange.svg)](https://www.oracle.com/java/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0+-blue.svg)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

## 🌟 Features

### 👥 Multi-Role Authentication System
- **Patients**: Account creation, profile management, doctor discovery
- **Doctors**: Professional profiles with specialization management
- **Admins**: System administration and doctor onboarding
- **JWT-based Authentication** with role-based access control

### 🔐 Security & Authorization
- Spring Security integration with JWT tokens
- Role-based access control (RBAC)
- Secure password hashing
- CORS configuration for frontend integration

### 💳 Payment Integration
- **Razorpay** payment gateway integration
- Secure payment order creation and verification
- Payment status tracking and history
- Transaction security with signature verification

### 📋 Appointment Management
- Doctor-patient appointment booking system
- Appointment status tracking
- Payment integration for consultations
- Schedule management

### 🖼️ File Management
- Profile image upload for patients and doctors
- Secure file storage and retrieval
- Multiple image format support

## 🛠️ Technology Stack

| Category | Technology |
|----------|------------|
| **Backend Framework** | Spring Boot 3.5.3 |
| **Language** | Java 17+ |
| **Database** | MySQL 8.0+ |
| **ORM** | Spring Data JPA / Hibernate |
| **Security** | Spring Security + JWT |
| **Payment Gateway** | Razorpay |
| **Build Tool** | Maven |
| **Architecture** | RESTful API, MVC Pattern |

## 🏗️ Project Architecture

```
src/
├── main/java/com/example/prescripto/
│   ├── config/          # Security & JWT Configuration
│   ├── controller/      # REST API Controllers
│   ├── dto/            # Data Transfer Objects
│   ├── models/         # JPA Entity Models
│   ├── repository/     # Data Access Layer
│   ├── services/       # Business Logic Layer
│   └── utils/          # Utility Classes
└── resources/
    ├── application.properties
    ├── static/         # Static Resources
    └── templates/      # View Templates
```

## 🚀 Getting Started

### Prerequisites

- **Java 17+** installed
- **MySQL 8.0+** running
- **Maven 3.6+** installed
- **Git** for version control

### Installation & Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/prescripto.git
   cd prescripto
   ```

2. **Database Setup**
   ```sql
   CREATE DATABASE prescriptodb;
   CREATE USER 'prescripto_user'@'localhost' IDENTIFIED BY 'your_password';
   GRANT ALL PRIVILEGES ON prescriptodb.* TO 'prescripto_user'@'localhost';
   ```

3. **Configure Application Properties**
   ```properties
   # Database Configuration
   spring.datasource.url=jdbc:mysql://localhost:3306/prescriptodb
   spring.datasource.username=prescripto_user
   spring.datasource.password=your_password
   
   # Razorpay Configuration
   razorpay.key.id=your_razorpay_key_id
   razorpay.key.secret=your_razorpay_secret
   
   # JWT Configuration
   jwt.secret=your_jwt_secret_key
   jwt.expiration=86400000
   ```

4. **Access the Application**
   - Application will be running on `http://localhost:8080`
   - API documentation: `http://localhost:8080/swagger-ui.html`

## 📚 API Documentation

### Authentication Endpoints
```http
POST /api/public/login          # User login
POST /api/public/patient-register  # Patient registration
GET  /api/public/doctors        # Public doctor list
```

### Patient Endpoints
```http
GET    /api/patient/profile     # Get patient profile
PUT    /api/patient/profile     # Update patient profile
POST   /api/patient/upload-image # Upload profile image
```

### Admin Endpoints
```http
POST   /api/admin/add-doctor    # Add new doctor
GET    /api/admin/doctors       # Manage doctors
```

### Payment Endpoints
```http
POST   /api/payment/create-order    # Create payment order
POST   /api/payment/verify-payment  # Verify payment
```

## 🗄️ Database Schema

### Core Entities
- **Patient**: User credentials and profile information
- **Doctor**: Healthcare provider profiles and specializations
- **Admin**: System administrator accounts
- **Appointment**: Booking and scheduling information
- **PaymentInfo**: Payment transaction records

## 🔧 Configuration

### Environment Variables
```env
DB_HOST=localhost
DB_PORT=3306
DB_NAME=prescriptodb
DB_USER=prescripto_user
DB_PASSWORD=your_password
RAZORPAY_KEY_ID=your_key_id
RAZORPAY_SECRET=your_secret
JWT_SECRET=your_jwt_secret
```

## 📈 Performance & Security

- **JWT Token-based Authentication** for stateless security
- **Password Encryption** using BCrypt
- **SQL Injection Prevention** through JPA/Hibernate
- **CORS Configuration** for secure cross-origin requests
- **File Upload Validation** for security
- **Payment Security** with Razorpay signature verification

## 🚀 Deployment

### Docker Deployment
```dockerfile
FROM openjdk:17-jdk-slim
COPY target/prescripto-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
```

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Developer

**Your Name**
- Email: Karanhimadri1234@gmail.com
- LinkedIn: [LinkedIn Profile](https://linkedin.com/in/himadrikaran)
- Portfolio: [Himadri.me](https://himadri.me)

## 🙏 Acknowledgments

- Spring Boot community for excellent documentation
- Razorpay for secure payment integration
- MySQL for reliable database management
- JWT for stateless authentication

---

⭐ **Star this repository if you found it helpful!**

📧 **Have questions?** Feel free to reach out or create an issue.
