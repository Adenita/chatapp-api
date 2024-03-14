package mygroup.chatapp.room.transports;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mygroup.chatapp.room.entities.RoomType;
import mygroup.chatapp.user.transports.UserTransport;

import java.util.List;

@Builder
@Getter
@Setter
public class RoomDetailsTransport {
    private Long id;
    private String name;
    private RoomType type;
    private List<UserTransport> users;
}
