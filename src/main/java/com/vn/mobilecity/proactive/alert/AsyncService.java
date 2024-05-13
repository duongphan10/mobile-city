package com.vn.mobilecity.proactive.alert;

import com.vn.mobilecity.domain.entity.Notification;
import com.vn.mobilecity.repository.NotificationRepository;
import com.vn.mobilecity.util.TelegramUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AsyncService {
    @Value("${proactive.alert.enable}")
    private Boolean enableSendMsg;
    private final TelegramUtil telegramUtil;
    private final NotificationRepository notificationRepository;

    private boolean isEnableSendMessageToTelegram() {
        return enableSendMsg != null && enableSendMsg;
    }

    /**
     * Send Message to telegram
     *
     * @param id      notification id
     * @param message noi dung tin nhan telegram
     */
    @Async("taskExecutor")
    public void sendTelegramMessage(Integer id, String message) {
        log.info("send telegram message asynchronously in thread: " + Thread.currentThread().getName());
        try {
            if (!isEnableSendMessageToTelegram()) {
                log.info("send telegram message asynchronously DISABLE. Check environment 'proactive.alert.enable'");
                return;
            }
            Notification notification = notificationRepository.findById(id).orElse(null);
            if (notification == null) {
                log.warn("Notification with id " + id + " not found.");
                return;
            }
            telegramUtil.SendMessageTelegram(notification.getToken(), notification.getChatId(), notification.getTopicId(), message);
        } catch (Exception e) {
            log.warn("sendTelegramMessage throw exception: " + e.getMessage());
        }
    }

}
