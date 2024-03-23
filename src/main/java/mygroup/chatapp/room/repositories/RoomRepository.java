package mygroup.chatapp.room.repositories;

import mygroup.chatapp.room.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query(value="select r.* from room r join room_users ru on r.id = ru.room_id where user_id = :userId", nativeQuery=true)
    List<Room> getUserRooms(Long userId);

}
