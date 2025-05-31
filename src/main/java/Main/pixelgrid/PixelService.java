package Main.pixelgrid;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PixelService {

    private final ConcurrentHashMap<String, Pixel> pixels = new ConcurrentHashMap<>();
    private int gridWidth = 25; // Початкова ширина сітки
    private int gridHeight = 25; // Початкова висота сітки

    public void paintPixel(int x, int y, String color, String nickname) {
        if (x >= 0 && x < gridWidth && y >= 0 && y < gridHeight) {
            pixels.put(x + "," + y, new Pixel(x, y, color, nickname));
        }
    }

    public void clearPixel(int x, int y) {
        pixels.remove(x + "," + y);
    }

    public void clearAll() {
        pixels.clear();
    }

    public void fillAll(String color, String nickname) {
        pixels.clear();
        for (int x = 0; x < gridWidth; x++) {
            for (int y = 0; y < gridHeight; y++) {
                pixels.put(x + "," + y, new Pixel(x, y, color, nickname));
            }
        }
    }

    public void resizeGrid(int newWidth, int newHeight) {
        this.gridWidth = newWidth;
        this.gridHeight = newHeight;
        // Очищаємо пікселі, які виходять за межі нової сітки
        pixels.clear();
    }

    public List<Pixel> getAllPixels() {
        return new ArrayList<>(pixels.values());
    }

    public int getGridWidth() {
        return gridWidth;
    }

    public int getGridHeight() {
        return gridHeight;
    }
}