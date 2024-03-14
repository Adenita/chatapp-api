package mygroup.chatapp.room.controllers;

import lombok.RequiredArgsConstructor;
import mygroup.chatapp.room.services.RoomService;
import mygroup.chatapp.room.transports.RoomListTransport;
import mygroup.chatapp.room.transports.RoomTransport;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rooms")
public class RoomController {
    private final RoomService roomService;

    @GetMapping("")
    public RoomListTransport getRooms() {
        return roomService.getAll();
    }

    @GetMapping("/{id}")
    public RoomTransport getRoom(@PathVariable Long id) {
        return roomService.get(id);
    }

    @PostMapping("")
    public RoomTransport addRoom(@RequestBody RoomTransport room){
        return roomService.save(room);
    }

    @PutMapping("/{id}")
    public RoomTransport updateRoom(@PathVariable Long id, @RequestBody RoomTransport room){
        return roomService.update(id, room);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomService.delete(id);
    }

}
