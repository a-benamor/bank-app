package fr.kaibee.bank.app.exceptions;

public class AmountToDepositMustBeStrictlyPositiveException extends RuntimeException{
    public AmountToDepositMustBeStrictlyPositiveException(String message) {
        super(message);
    }
}
