package mygroup.chatapp.config.websocket;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import mygroup.chatapp.message.services.impl.SocketServiceImpl;
import mygroup.chatapp.message.transports.MessageTransport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
public class SocketComponent {
    private final SocketServiceImpl socketService;

    public SocketComponent(@Lazy SocketIOServer server, SocketServiceImpl socketService) {
        this.socketService = socketService;
        server.addConnectListener(this.onConnected());
        server.addDisconnectListener(this.onDisconnected());
        server.addEventListener("send_message", MessageTransport.class, this.onChatReceived());
    }

    private DataListener<MessageTransport> onChatReceived() {
        return (senderClient, data, ackSender) ->
                socketService.saveAndBroadcastMessage(senderClient, data);
    }

    private ConnectListener onConnected() {
        return (client) -> {
            var params = client.getHandshakeData().getUrlParams();

            if (params.containsKey("room") && params.get("room") != null) {
                String room = String.join("", params.get("room"));
                client.joinRoom(room);
            }

        };

    }

    private DisconnectListener onDisconnected() {
        return client -> {
            var params = client.getHandshakeData().getUrlParams();
            if (params.containsKey("room") && params.get("room") != null) {
                String room = String.join("", params.get("room"));
                client.leaveRoom(room);
            }
        };
    }
}
