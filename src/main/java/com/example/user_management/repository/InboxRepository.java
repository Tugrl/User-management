package com.example.user_management.repository;

import com.example.user_management.entity.Inbox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InboxRepository extends JpaRepository<Inbox, UUID> {
    List<Inbox> findByRecipientIdAndIsDeletedFalse(UUID recipientId);
}
