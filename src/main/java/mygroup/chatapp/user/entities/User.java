package mygroup.chatapp.user.entities;

import jakarta.persistence.*;
import lombok.*;
import mygroup.chatapp.room.entities.Room;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User implements UserDetails {

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.toString()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
