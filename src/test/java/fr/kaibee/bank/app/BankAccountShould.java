package fr.kaibee.bank.app;

import fr.kaibee.bank.app.exceptions.AmountToDepositOrWithdrawMustBeStrictlyPositiveException;
import fr.kaibee.bank.app.exceptions.InsufficientAccountBalanceException;
import fr.kaibee.bank.app.valueobjects.AccountId;
import fr.kaibee.bank.app.valueobjects.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        AmountToDepositOrWithdrawMustBeStrictlyPositiveException amountToDepositMustBeStrictlyPositiveException = Assertions.assertThrows(
                AmountToDepositOrWithdrawMustBeStrictlyPositiveException.class,
                () -> account.depositMoney(negativeAmount));

        Assertions.assertEquals("The amount to deposit must be strictly positive", amountToDepositMustBeStrictlyPositiveException.getMessage());
    }

    @Test
    void throw_an_exception_when_money_to_deposit_is_zero(){
        Money zeroAmount = new Money(0);

        AmountToDepositOrWithdrawMustBeStrictlyPositiveException amountToDepositMustBeStrictlyPositiveException = Assertions.assertThrows(
                AmountToDepositOrWithdrawMustBeStrictlyPositiveException.class,
                () -> account.depositMoney(zeroAmount));

        Assertions.assertEquals("The amount to deposit must be strictly positive", amountToDepositMustBeStrictlyPositiveException.getMessage());
    }

    @Test
    void have_the_right_account_balance_after_withdraw_operation() {
        Money moneyToWithdraw = new Money(50);

        account.withdrawMoney(moneyToWithdraw);

        Assertions.assertEquals(new Money(50.20), account.getBalance());
    }

    @Test
    void throw_an_exception_when_money_to_withdraw_is_negative() {
        Money negativeAmount = new Money(new BigDecimal(-50));

        AmountToDepositOrWithdrawMustBeStrictlyPositiveException amountToWithdrawMustBeStrictlyPositiveException = Assertions.assertThrows(
                AmountToDepositOrWithdrawMustBeStrictlyPositiveException.class,
                () -> account.withdrawMoney(negativeAmount));

        assertEquals("The amount to withdraw must be strictly positive", amountToWithdrawMustBeStrictlyPositiveException.getMessage());
    }

    @Test
    void throw_an_exception_when_account_balance_is_insufficient_to_make_a_withdrawal() {
        Money amountToWithdraw = new Money(new BigDecimal(250));

        InsufficientAccountBalanceException insufficientAccountBalanceException= Assertions.assertThrows(
                InsufficientAccountBalanceException.class,
                () -> account.withdrawMoney(amountToWithdraw));

        assertEquals("The account balance is insufficient to make the withdrawal", insufficientAccountBalanceException.getMessage());
    }

    @Test
    void let_customer_withdraw_all_its_saving() {
        account.withdrawMoney(initialBalance);

        Assertions.assertEquals(new Money(0), account.getBalance());
    }


}
