# 🏥 Prescripto - Cloud-Ready Healthcare Management System

> **A production-grade, cloud-deployed Spring Boot application demonstrating full-stack development, DevOps practices, and enterprise-level architecture**

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17+-orange.svg)](https://www.oracle.com/java/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0+-blue.svg)](https://www.mysql.com/)
[![Docker](https://img.shields.io/badge/Docker-Containerized-blue.svg)](https://www.docker.com/)
[![Render](https://img.shields.io/badge/Deployed-Render.com-success.svg)](https://render.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

## 🚀 Project Overview

Prescripto is a **fully-deployed, cloud-ready healthcare management backend** that showcases modern software development practices, from code to production deployment. This project demonstrates expertise in **Spring Boot development**, **cloud deployment**, **DevOps workflows**, and **production-ready architecture**.

## 💼 Professional Skills Demonstrated

### 🏗️ **Backend Development Excellence**
- **Spring Boot** ecosystem mastery with secure, scalable architecture
- **RESTful API** design with proper HTTP methods and status codes
- **JWT Authentication** with role-based access control (RBAC)
- **Database modeling** with JPA/Hibernate and MySQL optimization
- **Payment integration** with Razorpay API for secure transactions

### ☁️ **DevOps & Cloud Deployment**
- **Docker containerization** for consistent development-to-production environments
- **Cloud deployment** on Render.com using both Docker and GitHub integration
- **Database hosting** on Railway with environment-based configuration
- **Application monitoring** with Spring Boot Actuator and health endpoints
- **Service availability** management with UptimeRobot monitoring

### 🔐 **Security & Production Readiness**
- **Spring Security** implementation with JWT filter chains
- **Environment-based configuration** with secure credential management
- **File upload handling** with security validations
- **CORS configuration** for cross-origin security
- **Production monitoring** with health checks and observability

## ✨ Key Features & Achievements

### 🎯 **What I Built and Deployed**
- ✅ **JWT-secured REST APIs** for Patients, Doctors, and Admins with role-based access
- ✅ **Dockerized Spring Boot application** for consistent dev-to-deploy environments
- ✅ **Cloud deployment** to Render.com using both Docker and GitHub-based methods
- ✅ **Cloud database integration** with Railway MySQL using environment variables
- ✅ **Payment processing** with Razorpay API for appointment transactions
- ✅ **File management system** for image uploads with static file serving
- ✅ **Health monitoring** endpoints (`/actuator/health`, `/v1/`) for observability
- ✅ **Production-ready configuration** with security and monitoring best practices

## 🛠️ Technology Stack & Architecture

| **Category** | **Technology** | **Purpose** |
|--------------|----------------|-------------|
| **Backend Framework** | Spring Boot 3.5.3 | RESTful API development with enterprise features |
| **Language** | Java 17+ | Modern Java with latest language features |
| **Database** | MySQL 8.0+ (Railway Cloud) | Relational database with cloud hosting |
| **Security** | Spring Security + JWT | Stateless authentication with role-based access |
| **Payment** | Razorpay API | Secure payment processing for healthcare services |
| **DevOps** | Docker + Render.com | Containerization and cloud deployment |
| **Monitoring** | Spring Actuator | Application health and metrics monitoring |
| **Architecture** | MVC + Repository Pattern | Clean, maintainable code structure |

### �️ **Enterprise Architecture Pattern**
```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Controllers   │ ──▶│    Services     │ ──▶│  Repositories   │
│  (REST Layer)   │    │ (Business Logic)│    │ (Data Access)   │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         ▼                       ▼                       ▼
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│      DTOs       │    │   Utilities     │    │   JPA Entities  │
│ (Data Transfer) │    │   (Helpers)     │    │ (Database Maps) │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

## 🌐 Deployment & DevOps Workflow

### **Production Deployment Stack**
- **🐳 Docker Hub**: Container registry for application images
- **☁️ Render.com**: Cloud platform for application hosting
- **🗄️ Railway**: Cloud MySQL database hosting
- **📊 UptimeRobot**: Service monitoring to prevent free-tier sleep
- **🔐 Environment Variables**: Secure credential management

### **Deployment Methods Mastered**
1. **Docker-based Deployment**: Built, tagged, and pushed images to Docker Hub
2. **GitHub Integration**: Direct deployment from repository with auto-builds
3. **Environment Management**: Configured production variables via Render dashboard
4. **Database Connectivity**: Connected Spring Boot to cloud MySQL via environment configs

## � Learning Outcomes & Professional Growth

### **Full-Stack Development Lifecycle Mastery**
- ✅ **Code → Container → Cloud**: Complete development-to-production pipeline
- ✅ **Production-Ready Design**: Security, monitoring, and role-based access control
- ✅ **DevOps Integration**: Docker workflows and cloud deployment strategies
- ✅ **Real-World Tools**: UptimeRobot, Railway, Actuator, and Spring Cloud concepts

### **Enterprise Development Practices**
- ✅ **Stateless Backend Architecture** with JWT authentication
- ✅ **SecurityFilterChain** implementation for fine-grained access control  
- ✅ **Clean Architecture** with Controller → Service → Repository pattern
- ✅ **Environment-based Configuration** for development, testing, and production

## 🚀 Quick Start Guide

### **Prerequisites**
- Java 17+, MySQL 8.0+, Docker (optional)
- Razorpay account for payment testing
- Cloud accounts: Render.com, Railway (for production deployment)

### **Local Development Setup**
```bash
# Clone and navigate to project
git clone https://github.com/karanhimadri/prescripto.git
cd prescripto

# Build and run application
./mvnw clean install
./mvnw spring-boot:run

# Access application
curl http://localhost:8080/v1/  # Health check
```

### **Docker Deployment**
```bash
# Build Docker image
docker build -t prescripto:latest .

# Run with volume mounting for uploads
docker run -p 8080:8080 -v $(pwd)/uploads:/app/uploads prescripto:latest

# Push to Docker Hub (production workflow)
docker tag prescripto:latest username/prescripto:latest
docker push username/prescripto:latest
```

## � API Architecture & Endpoints

### **Security Implementation**
- **🔐 JWT Authentication**: Stateless token-based security
- **🛡️ Role-Based Access**: Patient, Doctor, Admin authorization levels
- **🔒 BCrypt Encryption**: Secure password hashing
- **🌐 CORS Configuration**: Cross-origin request security

### **Core API Endpoints**

#### **Public Access** (No Authentication)
```http
GET    /v1/                    # Health check & welcome
POST   /v1/panel/login         # Admin/Doctor authentication
GET    /actuator/health        # Application monitoring endpoint
```

#### **Patient Operations** (JWT Required)
```http
POST   /patient/create-account      # User registration
POST   /patient/login               # Patient authentication  
GET    /patient/me                  # Profile retrieval
POST   /patient/book-appointment    # Appointment booking
POST   /patient/upload-patient-image # Profile image upload
```

#### **Admin Operations** (Admin JWT Required)
```http
POST   /admin/create-doctor         # Doctor onboarding with image upload
GET    /admin/dashboard             # Administrative overview
```

#### **Payment Processing** (JWT Required)
```http
POST   /api/payment/create-order    # Razorpay order creation
POST   /api/payment/verify          # Payment verification & completion
```

## � Database Design & Cloud Integration

### **Production Database Architecture**
- **🌐 Railway Cloud MySQL**: Hosted database with environment-based connectivity
- **🔄 Auto-Schema Management**: Hibernate DDL auto-updates for seamless deployments
- **📊 Entity Relationships**: JPA-mapped associations between core healthcare entities
- **🔍 Query Optimization**: MySQL 8 dialect with performance tuning

### **Core Data Models**
| **Entity** | **Purpose** | **Key Features** |
|------------|-------------|------------------|
| `Admin` | System administration | BCrypt authentication, role management |
| `Patient/PatientInfo` | User management | Split model for auth vs. profile data |
| `Doctor` | Healthcare providers | Specialization tracking, image uploads |
| `Appointment` | Service bookings | Patient-doctor relationships, payment integration |
| `PaymentInfo` | Transaction records | Razorpay integration, payment verification |

## 🔧 Production Configuration Management

### **Environment-Based Security**
```bash
# Production Environment Variables (Render.com)
DATABASE_URL=mysql://railway-cloud-connection
RAZORPAY_KEY_ID=rzp_live_production_key
RAZORPAY_KEY_SECRET=production_secret_key
JWT_SECRET=64-character-production-secret
ADMIN_EMAIL=production-admin@domain.com
```

### **Cloud Configuration Features**
- ✅ **Secure credential management** via environment variables
- ✅ **Database connection pooling** for high-availability
- ✅ **File upload security** with multipart validation
- ✅ **CORS configuration** for frontend integration
- ✅ **Actuator endpoints** for monitoring and health checks

## 📊 Project Impact & Professional Value

### **Technical Leadership Demonstrated**
- 🎯 **Full-Stack Ownership**: End-to-end development from database design to cloud deployment
- 🏗️ **Architecture Design**: Scalable, maintainable code structure following enterprise patterns  
- 🔐 **Security Implementation**: Production-grade authentication and authorization systems
- ☁️ **DevOps Integration**: Modern deployment workflows with containerization

### **Production-Ready Implementation**
- ✅ **Monitoring & Observability**: Health checks, metrics, and uptime monitoring
- ✅ **Payment Processing**: Secure financial transactions with signature verification
- ✅ **File Management**: Secure upload/download with proper validation
- ✅ **Database Optimization**: Connection pooling, query optimization, and cloud hosting

## 🎯 Resume-Ready Accomplishments

**"Developed and deployed a cloud-ready Spring Boot healthcare management system demonstrating:**
- **Backend Development**: RESTful APIs with JWT authentication and role-based access control
- **Cloud Deployment**: Dockerized application deployed to Render.com with Railway database integration  
- **Payment Integration**: Secure Razorpay payment processing with transaction verification
- **DevOps Practices**: Container workflows, environment management, and production monitoring"

## � Live Demo & Resources

### **Production Links**
- 🌐 **Live Application**: [Deployed on Render.com](https://prescripto-backend.onrender.com/v1/)
- 💾 **Docker Image**: Available on Docker Hub  
- 📊 **Monitoring**: UptimeRobot health checks configured
- 🗄️ **Database**: Railway cloud MySQL hosting

### **Test Credentials**
- **Admin Login**: `admin@gmail.com` / `admin123`
- **Payment Testing**: Razorpay test mode enabled
- **Health Check**: `/actuator/health` endpoint active

## 👨‍💻 Developer Profile

**Himadri Karan**  
*Backend Developer & Cloud Solutions Specialist*

- 📧 **Email**: [Karanhimadri1234@gmail.com](mailto:Karanhimadri1234@gmail.com)
- 💼 **LinkedIn**: [linkedin.com/in/himadrikaran](https://linkedin.com/in/himadrikaran)  
- 🌐 **Portfolio**: [Himadri.me](https://himadri.me)
- � **GitHub**: [github.com/karanhimadri](https://github.com/karanhimadri)

---

## 🌟 Why This Project Matters

This project showcases the complete software development lifecycle that employers value:
- **Problem Solving**: Built a complex healthcare domain application
- **Modern Architecture**: Used current industry-standard frameworks and patterns  
- **Production Deployment**: Handled real-world deployment challenges and solutions
- **Security Focus**: Implemented enterprise-level authentication and data protection
- **DevOps Integration**: Demonstrated modern development and deployment practices

---

⭐ **Star this repository to see more enterprise-level projects!**

📧 **Interested in collaboration?** Let's connect and build something amazing together.
