package io.github.t0m4uk1991.notifyme.tg.command;

public enum Commands {

    JOIN_COMMAND("/join"),
    LEAVE_COMMAND("/leave");

    private final String commandName;
    Commands(String commandName) { this.commandName = commandName; }
    public String value() { return this.commandName; }
}
