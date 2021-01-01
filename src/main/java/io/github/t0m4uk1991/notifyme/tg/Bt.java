package io.github.t0m4uk1991.notifyme.tg;

import com.google.inject.Singleton;
import io.github.t0m4uk1991.notifyme.tg.command.*;
import io.github.t0m4uk1991.notifyme.tg.configuration.BotConfig;
import io.github.t0m4uk1991.notifyme.tg.model.UserMetadata;
import io.github.t0m4uk1991.notifyme.tg.service.TgService;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class Bt extends TelegramLongPollingBot {

    private Map<String, Command> commands;

    public Bt(){
        TgService tgService = new TgService(this);
        commands = new HashMap<String, Command>(){
            { put(Commands.JOIN_COMMAND.value(), new JoinCommand(tgService)); }
            { put(Commands.LEAVE_COMMAND.value(), new LeaveCommand(tgService)); }
        };
    }

    public void onUpdateReceived(Update update) {
        if (isMessageValid(update)) {
            Message message = update.getMessage();
            System.out.println(update.toString());

            String userName = message.getFrom().getUserName();
            if (BotConfig.getAllowedUsersSet().contains(userName)) {
                executeCommand(update);
            } else {
                sendMessageToNotAllowedUsers(update);
            }
        }
    }

    public void sendMessage(Long chatId, String messageBody) {
        try {
            this.sendMessage(new SendMessage(chatId, messageBody));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    private void executeCommand(Update update) {
        String commandName = update.getMessage().getText();
        UserMetadata userMetadata = new UserMetadata(
                update.getMessage().getFrom().getId().longValue(),
                update.getMessage().getChatId(),
                update.getMessage().getFrom().getUserName());
        commands.get(commandName).execute(userMetadata);
    }

    private void sendMessageToNotAllowedUsers(Update update) {
        SendMessage message = new SendMessage()
                .setChatId(update.getMessage().getChatId())
                .setText("Access denied");
        try {
            sendMessage(message);
        }catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private boolean isMessageValid(Update update) {
        return update.hasMessage() && update.getMessage().hasText();
    }

    public String getBotUsername() {
        return BotConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return BotConfig.getBotToken();
    }
}
