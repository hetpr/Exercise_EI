package assignment.chat.client;

import assignment.chat.model.Message;
import assignment.chat.model.User;

/**
 * A simple console adapter which prints messages to console.
 * Conforms to ClientAdapter (Adapter pattern) so other adapters can be swapped in easily.
 */
public class ConsoleClientAdapter implements ClientAdapter {

    private final User user;
    private volatile boolean running = false;

    public ConsoleClientAdapter(User user) {
        this.user = user;
    }

    @Override
    public void start() {
        running = true;
        // Nothing asynchronous here — ChatApp drives input loop — adapter listens to notifications
    }

    @Override
    public void stop() {
        running = false;
    }

    @Override
    public void sendMessage(Message message) {
        // For console adapter, sending is handled by ChatApp -> ChatRoom. This is kept for interface completeness.
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void onMessageReceived(Message message) {
        // Display messages intended for all or private messages involving this user
        if (!message.isPrivate()) {
            System.out.println(message.formatted());
        } else {
            // show private message only if sender or recipient matches this user
            String u = user.getUsername();
            if (message.getSender().getUsername().equals(u)
                    || (message.getRecipient() != null && message.getRecipient().getUsername().equals(u))) {
                System.out.println(message.formatted());
            }
        }
    }

    @Override
    public void onSystemEvent(String event) {
        System.out.println("[system] " + event);
    }
}