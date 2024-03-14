package mygroup.chatapp.message.transports;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class MessageListTransport {
    private List<MessageTransport> messageTransports;
}
