package io.github.t0m4uk1991.notifyme.tg.command;

import io.github.t0m4uk1991.notifyme.tg.storage.TgBotStorage;
import io.github.t0m4uk1991.notifyme.tg.model.UserMetadata;
import io.github.t0m4uk1991.notifyme.tg.service.TgService;
import org.telegram.telegrambots.logging.BotLogger;

public class JoinCommand implements Command {

    private TgService tgService;

    public JoinCommand(TgService tgService) {
        this.tgService = tgService;
    }

    public void execute(UserMetadata userMetadata) {
        TgBotStorage.getInstance().addUsernameChatIdPair(userMetadata.getUsername(), userMetadata.getChatId());
        String text = String.format("You chat id was stored and it is: %s", userMetadata.getUserId());
        tgService.sendTextMessage(userMetadata.getChatId(), text);

        BotLogger.debug("JOIN_COMMAND", text);
    }
}
