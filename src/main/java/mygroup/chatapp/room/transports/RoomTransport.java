package mygroup.chatapp.room.transports;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mygroup.chatapp.room.entities.RoomType;

@Builder
@Getter
@Setter
public class RoomTransport {
    private Long id;
    private String name;
    private RoomType type;
}
