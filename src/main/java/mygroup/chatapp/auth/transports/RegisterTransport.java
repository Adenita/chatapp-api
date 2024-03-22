package mygroup.chatapp.auth.transports;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mygroup.chatapp.user.entities.Role;


@Getter
@Setter
@Builder
public class RegisterTransport {
    private String name;
    private Role role;
    private String username;
    private String password;
}
