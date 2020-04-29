package exceptions;

public class DroneAlreadyExistsException extends Exception {

    public DroneAlreadyExistsException() {
        System.out.println("A drone already exists for given Id");
    }
}
