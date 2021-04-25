package by.example.person.exeption;


public class ClientNotFountException extends RuntimeException {

    public ClientNotFountException(String message) {
        super(message);
    }
}
