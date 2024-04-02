package mygroup.chatapp.auth.entities;

import jakarta.persistence.*;
import lombok.*;
import mygroup.chatapp.user.entities.User;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user_refresh_token")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "expire_date")
    private Instant expireDate;
}
