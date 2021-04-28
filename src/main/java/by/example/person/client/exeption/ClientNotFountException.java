package by.example.person.client.exeption;


public class ClientNotFountException extends RuntimeException {

    public ClientNotFountException(String message) {
        super(message);
    }
}
