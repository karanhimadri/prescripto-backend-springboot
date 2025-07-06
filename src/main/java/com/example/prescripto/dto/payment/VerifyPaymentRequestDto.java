package com.example.prescripto.dto.payment;

public class VerifyPaymentRequestDto {
    private String razorpayPaymentId;
    private String razorpayOrderId;
    private String razorpaySignature;

    public VerifyPaymentRequestDto() {}

    public String getRazorpayPaymentId() {
        return razorpayPaymentId;
    }

    public String getRazorpayOrderId() {
        return razorpayOrderId;
    }

    public String getRazorpaySignature() {
        return razorpaySignature;
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
}
