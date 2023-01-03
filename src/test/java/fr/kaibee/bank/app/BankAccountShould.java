package fr.kaibee.bank.app;

import fr.kaibee.bank.app.exceptions.AmountToDepositMustBeStrictlyPositiveException;
import fr.kaibee.bank.app.valueobjects.AccountId;
import fr.kaibee.bank.app.valueobjects.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class BankAccountShould {

    private AccountId accountId;

    private Account account;

    private Money initialBalance;

    @BeforeEach
    void setUp() {
        accountId = new AccountId(UUID.randomUUID());
        initialBalance = new Money(100.20);
        account = new Account(accountId, initialBalance);
    }

    @Test
    void works() {
        org.assertj.core.api.Assertions.assertThat(true).isTrue();
    }

    @Test
    void have_the_right_balance_after_deposit_operation() {
        Money moneyToDeposit = new Money(100.5);

        account.depositMoney(moneyToDeposit);

        Assertions.assertEquals(new Money(200.70), account.getBalance());
    }

    @Test
    void throw_an_exception_when_money_to_deposit_is_negative(){
        Money negativeAmount = new Money(-50);

        AmountToDepositMustBeStrictlyPositiveException amountToDepositMustBeStrictlyPositiveException = Assertions.assertThrows(
                AmountToDepositMustBeStrictlyPositiveException.class,
                () -> account.depositMoney(negativeAmount));

        Assertions.assertEquals("The amount to deposit must be strictly positive", amountToDepositMustBeStrictlyPositiveException.getMessage());
    }

    @Test
    void throw_an_exception_when_money_to_deposit_is_zero(){
        Money zeroAmount = new Money(0);

        AmountToDepositMustBeStrictlyPositiveException amountToDepositMustBeStrictlyPositiveException = Assertions.assertThrows(
                AmountToDepositMustBeStrictlyPositiveException.class,
                () -> account.depositMoney(zeroAmount));

        Assertions.assertEquals("The amount to deposit must be strictly positive", amountToDepositMustBeStrictlyPositiveException.getMessage());
    }

}
