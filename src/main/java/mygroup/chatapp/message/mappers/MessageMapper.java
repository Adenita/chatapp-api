package mygroup.chatapp.message.mappers;

import mygroup.chatapp.message.entities.Message;
import mygroup.chatapp.message.transports.MessageTransport;
import mygroup.chatapp.room.mappers.RoomMapper;
import mygroup.chatapp.message.transports.MessageListTransport;
import mygroup.chatapp.room.transports.RoomTransport;
import mygroup.chatapp.user.mappers.UserMapper;

import java.util.ArrayList;
import java.util.List;

public class MessageMapper {
    public static MessageTransport toTransport(Message message) {
        return MessageTransport
                .builder()
                .id(message.getId())
                .content(message.getContent())
                .userTransport(UserMapper.toTransport(message.getUser()))
                .roomTransport(RoomMapper.toTransport(message.getRoom()))
                .date(message.getDate())
                .build();
    }

    public static Message toEntity(MessageTransport messageTransport) {
        return Message
                .builder()
                .id(messageTransport.getId())
                .content(messageTransport.getContent())
                .user(UserMapper.toEntity(messageTransport.getUserTransport()))
                .room(RoomMapper.toEntity(messageTransport.getRoomTransport()))
                .date(messageTransport.getDate())
                .build();
    }

    public static List<MessageTransport> toTransport(List<Message> messages) {
        List<MessageTransport> messageTransports = new ArrayList<>();
        messages.forEach(message -> messageTransports.add(MessageMapper.toTransport(message)));
        return messageTransports;
    }

    public static List<Message> toEntity(List<MessageTransport> messageTransports) {
        List<Message> messages = new ArrayList<>();
        messageTransports.forEach(messageTransport -> messages.add(MessageMapper.toEntity(messageTransport)));
        return messages;
    }

    public static MessageListTransport toListTransport(List<MessageTransport> transports) {
        return MessageListTransport.builder().messageTransports(transports).build();
    }
}
