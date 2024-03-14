package mygroup.chatapp.user.transports;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserListTransport {
    private List<UserTransport> userTransports;
}
