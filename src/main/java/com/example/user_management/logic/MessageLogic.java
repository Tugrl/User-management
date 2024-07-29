package com.example.user_management.logic;

import com.example.user_management.entity.Inbox;
import com.example.user_management.entity.Message;
import com.example.user_management.entity.User;
import com.example.user_management.repository.InboxRepository;
import com.example.user_management.repository.MessageRepository;
import com.example.user_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class MessageLogic {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private InboxRepository inboxRepository;

    public List<Message> getAllMesages() {
        return messageRepository.findAll();
    }
    public Message getMessageById(UUID id) {
        return messageRepository.findById(id).orElseThrow(() -> new RuntimeException("Message not found"));

    }
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }
    public Message createMessage(Message message,UUID receiverId) {
        User sender = userRepository.findById(message.getSender().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        User receiver = userRepository.findById(receiverId)
                        .orElseThrow(() -> new RuntimeException("Receiver not found"));
        message.setSender(sender);
        message.setSendDate(LocalDateTime.now());
        message.setReceiverId(receiverId);
        Message savedMessage = messageRepository.save(message);
        Inbox inbox = new Inbox();
        inbox.setMessage(savedMessage);
        inbox.setReceivedDate(LocalDateTime.now());
        inbox.setDeleted(false);
        inbox.setReceiverUser(receiver);
        inboxRepository.save(inbox);
        return savedMessage;

    }
}
