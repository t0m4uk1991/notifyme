package io.github.t0m4uk1991.notifyme.tg.storage;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import java.io.File;
import java.util.concurrent.ConcurrentMap;

public class TgBotStorage {
    private ConcurrentMap<String,Long> map;

    private TgBotStorage() {
        String userHome = System.getProperty("user.home");
        String pathSeparator = File.separator;
        String fileDName = ".notifyme.data";
        String fileDbPath = String.format("%s%s%s", userHome, pathSeparator, fileDName);

        DB db = DBMaker
                .fileDB(fileDbPath)
                .fileMmapEnable()
                .closeOnJvmShutdown()
                .make();
        map = db
                .hashMap("map", Serializer.STRING, Serializer.LONG)
                .createOrOpen();
    }

    private static TgBotStorage data = new TgBotStorage();
    public static TgBotStorage getInstance() {
        return data;
    }

    public Long getChatId(String username) {
        return map.get(username);
    }

    public void addUsernameChatIdPair(String username, Long chatId) {
        map.put(username, chatId);
    }

    public void removeUsernameChatIdPair(String username) {
        map.remove(username);
    }

}
