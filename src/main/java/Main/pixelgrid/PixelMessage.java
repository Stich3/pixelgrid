package Main.pixelgrid;

public class PixelMessage {
    private int x;
    private int y;
    private String color;
    private boolean clear;
    private boolean fill;
    private String password;
    private String nickname; // Додаємо поле для нікнейму

    public PixelMessage() {}

    public PixelMessage(int x, int y, String color, boolean clear, String password, String nickname) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.clear = clear;
        this.password = password;
        this.nickname = nickname;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isClear() {
        return clear;
    }

    public void setClear(boolean clear) {
        this.clear = clear;
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}