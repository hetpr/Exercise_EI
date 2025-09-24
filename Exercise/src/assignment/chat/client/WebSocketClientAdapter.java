package assignment.chat.client;

import assignment.chat.model.Message;
import assignment.chat.model.User;

/**
 * Simulated WebSocket adapter stub showing how another protocol adapter would fit.
 * In a real application this would manage socket lifecycle, message framing, etc.
 */
public class WebSocketClientAdapter implements ClientAdapter {

    private final User user;

    public WebSocketClientAdapter(User user) {
        this.user = user;
    }

    @Override
    public void start() {
        // In a real adapter we'd open websocket connection.
        System.out.println("[WebSocketAdapter] starting for " + user.getUsername());
    }

    @Override
    public void stop() {
        System.out.println("[WebSocketAdapter] stopping for " + user.getUsername());
    }

    @Override
    public void sendMessage(Message message) {
        // Convert message to wire format and send via socket (simulated)
        System.out.println("[WebSocketAdapter] would send: " + message.formatted());
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void onMessageReceived(Message message) {
        // In a real adapter, this would push the message over socket to connected client
        System.out.println("[WebSocketAdapter RECEIVED] " + message.formatted());
    }

    @Override
    public void onSystemEvent(String event) {
        System.out.println("[WebSocketAdapter SYSTEM] " + event);
    }
}