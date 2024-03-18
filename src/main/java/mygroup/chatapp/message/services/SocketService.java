package mygroup.chatapp.message.services;

import com.corundumstudio.socketio.SocketIOClient;
import mygroup.chatapp.message.transports.MessageTransport;

public interface SocketService {
    void saveAndBroadcastMessage(SocketIOClient senderClient, MessageTransport messageTransport);
}
