package com.example.prescripto.repository.payment;

import com.example.prescripto.models.payment.PaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentInfoRepository extends JpaRepository<PaymentInfo, Long> {

    Optional<PaymentInfo> findByRazorpayOrderId(String razorpayOrderId);
}
