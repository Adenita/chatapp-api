package mygroup.chatapp.room.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mygroup.chatapp.room.entities.Room;
import mygroup.chatapp.room.mappers.RoomMapper;
import mygroup.chatapp.room.repositories.RoomRepository;
import mygroup.chatapp.room.services.RoomService;
import mygroup.chatapp.room.transports.RoomListTransport;
import mygroup.chatapp.room.transports.RoomTransport;
import mygroup.chatapp.user.entities.User;
import mygroup.chatapp.user.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    @Override
    public RoomListTransport getAll() {
        return RoomMapper.toListTransport(
                RoomMapper.toTransport(roomRepository.findAll())
        );
    }

    @Override
    public RoomTransport get(Long id) {
        Room room = roomRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        return RoomMapper.toTransport(room);
    }

    @Override
    public RoomTransport save(RoomTransport transport) {
        Room room = roomRepository.save(RoomMapper.toEntity(transport));
        return RoomMapper.toTransport(room);
    }

    @Override
    public RoomTransport update(Long id, RoomTransport transport) {
        Room room = roomRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        room.setName(transport.getName());
        room.setType(transport.getType());

        roomRepository.save(room);

        return RoomMapper.toTransport(room);
    }

    @Override
    public void delete(Long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public RoomListTransport getUserRooms(Long userId) {
        return RoomMapper.toListTransport(
                RoomMapper.toTransport(roomRepository.getUserRooms(userId))
        );
    }

    @Override
    @Transactional
    public void joinRoom(Long userId, Long roomId) {
        RoomTransport room = get(roomId);
        List<User> roomUsers = userRepository.getRoomUsers(roomId);

        if (roomUsers.size() < room.getMaxUsers()) {
            roomRepository.joinRoom(userId, roomId);
            return;
        }

        throw new RuntimeException("Room capacity is maxed out");
    }

    @Override
    public RoomListTransport getUserDMs(Long userId) {
        return RoomMapper.toListTransport(
                RoomMapper.toTransport(roomRepository.getUserDMs(userId))
        );
    }

    @Override
    public RoomListTransport getUserChannels(Long userId) {
        return RoomMapper.toListTransport(
                RoomMapper.toTransport(roomRepository.getUserChannels(userId))
        );
    }

    @Override
    public RoomListTransport getAvailableNonUserChannels(Long userId) {
        return RoomMapper.toListTransport(
                RoomMapper.toTransport(roomRepository.getAvailableNonUserChannels(userId))
        );
    }
}
