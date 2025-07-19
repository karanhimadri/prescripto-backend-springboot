package com.example.prescripto.models.payment;

import com.example.prescripto.models.junctionModel.Appointment;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class PaymentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private String razorpayPaymentId;
    private String razorpayOrderId;
    private String razorpaySignature;
    private Integer amount;
    private String currency;
    private String receipt;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime paidAt;

    @OneToOne(mappedBy = "paymentInfo")
    private Appointment appointment;

    public PaymentInfo() {}

    public PaymentInfo(String razorpayOrderId, Integer amount, String currency, String receipt, String status, LocalDateTime createdAt) {
        this.razorpayOrderId = razorpayOrderId;
        this.amount = amount;
        this.currency = currency;
        this.receipt = receipt;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public String getRazorpayPaymentId() {
        return razorpayPaymentId;
    }

    public String getRazorpayOrderId() {
        return razorpayOrderId;
    }

    public String getRazorpaySignature() {
        return razorpaySignature;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getReceipt() {
        return receipt;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getPaidAt() {
        return paidAt;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public void setRazorpayPaymentId(String razorpayPaymentId) {
        this.razorpayPaymentId = razorpayPaymentId;
    }

    public void setRazorpayOrderId(String razorpayOrderId) {
        this.razorpayOrderId = razorpayOrderId;
    }

    public void setRazorpaySignature(String razorpaySignature) {
        this.razorpaySignature = razorpaySignature;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setPaidAt(LocalDateTime paidAt) {
        this.paidAt = paidAt;
    }
}
