package mygroup.chatapp.auth.services;

import lombok.RequiredArgsConstructor;
import mygroup.chatapp.auth.entities.RefreshToken;
import mygroup.chatapp.auth.exceptions.TokenRefreshException;
import mygroup.chatapp.auth.mappers.AuthMapper;
import mygroup.chatapp.auth.transports.AuthTransport;
import mygroup.chatapp.auth.transports.LoginTransport;
import mygroup.chatapp.auth.transports.RegisterTransport;
import mygroup.chatapp.user.entities.User;
import mygroup.chatapp.user.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;

    public AuthTransport register(RegisterTransport registerTransport) {
        User user = AuthMapper.toEntity(
                registerTransport,
                passwordEncoder.encode(registerTransport.getPassword())
        );

        var jwtToken = jwtService.generateToken(user);

        user = userRepository.save(user);
        RefreshToken refreshToken = refreshTokenService.generateAndSaveRefreshToken(user);

        return AuthTransport
                .builder()
                .token(jwtToken)
                .refreshToken(refreshToken.getToken())
                .build();
    }

    public AuthTransport login(LoginTransport loginTransport) {
        Authentication authentication =
                authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginTransport.getUsername(),
                            loginTransport.getPassword()
                    )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        var user = userRepository.findByUsername(loginTransport.getUsername()).orElseThrow();

        String jwtToken = jwtService.generateToken(user);
        RefreshToken refreshToken = refreshTokenService.generateAndSaveRefreshToken(user);
        return AuthTransport.builder()
                .token(jwtToken)
                .refreshToken(refreshToken.getToken())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }

    public AuthTransport refreshToken(AuthTransport authTransport) {
        String myRefreshToken = authTransport.getRefreshToken();
        return refreshTokenService.findByToken(myRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtService.generateToken(user);
                    return AuthTransport
                            .builder()
                            .token(token)
                            .refreshToken(myRefreshToken)
                            .build();
                })
                .orElseThrow(() -> new TokenRefreshException(myRefreshToken, "Refresh token is not in database!"));
    }

}
