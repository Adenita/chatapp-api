package mygroup.chatapp.message.transports;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mygroup.chatapp.room.transports.RoomTransport;
import mygroup.chatapp.user.transports.UserTransport;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageTransport {
    private Long id;
    private String content;
    private UserTransport userTransport;
    private RoomTransport roomTransport;
}
