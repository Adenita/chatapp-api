package mygroup.chatapp.auth.mappers;

import mygroup.chatapp.auth.transports.RegisterTransport;
import mygroup.chatapp.user.entities.User;

public class AuthMapper {

    public static RegisterTransport toTransport(User user, String encodedPassword) {
        return RegisterTransport.builder()
                .name(user.getName())
                .username(user.getUsername())
                .password(encodedPassword)
                .role(user.getRole())
                .build();
    }

    public static User toEntity(RegisterTransport userTransport, String encodedPassword) {
         return User.builder()
                 .name(userTransport.getName())
                 .username(userTransport.getUsername())
                 .password(encodedPassword)
                 .role(userTransport.getRole())
                 .build();
    }
}
