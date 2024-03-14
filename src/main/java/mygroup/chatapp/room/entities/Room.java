package mygroup.chatapp.room.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mygroup.chatapp.user.entities.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(insertable = false, updatable = false)
    private RoomType type;

    @ManyToMany
    @JoinTable(
            name ="room_users",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="room_id"))
    private List<User> users = new ArrayList<>();
}
