package mygroup.chatapp.message.services.impl;

import lombok.RequiredArgsConstructor;
import mygroup.chatapp.message.entities.Message;
import mygroup.chatapp.message.mappers.MessageMapper;
import mygroup.chatapp.message.repositories.MessageRepository;
import mygroup.chatapp.message.services.MessageService;
import mygroup.chatapp.message.transports.MessageListTransport;
import mygroup.chatapp.message.transports.MessageTransport;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    @Override
    public MessageListTransport getAll() {
        return MessageMapper.toListTransport(
                MessageMapper.toTransport(messageRepository.findAll())
        );
    }

    @Override
    public MessageTransport get(Long id) {
        Message message = messageRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found"));

        return MessageMapper.toTransport(message);
    }

    @Override
    public MessageTransport save(MessageTransport transport) {
        Message message = messageRepository.save(MessageMapper.toEntity(transport));
        return MessageMapper.toTransport(message);
    }

    @Override
    public MessageTransport update(Long id, MessageTransport transport) {
        Message message = messageRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found"));

        message.setContent(transport.getContent());

        messageRepository.save(message);

        return MessageMapper.toTransport(message);
    }

    @Override
    public void delete(Long id) {
        messageRepository.deleteById(id);
    }

    @Override
    public MessageListTransport getUserMessagesForRoom(Long userId, Long professorId) {
        return MessageMapper.toListTransport(
                MessageMapper.toTransport(messageRepository.getUserMessagesForRoom(userId, professorId))
        );
    }
}
