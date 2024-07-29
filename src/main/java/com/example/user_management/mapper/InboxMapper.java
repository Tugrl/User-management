package com.example.user_management.mapper;

import com.example.user_management.dto.InboxDTO;
import com.example.user_management.entity.Inbox;
import org.springframework.stereotype.Component;

@Component
public class InboxMapper {
    private final MessageMapper messageMapper;
    private final UserMapper userMapper;

    public InboxMapper(MessageMapper messageMapper, UserMapper userMapper) {
        this.messageMapper = messageMapper;
        this.userMapper = userMapper;
    }

    public InboxDTO inboxToInboxDTO(Inbox inbox) {
        if (inbox == null) {
            return null;
        };
        InboxDTO inboxDTO = new InboxDTO();
        inboxDTO.setId(inbox.getId());
        inboxDTO.setReceivedDate(inbox.getReceivedDate());
        inboxDTO.setReadDate(inbox.getReadDate());
        inboxDTO.setDeleted(inbox.isDeleted());
        inboxDTO.setMessage(messageMapper.messageToMessageDTO(inbox.getMessage()));
        inboxDTO.setReceiverUser(userMapper.userToUserDTO(inbox.getReceiverUser()));
        return inboxDTO;
    }
    public Inbox inboxDTOToInbox(InboxDTO inboxDTO) {
        if (inboxDTO == null) {
            return null;
        }
        Inbox inbox = new Inbox();
        inbox.setId(inboxDTO.getId());
        inbox.setReceivedDate(inboxDTO.getReceivedDate());
        inbox.setReadDate(inboxDTO.getReadDate());
        inbox.setDeleted(inboxDTO.isDeleted());
        return inbox;
    }
}
