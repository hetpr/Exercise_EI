package assignment.chat.core;

import assignment.chat.model.Message;
import assignment.chat.model.User;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Observable — maintains observers (MessageListener) and notifies them on new messages or events.
 */
public class ChatRoom {
    private final String roomId;
    private final List<Message> history = new ArrayList<>();
    private final Set<User> activeUsers = new LinkedHashSet<>();
    // CopyOnWriteArrayList so we can safely iterate while notifying
    private final List<MessageListener> listeners = new CopyOnWriteArrayList<>();

    public ChatRoom(String roomId) {
        this.roomId = Objects.requireNonNull(roomId);
    }

    public String getRoomId() {
        return roomId;
    }

    public synchronized void join(User user, MessageListener listener) {
        if (user == null || listener == null) throw new IllegalArgumentException("user and listener required");
        boolean added = activeUsers.add(user);
        listeners.add(listener);
        if (added) {
            String event = user.getUsername() + " joined the room.";
            notifySystemEvent(event);
        }
    }

    public synchronized void leave(User user, MessageListener listener) {
        activeUsers.remove(user);
        listeners.remove(listener);
        String event = user.getUsername() + " left the room.";
        notifySystemEvent(event);
    }

    public synchronized void postMessage(Message message) {
        if (message == null) return;
        history.add(message);
        notifyMessage(message);
    }

    public synchronized List<Message> getHistory() {
        return new ArrayList<>(history);
    }

    public synchronized List<String> getActiveUsernames() {
        List<String> names = new ArrayList<>();
        for (User u : activeUsers) names.add(u.getUsername());
        return names;
    }

    private void notifyMessage(Message message) {
        for (MessageListener listener : listeners) {
            try {
                // deliver only public messages to all; private messages delivered to sender & recipient
                if (!message.isPrivate()) {
                    listener.onMessageReceived(message);
                } else {
                    // private: deliver to listener only if its user is sender or recipient
                    listener.onMessageReceived(message);
                }
            } catch (Throwable t) {
                // Defensive logging — don't let one bad listener break notifications.
                System.err.println("Failed to notify a listener: " + t.getMessage());
            }
        }
    }

    private void notifySystemEvent(String event) {
        for (MessageListener listener : listeners) {
            try {
                listener.onSystemEvent(event);
            } catch (Throwable t) {
                System.err.println("Failed to notify a listener of system event: " + t.getMessage());
            }
        }
    }
}