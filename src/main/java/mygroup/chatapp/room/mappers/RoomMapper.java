package mygroup.chatapp.room.mappers;

import mygroup.chatapp.room.entities.Room;
import mygroup.chatapp.room.transports.RoomListTransport;
import mygroup.chatapp.room.transports.RoomTransport;

import java.util.ArrayList;
import java.util.List;

public class RoomMapper {
    public static RoomTransport toTransport(Room room) {
        return RoomTransport
                .builder()
                .id(room.getId())
                .name(room.getName())
                .type(room.getType())
                .build();
    }

    public static Room toEntity(RoomTransport roomTransport) {
        return Room
                .builder()
                .id(roomTransport.getId())
                .name(roomTransport.getName())
                .type(roomTransport.getType())
                .build();
    }

    public static List<RoomTransport> toTransport(List<Room> rooms) {
        List<RoomTransport> roomTransports = new ArrayList<>();
        rooms.forEach(room -> roomTransports.add(RoomMapper.toTransport(room)));
        return roomTransports;
    }

    public static List<Room> toEntity(List<RoomTransport> roomTransports) {
        List<Room> rooms = new ArrayList<>();
        roomTransports.forEach(roomTransport -> rooms.add(RoomMapper.toEntity(roomTransport)));
        return rooms;
    }

    public static RoomListTransport toListTransport(List<RoomTransport> transports) {
        return RoomListTransport.builder().roomTransports(transports).build();
    }
}
