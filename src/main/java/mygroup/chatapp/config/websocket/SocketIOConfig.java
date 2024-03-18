package mygroup.chatapp.config.websocket;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class SocketIOConfig {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public SocketIOServer socketIOServer() {
        Configuration config = new Configuration();
        config.setHostname("${your-ip-address}");
        config.setPort(8081);
        return new SocketIOServer(config);
    }

}
