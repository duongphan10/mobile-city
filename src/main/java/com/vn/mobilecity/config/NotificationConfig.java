package com.vn.mobilecity.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationConfig {
    @Value("${notification.order.id}")
    public static int ORDER = 1;
    @Value("${notification.review.id}")
    public static int REVIEW = 2;
}
