package mygroup.chatapp.room.repositories;

import mygroup.chatapp.room.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query(value="select r.* from room r join room_users ru on r.id = ru.room_id where user_id = :userId", nativeQuery=true)
    List<Room> getUserRooms(Long userId);

    @Modifying
    @Query(value="insert into room_users (user_id, room_id) values (:userId, :roomId)", nativeQuery=true)
    void joinRoom(Long userId, Long roomId);

    @Query(value="select r.* from room r join room_users ru on r.id = ru.room_id where user_id = :userId and r.type = 'DM'", nativeQuery=true)
    List<Room> getUserDMs(Long userId);

    @Query(value="select r.* from room r join room_users ru on r.id = ru.room_id where user_id = :userId and r.type = 'GROUP'", nativeQuery=true)
    List<Room> getUserChannels(Long userId);

    @Query(value="select distinct r.* from room r left join room_users ru on r.id = ru.room_id and user_id = :userId " +
            "where user_id IS NULL and r.type = 'GROUP' " +
            "and (SELECT COUNT(*) FROM room_users WHERE ru.room_id = r.id) < r.max_users", nativeQuery=true)
    List<Room> getAvailableNonUserChannels(Long userId);

}
