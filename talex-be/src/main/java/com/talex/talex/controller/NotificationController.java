package com.talex.talex.controller;

import com.talex.talex.dto.res.NotificationResponse;
import com.talex.talex.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {

    public final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<NotificationResponse>> getNotifications(Principal principal) {
        return ResponseEntity.ok(notificationService.getNotifications(principal.getName()));
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<NotificationResponse> markAsRead(
            Principal principal,
            @PathVariable("id") Long notificationId
    ) {
        return ResponseEntity.ok(notificationService.markAsRead(principal.getName(), notificationId));
    }

    @PutMapping("/read-all")
    public ResponseEntity<Void> markAllAsRead(Principal principal) {
        notificationService.markAllAsRead(principal.getName());
        return ResponseEntity.noContent().build();
    }
}
