package mygroup.chatapp.user.transports;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mygroup.chatapp.user.entities.Role;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserTransport {
    private Long id;
    private String name;
    private Role role;
}
