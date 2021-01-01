package io.github.t0m4uk1991.notifyme.tg.command;

import io.github.t0m4uk1991.notifyme.tg.storage.TgBotStorage;
import io.github.t0m4uk1991.notifyme.tg.model.UserMetadata;
import io.github.t0m4uk1991.notifyme.tg.service.TgService;
import org.telegram.telegrambots.logging.BotLogger;

public class LeaveCommand implements Command {

    private TgService tgService;

    public LeaveCommand(TgService tgService) {
        this.tgService = tgService;
    }

    public void execute(UserMetadata userMetadata) {
        TgBotStorage.getInstance().removeUsernameChatIdPair(userMetadata.getUsername());
        String text = String.format("You chat id %s was removed from bot db.", userMetadata.getUserId());
        tgService.sendTextMessage(userMetadata.getChatId(), text);

        BotLogger.debug("LEAVE_COMMAND", text);
    }
}
