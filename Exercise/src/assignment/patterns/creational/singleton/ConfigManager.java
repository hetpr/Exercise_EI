package assignment.patterns.creational.singleton;

public class ConfigManager {
    private static ConfigManager instance;

    private ConfigManager() {
        System.out.println("Loading configuration...");
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    public String getDbUrl() {
        return "jdbc:mysql://localhost:3306/mydb";
    }
}