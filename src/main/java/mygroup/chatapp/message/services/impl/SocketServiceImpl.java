package mygroup.chatapp.message.services.impl;

import com.corundumstudio.socketio.SocketIOClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import mygroup.chatapp.message.services.MessageService;
import mygroup.chatapp.message.services.SocketService;
import mygroup.chatapp.message.transports.MessageTransport;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SocketServiceImpl implements SocketService {
    private final MessageService messageService;
    private final ObjectMapper objectMapper;

    public void saveAndBroadcastMessage(SocketIOClient senderClient, JSONObject messageTransport) {

        MessageTransport parsedMessage = convertJsonToMessageTransport(messageTransport);
        MessageTransport storedMessage = messageService.save(parsedMessage);
        broadcastMessage(
                senderClient,
                storedMessage,
                storedMessage.getRoomTransport().getId() + ""
        );
    }

    private void broadcastMessage(SocketIOClient senderClient, MessageTransport messageTransport, String room) {
        for (
                SocketIOClient client: senderClient.getNamespace().getRoomOperations(room).getClients()
        ) {
            String stringifyMessage = convertMessageToJson(messageTransport);
            client.sendEvent("read_message", stringifyMessage);
        }
    }

    public MessageTransport convertJsonToMessageTransport(JSONObject jsonObject) {
        try {
            return objectMapper.readValue(jsonObject.toString(), MessageTransport.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String convertMessageToJson(MessageTransport messageTransport) {
        try {
            return objectMapper.writeValueAsString(messageTransport);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
