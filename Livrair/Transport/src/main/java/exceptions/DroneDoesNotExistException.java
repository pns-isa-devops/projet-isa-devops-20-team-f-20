package exceptions;

public class DroneDoesNotExistException extends Throwable {

    public DroneDoesNotExistException() {
        System.out.println("A drone doesnt exist");
    }

}
