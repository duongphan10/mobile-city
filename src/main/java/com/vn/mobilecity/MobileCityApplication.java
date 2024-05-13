package com.vn.mobilecity;

import com.vn.mobilecity.constant.OrderStatusConstant;
import com.vn.mobilecity.constant.PaymentTypeConstant;
import com.vn.mobilecity.constant.RoleConstant;
import com.vn.mobilecity.domain.entity.OrderStatus;
import com.vn.mobilecity.domain.entity.PaymentType;
import com.vn.mobilecity.domain.entity.Role;
import com.vn.mobilecity.repository.NotificationRepository;
import com.vn.mobilecity.repository.OrderStatusRepository;
import com.vn.mobilecity.repository.PaymentTypeRepository;
import com.vn.mobilecity.repository.RoleRepository;
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
                roleRepository.save(new Role(1, RoleConstant.ADMIN.getName(), null, null));
                roleRepository.save(new Role(2, RoleConstant.USER.getName(), null, null));
                roleRepository.save(new Role(3, RoleConstant.SUPPORT.getName(), null, null));
            }

            //init order status
            if (orderStatusRepository.count() == 0) {
                orderStatusRepository.save(new OrderStatus(1, OrderStatusConstant.WAITING.getName(), null, null));
                orderStatusRepository.save(new OrderStatus(2, OrderStatusConstant.CONFIRMED.getName(), null, null));
                orderStatusRepository.save(new OrderStatus(3, OrderStatusConstant.DELIVERING.getName(), null, null));
                orderStatusRepository.save(new OrderStatus(4, OrderStatusConstant.DELIVERED.getName(), null, null));
                orderStatusRepository.save(new OrderStatus(5, OrderStatusConstant.CANCELLED.getName(), null, null));
            }

            //init payment type
            if (paymentTypeRepository.count() == 0) {
                paymentTypeRepository.save(new PaymentType(1, PaymentTypeConstant.MOMO.getName(), null, true, null));
                paymentTypeRepository.save(new PaymentType(2, PaymentTypeConstant.VNPAY.getName(), null, true, null));
                paymentTypeRepository.save(new PaymentType(3, PaymentTypeConstant.OFFLINE.getName(), null, true, null));
            }
        };
    }

}
