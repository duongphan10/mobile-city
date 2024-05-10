package com.vn.mobilecity;

import com.vn.mobilecity.constant.RoleConstant;
import com.vn.mobilecity.constant.StatusConstant;
import com.vn.mobilecity.domain.entity.OrderStatus;
import com.vn.mobilecity.domain.entity.Role;
import com.vn.mobilecity.repository.OrderStatusRepository;
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
                roleRepository.save(new Role(1, RoleConstant.ADMIN, null, null));
                roleRepository.save(new Role(2, RoleConstant.USER, null, null));
                roleRepository.save(new Role(3, RoleConstant.SUPPORT, null, null));
            }
            //init status
//            if (orderStatusRepository.count() == 0) {
//                orderStatusRepository.save(new OrderStatus(1, StatusConstant.PENDING.getName(), null, null));
//                orderStatusRepository.save(new OrderStatus(2, StatusConstant.WAITING.getName(), null, null));
//                orderStatusRepository.save(new OrderStatus(3, StatusConstant.DELIVERING.getName(), null, null));
//                orderStatusRepository.save(new OrderStatus(4, StatusConstant.DELIVERED.getName(), null, null));
//                orderStatusRepository.save(new OrderStatus(5, StatusConstant.CANCELLED.getName(), null, null));
//            }
        };
    }

}
