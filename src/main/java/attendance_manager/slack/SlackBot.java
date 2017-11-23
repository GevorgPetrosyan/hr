package attendance_manager.slack;

import attendance_manager.slack.domain.Conversation;
import me.ramswaroop.jbot.core.slack.Bot;
import me.ramswaroop.jbot.core.slack.Controller;
import me.ramswaroop.jbot.core.slack.EventType;
import me.ramswaroop.jbot.core.slack.models.Event;
import me.ramswaroop.jbot.core.slack.models.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;

@Component
@PropertySource("classpath:slack.properties")
public class SlackBot extends Bot {

    private static final Logger logger = LoggerFactory.getLogger(SlackBot.class);

    @Value("${slackBotToken}")
    private String slackToken;

    @Override
    public String getSlackToken() {
        return slackToken;
    }

    @Override
    public Bot getSlackBot() {
        return this;
    }

    @Controller(events = {EventType.DIRECT_MENTION, EventType.DIRECT_MESSAGE})
    public void directMessage(final WebSocketSession webSocketSession, final Event event) {
        final Map<String, Object> attributes = webSocketSession.getAttributes();
        Conversation conversation = (Conversation) attributes.get("conversation");
        if (conversation != null) {
            conversation.accept(event.getText());
        } else {
            conversation = Conversation.start(event.getText());
            attributes.put("conversation", conversation);
        }
        if (conversation.isFinished()) {
            attributes.remove("conversation");
        }
        reply(webSocketSession, event, new Message(conversation.reply()));
    }
}