package Main.pixelgrid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ClientCountService {

    private final AtomicInteger clientCount = new AtomicInteger(0);
    private final Map<String, String> sessionToNickname = new ConcurrentHashMap<>();
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public ClientCountService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @EventListener
    public void onClientConnect(SessionConnectEvent event) {
        int newCount = clientCount.incrementAndGet();
        System.out.println("Client connected, new count: " + newCount); // Дебагінг
        messagingTemplate.convertAndSend("/topic/clientCount", newCount);
        sendNicknameList();
    }

    @EventListener
    public void onClientDisconnect(SessionDisconnectEvent event) {
        String sessionId = event.getSessionId();
        String removedNickname = sessionToNickname.remove(sessionId);
        int newCount = clientCount.decrementAndGet();
        System.out.println("Client disconnected, sessionId: " + sessionId + ", removed nickname: " + removedNickname + ", new count: " + newCount); // Дебагінг
        messagingTemplate.convertAndSend("/topic/clientCount", newCount);
        sendNicknameList();
    }

    public void addNickname(String sessionId, String nickname) {
        sessionToNickname.put(sessionId, nickname);
        System.out.println("Added nickname: " + nickname + " for sessionId: " + sessionId); // Дебагінг
        sendNicknameList();
    }

    private void sendNicknameList() {
        System.out.println("Sending nickname list: " + sessionToNickname.values()); // Дебагінг
        messagingTemplate.convertAndSend("/topic/nicknames", sessionToNickname.values());
    }

    public String[] getNicknames() {
        return sessionToNickname.values().toArray(new String[0]);
    }

    public void kickClient(String nickname) {
        sessionToNickname.entrySet().stream()
                .filter(entry -> entry.getValue().equals(nickname))
                .findFirst()
                .ifPresent(entry -> {
                    String sessionId = entry.getKey();
                    messagingTemplate.convertAndSend("/topic/kick/" + sessionId, "You have been kicked for violating the rules.");
                    sessionToNickname.remove(sessionId);
                    int newCount = clientCount.decrementAndGet();
                    System.out.println("Kicked client, nickname: " + nickname + ", new count: " + newCount); // Дебагінг
                    messagingTemplate.convertAndSend("/topic/clientCount", newCount);
                    sendNicknameList();
                });
    }

    public int getClientCount() {
        return clientCount.get();
    }
}