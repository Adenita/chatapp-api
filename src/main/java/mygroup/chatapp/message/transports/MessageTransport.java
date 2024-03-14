package mygroup.chatapp.message.transports;

import lombok.Builder;
import lombok.Getter;
import mygroup.chatapp.room.transports.RoomTransport;
import mygroup.chatapp.user.transports.UserTransport;

@Getter
@Builder
public class MessageTransport {
    private Long id;
    private String content;
    private UserTransport userTransport;
    private RoomTransport roomTransport;
}
