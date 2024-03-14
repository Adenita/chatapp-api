package mygroup.chatapp.room.services.impl;

import lombok.RequiredArgsConstructor;
import mygroup.chatapp.room.entities.Room;
import mygroup.chatapp.room.mappers.RoomMapper;
import mygroup.chatapp.room.repositories.RoomRepository;
import mygroup.chatapp.room.services.RoomService;
import mygroup.chatapp.room.transports.RoomListTransport;
import mygroup.chatapp.room.transports.RoomTransport;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

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
}
