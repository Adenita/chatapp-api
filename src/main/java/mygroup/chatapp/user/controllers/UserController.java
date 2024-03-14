package mygroup.chatapp.user.controllers;

import lombok.RequiredArgsConstructor;
import mygroup.chatapp.user.services.UserService;
import mygroup.chatapp.user.transports.UserListTransport;
import mygroup.chatapp.user.transports.UserTransport;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    @GetMapping("")
    public UserListTransport getUsers() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserTransport getUser(@PathVariable Long id) {
        return userService.get(id);
    }

    @PostMapping("")
    public UserTransport addUser(@RequestBody UserTransport user){
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public UserTransport updateUser(@PathVariable Long id, @RequestBody UserTransport user){
        return userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }

}
