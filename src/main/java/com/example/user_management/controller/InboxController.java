package com.example.user_management.controller;

import com.example.user_management.dto.InboxDTO;
import com.example.user_management.service.InboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/inbox")
public class InboxController {
    @Autowired
    private InboxService inboxService;

    @GetMapping("/{receiverId}")
    public List<InboxDTO> getInboxByReceiver(@PathVariable UUID receiverId) {
        return inboxService.getInboxByReceiver(receiverId);
    }
    @PostMapping
    public ResponseEntity<InboxDTO> receiveMessage(@RequestBody InboxDTO inboxDTO) {
        InboxDTO received = inboxService.receiveMessage(inboxDTO);
        return ResponseEntity.status(201).body(received);

    }
    @PatchMapping("/read/{inboxId}")
    public ResponseEntity<InboxDTO> marksAsRead(@PathVariable UUID inboxId) {
        inboxService.markAsRead(inboxId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{inboxId}")
    public ResponseEntity<InboxDTO> deleteMessage(@PathVariable UUID inboxId) {
        inboxService.deleteMessage(inboxId);
        return ResponseEntity.noContent().build();
    }


}
