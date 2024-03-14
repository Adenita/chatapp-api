package mygroup.chatapp.user.transports;

import lombok.Builder;
import lombok.Getter;
import mygroup.chatapp.user.entities.Role;

@Builder
@Getter
public class UserTransport {
    private Long id;
    private String name;
    private Role role;
}
