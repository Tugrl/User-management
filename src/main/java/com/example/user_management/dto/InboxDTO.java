package com.example.user_management.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class InboxDTO {
    private UUID id;
    private LocalDateTime receivedDate;
    private LocalDateTime readDate;
    private boolean isDeleted;
    private UserDTO receiverUser;
    private MessageDTO message;

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public LocalDateTime getReceivedDate() {
        return receivedDate;
    }
    public void setReceivedDate(LocalDateTime receivedDate) {
        this.receivedDate = receivedDate;
    }
    public LocalDateTime getReadDate() {
        return readDate;
    }
    public void setReadDate(LocalDateTime readDate) {
        this.readDate = readDate;
    }
    public boolean isDeleted() {
        return isDeleted;
    }
    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
    public UserDTO getReceiverUser() {
        return receiverUser;
    }
    public void setReceiverUser(UserDTO receiverUser) {
        this.receiverUser = receiverUser;
    }
    public MessageDTO getMessage() {
        return message;
    }
    public void setMessage(MessageDTO message) {
        this.message = message;
    }

}
