package com.vn.mobilecity;

import com.vn.mobilecity.constant.OrderStatusConstant;
import com.vn.mobilecity.constant.PaymentTypeConstant;
import com.vn.mobilecity.constant.PromotionConstant;
import com.vn.mobilecity.constant.RoleConstant;
import com.vn.mobilecity.domain.entity.OrderStatus;
import com.vn.mobilecity.domain.entity.PaymentType;
import com.vn.mobilecity.domain.entity.Promotion;
import com.vn.mobilecity.domain.entity.Role;
import com.vn.mobilecity.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class MobileCityApplication {
    private final RoleRepository roleRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final PaymentTypeRepository paymentTypeRepository;
    private final PromotionRepository promotionRepository;
    private final NotificationRepository notificationRepository;

    public static void main(String[] args) {
        Environment env = SpringApplication.run(MobileCityApplication.class, args).getEnvironment();
        String appName = env.getProperty("spring.application.name");
        if (appName != null) {
            appName = appName.toUpperCase();
        }
        String port = env.getProperty("server.port");
        log.info("Url swagger-ui: http://localhost:" + port + "/swagger-ui.html");
        log.info("----- START SUCCESS " + appName + " Application -----");
    }

    @Bean
    CommandLineRunner init() {
        return args -> {
            //init role
            if (roleRepository.count() == 0) {
                roleRepository.save(new Role(RoleConstant.ADMIN.getId(), RoleConstant.ADMIN.getName(), RoleConstant.ADMIN.getDescription(), null));
                roleRepository.save(new Role(RoleConstant.USER.getId(), RoleConstant.USER.getName(), RoleConstant.USER.getDescription(), null));
                roleRepository.save(new Role(RoleConstant.SUPPORT.getId(), RoleConstant.SUPPORT.getName(), RoleConstant.SUPPORT.getDescription(), null));
            }

            //init order status
            if (orderStatusRepository.count() == 0) {
                orderStatusRepository.save(new OrderStatus(OrderStatusConstant.WAITING.getId(), OrderStatusConstant.WAITING.getName(), OrderStatusConstant.WAITING.getDescription(), null));
                orderStatusRepository.save(new OrderStatus(OrderStatusConstant.CONFIRMED.getId(), OrderStatusConstant.CONFIRMED.getName(), OrderStatusConstant.CONFIRMED.getDescription(), null));
                orderStatusRepository.save(new OrderStatus(OrderStatusConstant.DELIVERING.getId(), OrderStatusConstant.DELIVERING.getName(), OrderStatusConstant.WAITING.getDescription(), null));
                orderStatusRepository.save(new OrderStatus(OrderStatusConstant.DELIVERED.getId(), OrderStatusConstant.DELIVERED.getName(), OrderStatusConstant.DELIVERING.getDescription(), null));
                orderStatusRepository.save(new OrderStatus(OrderStatusConstant.CANCELLED.getId(), OrderStatusConstant.CANCELLED.getName(), OrderStatusConstant.CANCELLED.getDescription(), null));
            }

            //init payment type
            if (paymentTypeRepository.count() == 0) {
                paymentTypeRepository.save(new PaymentType(PaymentTypeConstant.OFFLINE.getId(), PaymentTypeConstant.OFFLINE.getName(), PaymentTypeConstant.OFFLINE.getDescription(), true, null));
                paymentTypeRepository.save(new PaymentType(PaymentTypeConstant.MOMO.getId(), PaymentTypeConstant.MOMO.getName(), PaymentTypeConstant.MOMO.getDescription(), true, null));
                paymentTypeRepository.save(new PaymentType(PaymentTypeConstant.VNPAY.getId(), PaymentTypeConstant.VNPAY.getName(), PaymentTypeConstant.VNPAY.getDescription(), true, null));
                paymentTypeRepository.save(new PaymentType(PaymentTypeConstant.TTKNH.getId(), PaymentTypeConstant.TTKNH.getName(), PaymentTypeConstant.TTKNH.getDescription(), true, null));
            }

            //init promotion
            if (promotionRepository.count() == 0) {
                promotionRepository.save(new Promotion(PromotionConstant.NONE.getId(), PromotionConstant.NONE.getName(), PromotionConstant.NONE.getDescription(), null));
                promotionRepository.save(new Promotion(PromotionConstant.GIAM_GIA.getId(), PromotionConstant.GIAM_GIA.getName(), PromotionConstant.GIAM_GIA.getDescription(), null));
                promotionRepository.save(new Promotion(PromotionConstant.MOI_RA_MAT.getId(), PromotionConstant.MOI_RA_MAT.getName(), PromotionConstant.MOI_RA_MAT.getDescription(), null));
                promotionRepository.save(new Promotion(PromotionConstant.GIA_RE_ONLINE.getId(), PromotionConstant.GIA_RE_ONLINE.getName(), PromotionConstant.GIA_RE_ONLINE.getDescription(), null));
                promotionRepository.save(new Promotion(PromotionConstant.TRA_GOP.getId(), PromotionConstant.TRA_GOP.getName(), PromotionConstant.TRA_GOP.getDescription(), null));
            }
        };
    }

}
