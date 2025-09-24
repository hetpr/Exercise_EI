# Design Patterns & Mini Project Assignment (2025-26)

## Overview
This repository contains solutions for the **Coding Exercises (2025-26)**.  
The assignment is divided into **two exercises**:

1. **Exercise 1** – Demonstrations of Software Design Patterns  
2. **Exercise 2** – Mini-project implementation (Problem 5: Real-time Chat Application)

All code is written in **Java**, structured as simple **console-based applications**, following:
- **OOP principles**  
- **SOLID design principles**  
- **Design patterns (Behavioral, Creational, Structural)**  
- **Clean code & folder organization**  

---

## Exercise 1 – Design Patterns Demo
Implemented six use cases (two each from Behavioral, Creational, Structural patterns).  
Each example is business-oriented and placed in its own package for clarity.  
The goal was to showcase **understanding of patterns**, not build a full application.

### Implemented Patterns
- **Behavioral**  
  - Strategy Pattern (e.g., Payment processing strategies)  
  - Observer Pattern (e.g., Stock price updates)  

- **Creational**  
  - Singleton Pattern (e.g., Configuration manager)  
  - Factory Pattern (e.g., Shape factory)  

- **Structural**  
  - Adapter Pattern (e.g., Legacy system integration)  
  - Decorator Pattern (e.g., Text formatting options)  

---

## Exercise 2 – Mini Project (Problem 5)

### Problem Statement
**Problem 5: Real-time Chat Application**  
Create a console-based chat system where users can create or join chat rooms, exchange messages, and view active users.  

### Features
- Create or join chat rooms using a unique ID  
- Send and receive messages in real-time (simulated in console)  
- Display list of active users in a chat room  
- Private messaging (optional, implemented)  
- Message history persists when user leaves and rejoins  

### Design Patterns Applied
- **Observer Pattern (Behavioral)**  
  - Chat room notifies all connected users when a new message arrives.  

- **Singleton Pattern (Creational)**  
  - `ChatRoomManager` ensures only one global manager exists to handle all chat rooms.  

- **Adapter Pattern (Structural)**  
  - Abstracts client communication via `ClientAdapter`.  
  - Implementations: `ConsoleClientAdapter` (working), `WebSocketClientAdapter` (stub/demo).  

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
    ├── core/                       # (Behavioral: Observer)
    │   ├── MessageListener.java
    │   └── ChatRoom.java
    │
    ├── manager/                    # (Creational: Singleton)
    │   └── ChatRoomManager.java
    │
    ├── client/                     # (Structural: Adapter)
    │   ├── ClientAdapter.java
    │   ├── ConsoleClientAdapter.java
    │   └── WebSocketClientAdapter.java

### How to Run
1. Clone the repo.  
2. Open in IntelliJ IDEA (or any Java IDE).  
3. Run `ChatApp` (console main class).  
4. Use menu options to create/join chat rooms and chat interactively.  

---

## Evaluation Criteria Addressed
- **Code Quality**: SOLID principles, design patterns, modular structure  
- **Functionality**: Meets all required chat features  
- **Global Conventions**: Standard naming & folder organization  
- **Gold Standards**: Logging placeholders, defensive programming, exception handling  
- **Code Walkthrough Ready**: Each design decision can be explained  

---

## Notes
- This submission corresponds to **Exercise 2, Problem 5: Real-time Chat Application**.  
- Exercise 1 and Exercise 2 are both included in the same repo, separated by packages.  
- No external frameworks/libraries (pure Java console app).
