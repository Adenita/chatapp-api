package mygroup.chatapp.user.services;

import mygroup.chatapp.shared.GeneralService;
import mygroup.chatapp.user.transports.UserListTransport;
import mygroup.chatapp.user.transports.UserTransport;

public interface UserService extends GeneralService<UserTransport, UserListTransport> {
    UserListTransport getRoomUsers(Long userId);

    UserTransport getUserByUsername(String username);
}
