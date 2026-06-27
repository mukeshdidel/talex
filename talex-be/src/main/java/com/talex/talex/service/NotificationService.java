package com.talex.talex.service;

import com.talex.talex.dto.res.NotificationResponse;
import com.talex.talex.entity.Notification;
import com.talex.talex.entity.User;
import com.talex.talex.mapper.NotificationMapper;
import com.talex.talex.repo.NotificationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    public final NotificationRepo notificationRepo;
    public final NotificationMapper notificationMapper;
    public final UserService userService;

    @Transactional
    public void notify(User user, String title, String message) {
        Notification notification = Notification.builder()
                .user(user)
                .title(title)
                .message(message)
                .isRead(false)
                .build();

        notificationRepo.save(notification);
    }

    @Transactional(readOnly = true)
    public List<NotificationResponse> getNotifications(String username) {
        User user = userService.getUserByUsername(username);
        List<Notification> notifications = notificationRepo.findByUser_IdOrderByCreatedAtDesc(user.getId());

        return notificationMapper.toNotificationResponseList(notifications);
    }

    @Transactional
    public NotificationResponse markAsRead(String username, Long notificationId) {
        User user = userService.getUserByUsername(username);
        Notification notification = notificationRepo.findById(notificationId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Notification not found"
                ));

        if (!notification.getUser().getId().equals(user.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "You are not allowed to access this notification"
            );
        }

        notification.setIsRead(true);
        notification = notificationRepo.save(notification);

        return notificationMapper.toNotificationResponse(notification);
    }

    @Transactional
    public void markAllAsRead(String username) {
        User user = userService.getUserByUsername(username);
        List<Notification> notifications = notificationRepo.findByUser_IdAndIsReadFalse(user.getId());

        notifications.forEach(notification -> notification.setIsRead(true));
        notificationRepo.saveAll(notifications);
    }
}
