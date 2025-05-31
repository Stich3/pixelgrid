package Main.pixelgrid;

public class KickMessage {
    private String nickname;
    private String password;

    // Конструктори
    public KickMessage() {}

    public KickMessage(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    // Геттери і сеттери
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}