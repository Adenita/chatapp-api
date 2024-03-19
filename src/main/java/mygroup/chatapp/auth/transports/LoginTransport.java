package mygroup.chatapp.auth.transports;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginTransport {
    private String username;
    private String password;
}
