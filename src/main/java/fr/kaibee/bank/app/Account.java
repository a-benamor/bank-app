package fr.kaibee.bank.app;

import fr.kaibee.bank.app.exceptions.AmountToDepositMustBeStrictlyPositiveException;
import fr.kaibee.bank.app.valueobjects.AccountId;
import fr.kaibee.bank.app.valueobjects.Money;
import fr.kaibee.bank.app.valueobjects.Operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Account {
    private  AccountId accountId;
    private  Money balance;
    private  List<Operation> operations;

    public Account(AccountId accountId, Money balance) {
        this.accountId = accountId;
        this.balance = balance;
        this.operations = new ArrayList<>();
    }

    public void depositMoney(Money moneyToDeposit) {
        if (moneyToDeposit.isLessOrEqualToZero()){
            throw new AmountToDepositMustBeStrictlyPositiveException("The amount to deposit must be strictly positive");
        }

        this.balance = this.balance.add(moneyToDeposit);
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
}
