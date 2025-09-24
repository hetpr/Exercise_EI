package assignment.chat.manager;

import assignment.chat.core.ChatRoom;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Singleton: manages chat rooms, ensures single global manager.
 */
public class ChatRoomManager {

    private static volatile ChatRoomManager instance;

    // thread-safe map of rooms
    private final Map<String, ChatRoom> rooms = new ConcurrentHashMap<>();

    private ChatRoomManager() { }

    public static ChatRoomManager getInstance() {
        if (instance == null) {
            synchronized (ChatRoomManager.class) {
                if (instance == null) instance = new ChatRoomManager();
            }
        }
        return instance;
    }

    public ChatRoom createRoom(String roomId) {
        if (roomId == null || roomId.trim().isEmpty()) {
            throw new IllegalArgumentException("roomId required");
        }
        return rooms.computeIfAbsent(roomId, ChatRoom::new);
    }

    public ChatRoom getRoom(String roomId) {
        return rooms.get(roomId);
    }

    public Map<String, ChatRoom> getAllRooms() {
        return Collections.unmodifiableMap(rooms);
    }
}