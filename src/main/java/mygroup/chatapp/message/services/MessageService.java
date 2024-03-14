package mygroup.chatapp.message.services;

import mygroup.chatapp.message.transports.MessageListTransport;
import mygroup.chatapp.message.transports.MessageTransport;
import mygroup.chatapp.shared.GeneralService;

public interface MessageService extends GeneralService<MessageTransport, MessageListTransport> {
    MessageListTransport getUserMessagesForRoom(Long userId, Long professorId);
}
