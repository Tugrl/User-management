package com.example.user_management.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tbl_inbox")
public class Inbox {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private LocalDateTime receivedDate;
    @Column
    private LocalDateTime readDate;
    @Column
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User recipient;

    @ManyToOne
    @JoinColumn(name = "message_id")
    private Message message;

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
    public User getReceiverUser() {
        return recipient;
    }
    public void setReceiverUser(User receiverUser) {
        this.recipient = receiverUser;
    }
    public Message getMessage() {
        return message;
    }
    public void setMessage(Message message) {
        this.message = message;
    }

}
