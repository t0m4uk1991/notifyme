package io.github.t0m4uk1991.notifyme.tg.service;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class TgService {

    private TelegramLongPollingBot botRef;

    public TgService(TelegramLongPollingBot botRef) {
        this.botRef = botRef;
    }

    public void sendTextMessage(Long chatId, String text) {

        SendMessage message = new SendMessage()
                .setChatId(chatId)
                .setText(text);
        try {
            botRef.sendMessage(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
