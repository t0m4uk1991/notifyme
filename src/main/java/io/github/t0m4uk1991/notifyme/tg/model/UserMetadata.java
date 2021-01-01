package io.github.t0m4uk1991.notifyme.tg.model;

public class UserMetadata {

    private Long userId;
    private Long chatId;
    private String username;

    public UserMetadata(Long userId, Long chatId, String username) {
        this.userId = userId;
        this.chatId = chatId;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }
}
