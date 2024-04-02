package mygroup.chatapp.auth.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mygroup.chatapp.auth.entities.RefreshToken;
import mygroup.chatapp.auth.exceptions.TokenRefreshException;
import mygroup.chatapp.auth.repositories.RefreshTokenRepository;
import mygroup.chatapp.user.entities.User;
import mygroup.chatapp.user.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Transactional
    public void deleteByUserId(Long userId) {
        refreshTokenRepository.deleteByUser(userId);
    }

    public RefreshToken generateAndSaveRefreshToken(UserDetails userDetails) {
        String token = jwtService.generateRefreshToken(userDetails);
        Instant instant = jwtService.extractExpiration(token).toInstant();
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();

        RefreshToken refreshToken = RefreshToken
                                      .builder()
                                      .user(user)
                                      .token(token)
                                      .expireDate(instant)
                                      .build();

        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpireDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "You have been signed out. Refresh token has expired");
        }
        return token;
    }
}