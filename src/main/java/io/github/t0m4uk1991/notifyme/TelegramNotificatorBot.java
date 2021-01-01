package io.github.t0m4uk1991.notifyme;

import io.github.t0m4uk1991.notifyme.tg.Bt;
import io.github.t0m4uk1991.notifyme.tg.configuration.BotConfig;
import io.github.t0m4uk1991.notifyme.tg.storage.TgBotStorage;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import spark.Route;

import static spark.Spark.post;

public class TelegramNotificatorBot {

    public static void main(String...args) {
        Bt bot = initAndGetBot();
        post("/notify/:recipient", processNotifyRequest(bot));
    }

    private static Route processNotifyRequest(Bt bot) {
        return (req, res) -> {
            String recipient = req.params("recipient");
            String message = req.body();

            Long chatId = TgBotStorage.getInstance().getChatId(recipient);

            if (chatId == null) {
                return String.format(
                        "%s not found. Pls ask user with specified name to join the bot by following link t.me/%s",
                        recipient, BotConfig.getBotName());
            }

            bot.sendMessage(chatId, message);

            return "OK";
        };
    }

    private static Bt initAndGetBot() {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        Bt bot = new Bt();
        try {
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return bot;
    }
}
