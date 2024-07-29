package com.example.user_management.service;

import com.example.user_management.dto.InboxDTO;
import com.example.user_management.entity.Inbox;
import com.example.user_management.logic.InboxLogic;
import com.example.user_management.mapper.InboxMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class InboxService {
    @Autowired
    private InboxMapper inboxMapper;
    @Autowired
    private InboxLogic inboxLogic;

    public List<InboxDTO> getInboxByReceiver(UUID receiverId) {
    return inboxLogic.getInboxByReceiver(receiverId).stream().map(inboxMapper::inboxToInboxDTO)
            .collect(Collectors.toList());
    }
    public InboxDTO receiveMessage(InboxDTO inboxDTO) {
        Inbox inbox=inboxMapper.inboxDTOToInbox(inboxDTO);
        Inbox receivedInbox=inboxLogic.receiveMessage(inbox);
        return inboxMapper.inboxToInboxDTO(receivedInbox);
    }
    public void markAsRead(UUID inboxId) {
        inboxLogic.markMessageAsRead(inboxId);
    }
    public void deleteMessage(UUID inboxId) {
        inboxLogic.deleteMessage(inboxId);
    }
}
