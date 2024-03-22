package mygroup.chatapp.auth.services;

import lombok.RequiredArgsConstructor;
import mygroup.chatapp.auth.mappers.AuthMapper;
import mygroup.chatapp.auth.transports.AuthTransport;
import mygroup.chatapp.auth.transports.LoginTransport;
import mygroup.chatapp.auth.transports.RegisterTransport;
import mygroup.chatapp.user.entities.User;
import mygroup.chatapp.user.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthTransport register(RegisterTransport registerTransport) {
        User user = AuthMapper.toEntity(
                registerTransport,
                passwordEncoder.encode(registerTransport.getPassword())
        );

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthTransport.builder().token(jwtToken).build();
    }

    public AuthTransport login(LoginTransport loginTransport) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginTransport.getUsername(),
                        loginTransport.getPassword()
                )
        );

        var user = userRepository.findByUsername(loginTransport.getUsername()).orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        return AuthTransport.builder()
                .token(jwtToken)
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }
}
