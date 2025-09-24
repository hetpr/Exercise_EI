package assignment.chat.core;

import assignment.chat.model.Message;

/**
 * Observer interface used by ChatRoom to notify clients.
 */
public interface MessageListener {
    /**
     * Called when a new message arrives in the room (or user event).
     */
    void onMessageReceived(Message message);

    /**
     * Optionally notify about system events, e.g., user joined/left.
     */
    void onSystemEvent(String event);
}