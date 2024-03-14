package mygroup.chatapp.user.repositories;

import mygroup.chatapp.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value="select * from room_users where room_id = :roomId", nativeQuery=true)
    List<User> getRoomUsers(Long roomId);
}
