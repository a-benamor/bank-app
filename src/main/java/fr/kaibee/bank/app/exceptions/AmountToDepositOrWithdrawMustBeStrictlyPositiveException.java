package fr.kaibee.bank.app.exceptions;

public class AmountToDepositOrWithdrawMustBeStrictlyPositiveException extends RuntimeException{
    public AmountToDepositOrWithdrawMustBeStrictlyPositiveException(String message) {
        super(message);
    }
}
