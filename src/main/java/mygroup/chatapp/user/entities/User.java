package mygroup.chatapp.user.entities;

import jakarta.persistence.*;
import lombok.*;
import mygroup.chatapp.room.entities.Room;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(insertable = false, updatable = false)
    private Role role;


    @ManyToMany
    @JoinTable(
            name ="room_users",
            joinColumns = @JoinColumn(name="room_id"),
            inverseJoinColumns = @JoinColumn(name="user_id"))
    private List<Room> rooms = new ArrayList<>();
}
