package assignment.chat.client;

import assignment.chat.core.MessageListener;
import assignment.chat.model.Message;
import assignment.chat.model.User;

/**
 * Adapter interface — allows different client communication adapters to conform to same contract.
 */
public interface ClientAdapter extends MessageListener {
    /**
     * Start the client (e.g., attach to a console, websocket, etc.)
     */
    void start();

    /**
     * Stop the client.
     */
    void stop();

    /**
     * Send a message through this client's channel (user typed).
     */
    void sendMessage(Message message);

    /**
     * Return the user associated with this adapter.
     */
    User getUser();
}