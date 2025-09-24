package assignment.patterns.creational.singleton;

public class SingletonDemo {
    public static void main(String[] args) {
        ConfigManager config1 = ConfigManager.getInstance();
        ConfigManager config2 = ConfigManager.getInstance();

        System.out.println("DB URL: " + config1.getDbUrl());
        System.out.println("Same instance? " + (config1 == config2));
    }
}