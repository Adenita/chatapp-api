package mygroup.chatapp.message.repositories;

import mygroup.chatapp.message.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query(value="select * from message where user_id = :userId and room_id = :roomId", nativeQuery=true)
    List<Message> getUserMessagesForRoom(Long userId, Long roomId);

}
