package mygroup.chatapp.auth.controllers;

import lombok.RequiredArgsConstructor;
import mygroup.chatapp.auth.services.AuthService;
import mygroup.chatapp.auth.transports.AuthTransport;
import mygroup.chatapp.auth.transports.LoginTransport;
import mygroup.chatapp.auth.transports.RegisterTransport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthTransport> register(@RequestBody RegisterTransport request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthTransport> login(@RequestBody LoginTransport request) {
        return ResponseEntity.ok(authService.login(request));
    }

}
