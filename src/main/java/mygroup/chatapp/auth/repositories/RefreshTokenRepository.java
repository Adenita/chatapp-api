package mygroup.chatapp.auth.repositories;

import mygroup.chatapp.auth.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    @Query(value = "select * from user_refresh_token where token= :token", nativeQuery = true)
    Optional<RefreshToken> findByToken(String token);

    @Modifying
    @Query(value = "delete from user_refresh_token where user_id = :userId", nativeQuery = true)
    void deleteByUser(Long userId);
}
