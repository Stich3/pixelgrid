package Main.pixelgrid;

public class NicknameMessage {
    private String sessionId;
    private String nickname;

    // Конструктори
    public NicknameMessage() {}

    public NicknameMessage(String sessionId, String nickname) {
        this.sessionId = sessionId;
        this.nickname = nickname;
    }

    // Геттери і сеттери
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}