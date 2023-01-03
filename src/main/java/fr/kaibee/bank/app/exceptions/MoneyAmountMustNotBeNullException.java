package fr.kaibee.bank.app.exceptions;

public class MoneyAmountMustNotBeNullException extends RuntimeException{
    public MoneyAmountMustNotBeNullException(String message) {
        super(message);
    }
}
