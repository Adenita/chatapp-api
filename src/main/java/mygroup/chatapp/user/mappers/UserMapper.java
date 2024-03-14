package mygroup.chatapp.user.mappers;

import mygroup.chatapp.user.entities.User;
import mygroup.chatapp.user.transports.UserListTransport;
import mygroup.chatapp.user.transports.UserTransport;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    public static UserTransport toTransport(User user) {
        return UserTransport
                .builder()
                .id(user.getId())
                .name(user.getName())
                .role(user.getRole())
                .build();
    }

    public static User toEntity(UserTransport userTransport) {
        return User
                .builder()
                .id(userTransport.getId())
                .name(userTransport.getName())
                .role(userTransport.getRole())
                .build();
    }

    public static List<UserTransport> toTransport(List<User> users) {
        List<UserTransport> userTransports = new ArrayList<>();
        users.forEach(user -> userTransports.add(UserMapper.toTransport(user)));
        return userTransports;
    }

    public static List<User> toEntity(List<UserTransport> userTransports) {
        List<User> users = new ArrayList<>();
        userTransports.forEach(userTransport -> users.add(UserMapper.toEntity(userTransport)));
        return users;
    }

    public static UserListTransport toListTransport(List<UserTransport> transports) {
        return UserListTransport.builder().userTransports(transports).build();
    }
}
