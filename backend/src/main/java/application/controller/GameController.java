package application.controller;

import application.service.ComputerGameProcessor;
import application.websocket.ChatMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping()
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GameController {

    final ComputerGameProcessor computerGameProcessor;

    @GetMapping(value = "/computer/{playerWeaponString}")
    public String getResult (@PathVariable String playerWeaponString) {
        return computerGameProcessor.performGame(playerWeaponString);
    }

    @MessageMapping({"/chat"})
    @SendTo("/topic/messages")
    public ChatMessage get(ChatMessage message) {
        return new ChatMessage(message.getPlayerName(), message.getWeapon());
    }


}
