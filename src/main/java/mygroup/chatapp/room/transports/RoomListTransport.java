package mygroup.chatapp.room.transports;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RoomListTransport {
    private List<RoomTransport> roomTransports;
}
