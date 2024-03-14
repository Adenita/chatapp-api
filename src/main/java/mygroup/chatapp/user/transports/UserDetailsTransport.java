package mygroup.chatapp.user.transports;

import lombok.Builder;
import lombok.Getter;
import mygroup.chatapp.room.transports.RoomTransport;
import mygroup.chatapp.user.entities.Role;

import java.util.List;

@Getter
@Builder
public class UserDetailsTransport {
    private Long id;
    private String name;
    private Role role;
    private List<RoomTransport> roomTransports;
}
