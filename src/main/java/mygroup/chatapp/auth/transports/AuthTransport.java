package mygroup.chatapp.auth.transports;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mygroup.chatapp.user.entities.Role;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthTransport {
    private String token;
    private String username;
    private Role role;
}
