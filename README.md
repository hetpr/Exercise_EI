# Educational Initiatives Campus Placement
## Coding Assignment Submission 
**Candidate:** Het Prajapati (22BCP039)

---

## Assignment Overview
This repository contains solutions to the **Coding Assignment** given as part of the **Educational Initiatives (EI) – Campus Placement Process 2026**.  

The assignment was structured into two main exercises:
1. **Exercise 1 – Design Pattern Demonstrations**  
2. **Exercise 2 – Mini Project (Problem 5: Real-time Chat Application)**  

All solutions are implemented in **Java** as console applications, with strong focus on:
- **Code quality and SOLID principles**  
- **Use of Behavioral, Creational, and Structural Design Patterns**  
- **Clean package structure and modular design**  
- **Defensive programming, validations, and error handling** 

---

## Exercise 1 – Design Patterns
Demonstrated six use cases with two examples each from:  
- **Behavioral Patterns** (Strategy, Observer)  
- **Creational Patterns** (Singleton, Factory)  
- **Structural Patterns** (Adapter, Decorator)  

The goal was to keep examples **business-oriented** and simple for clarity.

---

## Exercise 2 – Mini Project (Problem 5)

### Problem Statement
Build a **real-time chat application** where users can:  
- Create or join chat rooms using a unique ID  
- Send and receive messages in real time  
- View active users in the chat room  
- (Optional) Private messaging  
- (Optional) Message history persistence  

This corresponds to **Problem 5** of the given assignment.  

---

### Design Patterns Applied
- **Observer Pattern (Behavioral):** Chat room notifies all users about new messages.  
- **Singleton Pattern (Creational):** Global `ChatRoomManager` manages state of chat rooms.  
- **Adapter Pattern (Structural):** `ClientAdapter` abstraction allows different communication methods (Console, WebSocket).  

---

### Folder Structure
    src/main/java/com/example/chat/
    │
    ├── app/
    │   └── ChatApp.java            # Main console application
    │
    ├── model/
    │   ├── User.java
    │   └── Message.java
    │
    ├── core/                       # (Observer pattern)
    │   ├── MessageListener.java
    │   └── ChatRoom.java
    │
    ├── manager/                    # (Singleton pattern)
    │   └── ChatRoomManager.java
    │
    ├── client/                     # (Adapter pattern)
    │   ├── ClientAdapter.java
    │   ├── ConsoleClientAdapter.java
    │   └── WebSocketClientAdapter.java
