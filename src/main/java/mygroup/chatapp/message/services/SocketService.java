package mygroup.chatapp.message.services;

import com.corundumstudio.socketio.SocketIOClient;
import org.springframework.boot.configurationprocessor.json.JSONObject;

public interface SocketService {
    void saveAndBroadcastMessage(SocketIOClient senderClient, JSONObject messageTransport);
}
