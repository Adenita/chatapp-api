package mygroup.chatapp.room.transports;

import lombok.*;
import mygroup.chatapp.room.entities.RoomType;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomTransport {
    private Long id;
    private String name;
    private RoomType type;
}
