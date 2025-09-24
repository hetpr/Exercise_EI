package assignment.patterns.creational.factory;

public class FactoryDemo {
    public static void main(String[] args) {
        Vehicle v1 = VehicleFactory.getVehicle("car");
        v1.drive();

        Vehicle v2 = VehicleFactory.getVehicle("bike");
        v2.drive();

        Vehicle v3 = VehicleFactory.getVehicle("truck");
        v3.drive();
    }
}