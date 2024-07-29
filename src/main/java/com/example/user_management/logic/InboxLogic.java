package com.example.user_management.logic;

import com.example.user_management.entity.Inbox;
import com.example.user_management.repository.InboxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class InboxLogic {

    @Autowired
    private InboxRepository inboxRepository;

    public List<Inbox> getInboxByReceiver(UUID receiver) {
    return inboxRepository.findByRecipientIdAndIsDeletedFalse(receiver);
    }
    public Inbox receiveMessage(Inbox inbox) {
        return inboxRepository.save(inbox);
    }
    public void markMessageAsRead(UUID inboxId) {
        Inbox inbox = inboxRepository.findById(inboxId).orElseThrow(() -> new RuntimeException("inbox not found"));
        inbox.setReadDate(LocalDateTime.now());
        inboxRepository.save(inbox);
    }
    public void deleteMessage(UUID inboxId) {
        Inbox inbox=inboxRepository.findById(inboxId).orElseThrow(() -> new RuntimeException("inbox not found"));
        inbox.setDeleted(true);
        inboxRepository.save(inbox);
    }

}
