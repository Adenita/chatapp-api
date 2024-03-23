package mygroup.chatapp.room.mappers;

import mygroup.chatapp.room.entities.Room;
import mygroup.chatapp.room.transports.RoomDetailsTransport;
import mygroup.chatapp.user.mappers.UserMapper;

import java.util.ArrayList;
import java.util.List;

public class RoomDetailsMapper {
    public static RoomDetailsTransport toTransport(Room room) {
        return RoomDetailsTransport
                .builder()
                .id(room.getId())
                .name(room.getName())
                .type(room.getType())
                .maxUsers(room.getMaxUsers())
                .users(UserMapper.toTransport(room.getUsers()))
                .build();
    }

    public static Room toEntity(RoomDetailsTransport roomTransport) {
        return Room
                .builder()
                .id(roomTransport.getId())
                .name(roomTransport.getName())
                .type(roomTransport.getType())
                .maxUsers(roomTransport.getMaxUsers())
                .users(UserMapper.toEntity(roomTransport.getUsers()))
                .build();
    }

    public static List<RoomDetailsTransport> toTransport(List<Room> rooms) {
        List<RoomDetailsTransport> roomTransports = new ArrayList<>();
        rooms.forEach(room -> roomTransports.add(RoomDetailsMapper.toTransport(room)));
        return roomTransports;
    }

    public static List<Room> toEntity(List<RoomDetailsTransport> roomTransports) {
        List<Room> rooms = new ArrayList<>();
        roomTransports.forEach(roomTransport -> rooms.add(RoomDetailsMapper.toEntity(roomTransport)));
        return rooms;
    }
}
