package io.github.t0m4uk1991.notifyme.tg.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class BotConfig {

    private static Properties prop = null;

    static {

        prop = new Properties();
        String propFileName = "application.properties";

        InputStream inputStream = BotConfig.class.getClassLoader().getResourceAsStream(propFileName);

        if (inputStream != null) {
            try {
                prop.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new RuntimeException("property file '" + propFileName + "' not found in the classpath");
        }
    }

    public static String getProp(String key) {
        if (prop != null) return prop.getProperty(key);
        return null;
    }

    public static String getBotName() {
        return getProp("bot.name");
    }

    public static String getBotToken() {
        return getProp("bot.token");
    }

    public static Set<String> getAllowedUsersSet() {
        String allowedUsersCommaSeparatedList = getProp("bot.allowedUsers");
        String[] users = allowedUsersCommaSeparatedList.split(",");
        return new HashSet<String>(Arrays.asList(users));
    }
}
