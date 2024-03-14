package mygroup.chatapp.room.repositories;

import mygroup.chatapp.room.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query(value="select * from room_users where user_id = :userId", nativeQuery=true)
    List<Room> getUserRooms(Long userId);

}
