package mygroup.chatapp.message.controllers;

import lombok.RequiredArgsConstructor;
import mygroup.chatapp.message.services.MessageService;
import mygroup.chatapp.message.transports.MessageListTransport;
import mygroup.chatapp.message.transports.MessageTransport;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/messages")
public class MessageController {
    private final MessageService messageService;

    @GetMapping("")
    public MessageListTransport getMessages() {
        return messageService.getAll();
    }

    @GetMapping("/{id}")
    public MessageTransport getMessage(@PathVariable Long id) {
        return messageService.get(id);
    }

    @PostMapping("")
    public MessageTransport addMessage(@RequestBody MessageTransport message){
        return messageService.save(message);
    }

    @PutMapping("/{id}")
    public MessageTransport updateMessage(@PathVariable Long id, @RequestBody MessageTransport message){
        return messageService.update(id, message);
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Long id) {
        messageService.delete(id);
    }


}
