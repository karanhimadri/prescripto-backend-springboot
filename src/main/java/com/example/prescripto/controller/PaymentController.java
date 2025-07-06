package com.example.prescripto.controller;

import com.example.prescripto.dto.SuccessAndErrorResDto;
import com.example.prescripto.dto.payment.PaymentRequestDto;
import com.example.prescripto.dto.payment.VerifyPaymentRequestDto;
import com.example.prescripto.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired PaymentService paymentService;

    @PostMapping("/create-order")
    public ResponseEntity<String> createOrder(@RequestBody PaymentRequestDto paymentRequestDto) {
        return paymentService.createOrder(paymentRequestDto);
    }

    @PostMapping("/verify")
    public SuccessAndErrorResDto verifyPayment(@RequestBody VerifyPaymentRequestDto verifyPaymentRequestDto) {
        return paymentService.verifyPayment(verifyPaymentRequestDto);
    }
}
