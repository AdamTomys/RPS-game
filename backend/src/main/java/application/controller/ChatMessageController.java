package application.controller;

import application.websocket.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatMessageController {

    @MessageMapping({"/chat"})
    @SendTo("/topic/messages")
    public ChatMessage get(ChatMessage message) {
        return new ChatMessage(message.getPlayerName(), message.getWeapon());
    }

}
