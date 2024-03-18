package mygroup.chatapp.message.services.impl;

import com.corundumstudio.socketio.SocketIOClient;
import lombok.RequiredArgsConstructor;
import mygroup.chatapp.message.services.MessageService;
import mygroup.chatapp.message.services.SocketService;
import mygroup.chatapp.message.transports.MessageTransport;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SocketServiceImpl implements SocketService {
    private final MessageService messageService;

    public void saveAndBroadcastMessage(SocketIOClient senderClient, MessageTransport messageTransport) {

        MessageTransport storedMessage = messageService.save(messageTransport);

        broadcastMessage(
                senderClient,
                storedMessage,
                messageTransport.getRoomTransport().getId() + ""
        );
    }

    private void broadcastMessage(SocketIOClient senderClient, MessageTransport messageTransport, String room) {
        for (
                SocketIOClient client: senderClient.getNamespace().getRoomOperations(room).getClients()
        ) {
            client.sendEvent("read_message", messageTransport);
        }
    }

}
