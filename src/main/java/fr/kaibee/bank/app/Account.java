package fr.kaibee.bank.app;

import fr.kaibee.bank.app.exceptions.AmountToDepositOrWithdrawMustBeStrictlyPositiveException;
import fr.kaibee.bank.app.exceptions.InsufficientAccountBalanceException;
import fr.kaibee.bank.app.valueobjects.AccountId;
import fr.kaibee.bank.app.valueobjects.Money;
import fr.kaibee.bank.app.valueobjects.Operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Account {
    private static final String DEPOSIT_MESSAGE = "deposit";
    private static final String WITHDRAW_MESSAGE = "withdraw";
    private  AccountId accountId;
    private  Money balance;
    private  List<Operation> operations;

    public Account(AccountId accountId, Money balance) {
        this.accountId = accountId;
        this.balance = balance;
        this.operations = new ArrayList<>();
    }

    public void depositMoney(Money moneyToDeposit) {
        checkIfTheAmountIsPositive(moneyToDeposit, DEPOSIT_MESSAGE);

        this.balance = this.balance.add(moneyToDeposit);
    }

    public void withdrawMoney(Money moneyToWithdraw) {
        checkIfTheAmountIsPositive(moneyToWithdraw, WITHDRAW_MESSAGE);

        checkIfTheAccountHasSufficientBalance(moneyToWithdraw);

        this.balance = this.balance.subtract(moneyToWithdraw);
    }

    public AccountId getAccountId() {
        return accountId;
    }

    public Money getBalance() {
        return balance;
    }

    public List<Operation> getOperations() {
        if (operations == null){
            operations = new ArrayList<>();
        }

        return operations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountId.equals(account.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId);
    }

    private void checkIfTheAmountIsPositive(Money amount, String operationName) {
        if (amount.isLessOrEqualToZero()){
            throw new AmountToDepositOrWithdrawMustBeStrictlyPositiveException(String.format("The amount to %s must be strictly positive", operationName));
        }
    }

    private void checkIfTheAccountHasSufficientBalance(Money moneyToWithdraw) {
        if (moneyToWithdraw.isGreaterThan(this.balance)){
            throw new InsufficientAccountBalanceException("The account balance is insufficient to make the withdrawal");
        }
    }
}
