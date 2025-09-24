package assignment.chat.app;

import assignment.chat.core.ChatRoom;
import assignment.chat.manager.ChatRoomManager;
import assignment.chat.model.Message;
import assignment.chat.model.User;
import assignment.chat.client.ClientAdapter;
import assignment.chat.client.ConsoleClientAdapter;
import assignment.chat.client.WebSocketClientAdapter;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * Main console app — menu-driven.
 */
public class ChatApp {

    private final ChatRoomManager manager = ChatRoomManager.getInstance();
    private final Scanner scanner = new Scanner(System.in);
    private ClientAdapter currentAdapter;
    private ChatRoom currentRoom;

    public static void main(String[] args) {
        ChatApp app = new ChatApp();
        app.seedDemoRoomsAndBots();
        app.run();
    }

    private void seedDemoRoomsAndBots() {
        ChatRoom room = manager.createRoom("Room123");
        Thread bot = new Thread(() -> {
            User botUser = new User("NewsBot");
            ConsoleClientAdapter botAdapter = new ConsoleClientAdapter(botUser);
            room.join(botUser, botAdapter);
            String[] messages = {
                    "Welcome to Room123!",
                    "This is a demo bot posting updates.",
                    "Remember to be kind to other users.",
                    "Tip: use option 6 to see active users."
            };
            Random r = new Random();
            try {
                while (true) {
                    Thread.sleep(12000 + r.nextInt(8000));
                    room.postMessage(new Message(botUser, messages[r.nextInt(messages.length)]));
                }
            } catch (InterruptedException ignored) {
            }
        }, "DemoBot-Thread");
        bot.setDaemon(true);
        bot.start();
    }

    private void run() {
        println("=== Simple Real-time Chat Console ===");
        boolean exit = false;
        while (!exit) {
            try {
                showMainMenu();
                String option = scanner.nextLine().trim();
                switch (option) {
                    case "1": createRoom(); break;
                    case "2": listRooms(); break;
                    case "3": joinRoom(); break;
                    case "4": sendMessage(); break;
                    case "5": sendPrivateMessage(); break;
                    case "6": listActiveUsers(); break;
                    case "7": showHistory(); break;
                    case "8": leaveRoom(); break;
                    case "9": switchAdapter(); break;
                    case "0": exit = true; break;
                    default: println("Unknown option. Try again."); break;
                }
            } catch (Exception ex) {
                println("[ERROR] " + ex.getMessage());
            }
        }
        println("Goodbye!");
        scanner.close();
    }

    private void showMainMenu() {
        println("\nMain Menu:");
        println("1) Create chat room");
        println("2) List chat rooms");
        println("3) Join chat room");
        println("4) Send public message");
        println("5) Send private message");
        println("6) List active users in current room");
        println("7) Show message history");
        println("8) Leave current room");
        println("9) Switch adapter (Console/WebSocket-sim)");
        println("0) Exit");
        print("> ");
    }

    private void createRoom() {
        print("Enter new room id: ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) {
            println("Room id required.");
            return;
        }
        ChatRoom room = manager.createRoom(id);
        println("Room created: " + room.getRoomId());
    }

    private void listRooms() {
        Map<String, ChatRoom> rooms = manager.getAllRooms();
        if (rooms.isEmpty()) {
            println("No active rooms.");
            return;
        }
        println("Active rooms:");
        for (String id : rooms.keySet()) {
            println(" - " + id);
        }
    }

    private void joinRoom() {
        print("Enter room id to join: ");
        String roomId = scanner.nextLine().trim();
        if (roomId.isEmpty()) {
            println("Room id required.");
            return;
        }
        ChatRoom room = manager.createRoom(roomId); // get or create
        print("Enter your username: ");
        String username = scanner.nextLine().trim();
        if (username.isEmpty()) {
            println("username required.");
            return;
        }
        User user = new User(username);

        println("Choose adapter: 1) Console  2) WebSocket-sim (demo)");
        print("> ");
        String choice = scanner.nextLine().trim();
        if ("2".equals(choice)) {
            currentAdapter = new WebSocketClientAdapter(user);
        } else {
            currentAdapter = new ConsoleClientAdapter(user);
        }

        currentAdapter.start();
        room.join(user, currentAdapter);
        currentRoom = room;
        println("Joined room " + room.getRoomId() + " as " + user.getUsername());
    }

    private void sendMessage() {
        if (!checkInRoom()) return;
        print("Enter message (empty to cancel): ");
        String text = scanner.nextLine();
        if (text.trim().isEmpty()) {
            println("Cancelled.");
            return;
        }
        Message message = new Message(currentAdapter.getUser(), text.trim());
        currentRoom.postMessage(message);
    }

    private void sendPrivateMessage() {
        if (!checkInRoom()) return;
        print("Recipient username: ");
        String to = scanner.nextLine().trim();
        if (to.isEmpty()) {
            println("Recipient required.");
            return;
        }
        print("Message content: ");
        String text = scanner.nextLine().trim();
        if (text.isEmpty()) {
            println("Message required.");
            return;
        }
        User recipient = new User(to); // plain user object; room will not enforce presence
        Message pm = new Message(currentAdapter.getUser(), text, true, recipient);
        currentRoom.postMessage(pm);
    }

    private void listActiveUsers() {
        if (!checkInRoom()) return;
        List<String> users = currentRoom.getActiveUsernames();
        println("Active users in " + currentRoom.getRoomId() + ":");
        for (String u : users) println(" - " + u);
    }

    private void showHistory() {
        if (!checkInRoom()) return;
        List<Message> history = currentRoom.getHistory();
        if (history.isEmpty()) {
            println("No messages yet.");
            return;
        }
        println("=== Message history ===");
        for (Message m : history) {
            println(m.formatted());
        }
    }

    private void leaveRoom() {
        if (!checkInRoom()) return;
        currentRoom.leave(currentAdapter.getUser(), currentAdapter);
        currentAdapter.stop();
        println("Left room " + currentRoom.getRoomId());
        currentRoom = null;
        currentAdapter = null;
    }

    private void switchAdapter() {
        if (currentAdapter == null) {
            println("You're not in a room. Join one first.");
            return;
        }
        User user = currentAdapter.getUser();
        // leave and rejoin with different adapter
        currentRoom.leave(user, currentAdapter);
        currentAdapter.stop();

        println("Choose adapter: 1) Console  2) WebSocket-sim (demo)");
        print("> ");
        String choice = scanner.nextLine().trim();
        if ("2".equals(choice)) {
            currentAdapter = new WebSocketClientAdapter(user);
        } else {
            currentAdapter = new ConsoleClientAdapter(user);
        }
        currentAdapter.start();
        currentRoom.join(user, currentAdapter);
        println("Switched adapter for user " + user.getUsername());
    }

    private boolean checkInRoom() {
        if (currentRoom == null || currentAdapter == null) {
            println("You are not in a room. Join a room first (option 3).");
            return false;
        }
        return true;
    }

    // small helpers for console IO
    private void println(String s) {
        System.out.println(s);
    }
    private void print(String s) {
        System.out.print(s);
    }
}