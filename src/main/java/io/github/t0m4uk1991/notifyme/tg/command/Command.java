package io.github.t0m4uk1991.notifyme.tg.command;

import io.github.t0m4uk1991.notifyme.tg.model.UserMetadata;

public interface Command {
    void execute(UserMetadata userMetadata);
}


