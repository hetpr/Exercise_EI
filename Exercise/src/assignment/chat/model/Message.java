package assignment.chat.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private final User sender;
    private final String content;
    private final LocalDateTime timestamp;
    private final boolean privateMessage;
    private final User recipient; // nullable for public messages

    public Message(User sender, String content) {
        this(sender, content, false, null);
    }

    public Message(User sender, String content, boolean privateMessage, User recipient) {
        this.sender = sender;
        this.content = content;
        this.timestamp = LocalDateTime.now();
        this.privateMessage = privateMessage;
        this.recipient = recipient;
    }

    public User getSender() { return sender; }
    public String getContent() { return content; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public boolean isPrivate() { return privateMessage; }
    public User getRecipient() { return recipient; }

    public String formatted() {
        String time = timestamp.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        if (privateMessage && recipient != null) {
            return String.format("[%s] [PM] %s -> %s: %s", time, sender.getUsername(), recipient.getUsername(), content);
        }
        return String.format("[%s] %s: %s", time, sender.getUsername(), content);
    }
}