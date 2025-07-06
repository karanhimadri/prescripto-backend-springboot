package com.example.prescripto.services;

import com.example.prescripto.dto.SuccessAndErrorResDto;
import com.example.prescripto.dto.payment.VerifyPaymentRequestDto;
import com.example.prescripto.models.payment.PaymentInfo;
import com.example.prescripto.dto.payment.PaymentRequestDto;
import com.example.prescripto.repository.payment.PaymentInfoRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.Utils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

@Service
public class PaymentService {
    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);
    @Autowired PaymentInfoRepository paymentInfoRepository;

    @Value("${razorpay.key}")
    private String razorpayKey;

    @Value("${razorpay.secret}")
    private String razorpaySecret;

    public ResponseEntity<String> createOrder(PaymentRequestDto paymentRequestDto) {

        if (paymentRequestDto.getAmount() <= 0) {
            return ResponseEntity.badRequest().body("Invalid amount");
        }

        try {
            RazorpayClient razorpayClient = new RazorpayClient(razorpayKey, razorpaySecret);

            String receipt = "txn_"+System.currentTimeMillis();

            JSONObject option = new JSONObject();
            option.put("amount", paymentRequestDto.getAmount()*100); // convert ₹ to paise
            option.put("currency", "INR");
            option.put("receipt", receipt);
            option.put("payment_capture", 1);

            Order order = razorpayClient.orders.create(option);

            JSONObject orderJson = order.toJson();

            String orderId = orderJson.getString("id");
            String currency = orderJson.getString("currency");
            String receiptId = orderJson.getString("receipt");
            String status = orderJson.getString("status");
            int createdAt = orderJson.getInt("created_at");

            ZonedDateTime createdAtZoned = Instant.ofEpochSecond(createdAt)
                    .atZone(ZoneId.of("Asia/Kolkata"));


            PaymentInfo paymentInfo = new PaymentInfo(orderId, paymentRequestDto.getAmount(),currency,receiptId,status,createdAtZoned.toLocalDateTime());
            paymentInfoRepository.save(paymentInfo);

            return ResponseEntity.ok(orderJson.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error creating order");
        }
    }

    public SuccessAndErrorResDto verifyPayment(VerifyPaymentRequestDto vpRequest) {
        String orderId = vpRequest.getRazorpayOrderId();
        String paymentId = vpRequest.getRazorpayPaymentId();
        String signature = vpRequest.getRazorpaySignature();

        Optional<PaymentInfo> paymentInfo = paymentInfoRepository.findByRazorpayOrderId(orderId);
        if(paymentInfo.isEmpty()) {
            return new SuccessAndErrorResDto(false, "Payment info not found");
        }

        PaymentInfo pInfo = paymentInfo.get();

        if ("paid".equals(pInfo.getStatus())) {
            return new SuccessAndErrorResDto(true, "Payment already verified.");
        }

        try {
            String data = orderId + "|" + paymentId;
            boolean isValid = Utils.verifySignature(data, signature, razorpaySecret);

            if(isValid) {
                ZonedDateTime currentZonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));

                pInfo.setRazorpayPaymentId(paymentId);
                pInfo.setRazorpaySignature(signature);
                pInfo.setPaidAt(currentZonedDateTime.toLocalDateTime());
                pInfo.setStatus("paid");

                paymentInfoRepository.save(pInfo);

                return new SuccessAndErrorResDto(true, "Payment verified successfully.");
            } else {
                return new SuccessAndErrorResDto(false, "Payment was failed.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new SuccessAndErrorResDto(false, "Verification failed.");
        }
    }
}
