package com.vn.mobilecity.service.impl;

import com.vn.mobilecity.domain.dto.request.CheckMomoStatusRequestDto;
import com.vn.mobilecity.domain.dto.request.MomoRequestDto;
import com.vn.mobilecity.service.MomoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MomoServiceImpl implements MomoService {

    @Value("${momo.accessKey}")
    private String accessKey;

    @Value("${momo.secretKey}")
    private String secretKey;

    @Value("${momo.redirectUrl}")
    private String redirectUrl;

    @Value("${momo.ipnUrl}")
    private String ipnUrl;

    @Value("${momo.partnerCode}")
    private String partnerCode;

    @Value("${momo.requestType}")
    private String requestType;

    @Value("${momo.api.create}")
    private String momoCreateUrl;

    @Value("${momo.api.query}")
    private String momoQueryUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    private String createSignature(String data) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
        mac.init(secretKeySpec);
        return bytesToHex(mac.doFinal(data.getBytes()));
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    @Override
    public ResponseEntity<?> createPayment(MomoRequestDto requestDto) {
        try {
            String orderId = partnerCode + System.currentTimeMillis();
            String requestId = orderId;

            String rawSignature = String.format(
                    "accessKey=%s&amount=%s&extraData=%s&ipnUrl=%s&orderId=%s&orderInfo=%s&partnerCode=%s&redirectUrl=%s&requestId=%s&requestType=%s",
                    accessKey, requestDto.getAmount(), requestDto.getExtraData(), ipnUrl, orderId, requestDto.getOrderInfo(),
                    partnerCode, redirectUrl, requestId, requestType
            );

            String signature = createSignature(rawSignature);

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("partnerCode", partnerCode);
            requestBody.put("partnerName", "Test");
            requestBody.put("storeId", "MomoTestStore");
            requestBody.put("requestId", requestId);
            requestBody.put("amount", requestDto.getAmount());
            requestBody.put("orderId", orderId);
            requestBody.put("orderInfo", requestDto.getOrderInfo());
            requestBody.put("redirectUrl", redirectUrl);
            requestBody.put("ipnUrl", ipnUrl);
            requestBody.put("lang", "vi");
            requestBody.put("requestType", requestType);
            requestBody.put("autoCapture", true);
            requestBody.put("extraData", requestDto.getExtraData());
            requestBody.put("orderGroupId", requestDto.getOrderGroupId());
            requestBody.put("signature", signature);

            ResponseEntity<?> response = restTemplate.postForEntity(
                    momoCreateUrl, requestBody, Object.class);

            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Server error", "error", e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> checkStatusTransaction(CheckMomoStatusRequestDto requestDto) {
        try {
            String rawSignature = String.format(
                    "accessKey=%s&orderId=%s&partnerCode=%s&requestId=%s",
                    accessKey, requestDto.getOrderId(), partnerCode, requestDto.getOrderId()
            );

            String signature = createSignature(rawSignature);

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("partnerCode", partnerCode);
            requestBody.put("requestId", requestDto.getOrderId());
            requestBody.put("orderId", requestDto.getOrderId());
            requestBody.put("signature", signature);
            requestBody.put("lang", "vi");

            ResponseEntity<?> response = restTemplate.postForEntity(
                    momoQueryUrl, requestBody, Object.class);

            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Server error", "error", e.getMessage()));
        }
    }
}
