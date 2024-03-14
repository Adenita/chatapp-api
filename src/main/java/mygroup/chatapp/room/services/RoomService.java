package mygroup.chatapp.room.services;

import mygroup.chatapp.room.transports.RoomListTransport;
import mygroup.chatapp.room.transports.RoomTransport;
import mygroup.chatapp.shared.GeneralService;

public interface RoomService extends GeneralService<RoomTransport, RoomListTransport> {
    RoomListTransport getUserRooms(Long userId);
}
