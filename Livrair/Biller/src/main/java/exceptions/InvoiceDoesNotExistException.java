package exceptions;

public class InvoiceDoesNotExistException extends Exception {
    public InvoiceDoesNotExistException() {
        System.out.println("this invoice does not exist");
    }
}
