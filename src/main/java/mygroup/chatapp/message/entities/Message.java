package mygroup.chatapp.message.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mygroup.chatapp.room.entities.Room;
import mygroup.chatapp.user.entities.User;

@Getter
@Setter
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    private String content;
}
