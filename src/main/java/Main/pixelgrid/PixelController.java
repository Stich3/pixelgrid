package Main.pixelgrid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PixelController {

    private static final String ADMIN_PASSWORD = "091011"; // Пароль для адмін-команд

    @Autowired
    private PixelService pixelService;

    @Autowired
    private ClientCountService clientCountService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/paint")
    public void paint(Pixel pixel) {
        if (pixel.getX() == -1 && pixel.getY() == -1 && pixel.isClear()) {
            // Очищення всієї сітки
            if (ADMIN_PASSWORD.equals(pixel.getPassword())) {
                pixelService.clearAll();
                messagingTemplate.convertAndSend("/topic/pixels", pixel);
            }
        } else if (pixel.getX() == -2 && pixel.getY() == -2 && pixel.isFill()) {
            // Заповнення всієї сітки
            if (ADMIN_PASSWORD.equals(pixel.getPassword())) {
                pixelService.fillAll(pixel.getColor(), pixel.getNickname());
                messagingTemplate.convertAndSend("/topic/pixels", pixel);
            }
        } else if (pixel.getX() == -3 && pixel.getY() == -3 && pixel.isResize()) {
            // Зміна розміру сітки
            if (ADMIN_PASSWORD.equals(pixel.getPassword())) {
                pixelService.resizeGrid(pixel.getNewWidth(), pixel.getNewHeight());
                messagingTemplate.convertAndSend("/topic/pixels", pixel);
            }
        } else if (pixel.isClear()) {
            pixelService.clearPixel(pixel.getX(), pixel.getY());
            messagingTemplate.convertAndSend("/topic/pixels", pixel);
        } else {
            pixelService.paintPixel(pixel.getX(), pixel.getY(), pixel.getColor(), pixel.getNickname());
            messagingTemplate.convertAndSend("/topic/pixels", pixel);
        }
    }

    @MessageMapping("/registerNickname")
    public void registerNickname(NicknameMessage message) {
        clientCountService.addNickname(message.getSessionId(), message.getNickname());
    }

    @MessageMapping("/kick")
    public void kickClient(KickMessage message) {
        if (ADMIN_PASSWORD.equals(message.getPassword())) {
            clientCountService.kickClient(message.getNickname());
        }
    }

    @GetMapping("/initial-state")
    public List<Pixel> getInitialState() {
        return pixelService.getAllPixels();
    }

    @GetMapping("/client-count")
    public int getClientCount() {
        return clientCountService.getClientCount();
    }

    @GetMapping("/nicknames")
    public String[] getNicknames() {
        return clientCountService.getNicknames();
    }
}