package fr.kaibee.bank.app.exceptions;

public class InsufficientAccountBalanceException extends RuntimeException{
    public InsufficientAccountBalanceException(String message) {
        super(message);
    }
}
