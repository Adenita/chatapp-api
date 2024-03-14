package mygroup.chatapp.user.services.impl;

import lombok.RequiredArgsConstructor;
import mygroup.chatapp.user.entities.User;
import mygroup.chatapp.user.mappers.UserMapper;
import mygroup.chatapp.user.repositories.UserRepository;
import mygroup.chatapp.user.services.UserService;
import mygroup.chatapp.user.transports.UserListTransport;
import mygroup.chatapp.user.transports.UserTransport;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserListTransport getAll() {
        return UserMapper.toListTransport(
                UserMapper.toTransport(userRepository.findAll())
        );
    }

    @Override
    public UserTransport get(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return UserMapper.toTransport(user);
    }

    @Override
    public UserTransport save(UserTransport transport) {
        User user = userRepository.save(UserMapper.toEntity(transport));
        return UserMapper.toTransport(user);
    }

    @Override
    public UserTransport update(Long id, UserTransport transport) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(transport.getName());
        user.setRole(transport.getRole());

        userRepository.save(user);

        return UserMapper.toTransport(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserListTransport getRoomUsers(Long userId) {
        return UserMapper.toListTransport(
                UserMapper.toTransport(userRepository.getRoomUsers(userId))
        );
    }
}
