package mygroup.chatapp.user.controllers;

import lombok.RequiredArgsConstructor;
import mygroup.chatapp.room.services.RoomService;
import mygroup.chatapp.room.transports.RoomListTransport;
import mygroup.chatapp.user.services.UserService;
import mygroup.chatapp.user.transports.UserListTransport;
import mygroup.chatapp.user.transports.UserTransport;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final RoomService roomService;
    @GetMapping("")
    public UserListTransport getUsers() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserTransport getUser(@PathVariable Long id) {
        return userService.get(id);
    }

    @GetMapping("/{username}$")
    public UserTransport getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
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

    @GetMapping("/{id}/rooms")
    public RoomListTransport getUserRooms(@PathVariable Long id) {
        return roomService.getUserRooms(id);
    }

    @GetMapping("/{id}/dms")
    public RoomListTransport getUserDMs(@PathVariable Long id) {
        return roomService.getUserDMs(id);
    }

    @GetMapping("/{id}/channels")
    public RoomListTransport getUserChannels(@PathVariable Long id) {
        return roomService.getUserChannels(id);
    }

    @GetMapping("/{id}/non")
    public RoomListTransport getAvailableNonUserChannels(@PathVariable Long id) {
        return roomService.getAvailableNonUserChannels(id);
    }
}
