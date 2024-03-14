package mygroup.chatapp.user.mappers;

import mygroup.chatapp.room.mappers.RoomMapper;
import mygroup.chatapp.user.entities.User;
import mygroup.chatapp.user.transports.UserDetailsTransport;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsMapper {

    public static UserDetailsTransport toTransport(User user) {
        return UserDetailsTransport
                .builder()
                .id(user.getId())
                .name(user.getName())
                .role(user.getRole())
                .roomTransports(RoomMapper.toTransport(user.getRooms()))
                .build();
    }

    public static User toEntity(UserDetailsTransport userTransport) {
        return User
                .builder()
                .id(userTransport.getId())
                .name(userTransport.getName())
                .role(userTransport.getRole())
                .rooms(RoomMapper.toEntity(userTransport.getRoomTransports()))
                .build();
    }

    public static List<UserDetailsTransport> toTransport(List<User> users) {
        List<UserDetailsTransport> userTransports = new ArrayList<>();
        users.forEach(user -> userTransports.add(UserDetailsMapper.toTransport(user)));
        return userTransports;
    }

    public static List<User> toEntity(List<UserDetailsTransport> userTransports) {
        List<User> users = new ArrayList<>();
        userTransports.forEach(userTransport -> users.add(UserDetailsMapper.toEntity(userTransport)));
        return users;
    }
}
