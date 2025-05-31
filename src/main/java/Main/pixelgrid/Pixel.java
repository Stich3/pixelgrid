package Main.pixelgrid;

public class Pixel {
    private int x;
    private int y;
    private String color;
    private boolean clear;
    private boolean fill;
    private boolean resize;
    private int newWidth;
    private int newHeight;
    private String password;
    private String nickname;

    // Конструктори
    public Pixel() {}

    public Pixel(int x, int y, String color, String nickname) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.nickname = nickname;
    }

    // Геттери і сеттери
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

    public boolean isResize() {
        return resize;
    }

    public void setResize(boolean resize) {
        this.resize = resize;
    }

    public int getNewWidth() {
        return newWidth;
    }

    public void setNewWidth(int newWidth) {
        this.newWidth = newWidth;
    }

    public int getNewHeight() {
        return newHeight;
    }

    public void setNewHeight(int newHeight) {
        this.newHeight = newHeight;
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