package io.github.t0m4uk1991.notifyme.tg.command;

public class TgMessageFormatter {

    public static String makeBold(String args) {
        return String.format("<b>%s</b>", args);
    }
    public static String makeItalic(String args) {
        return String.format("<i>%s</i>", args);
    }
    public static String appendNewLine(String args) {
        return String.format("%s\n", args);
    }
}
