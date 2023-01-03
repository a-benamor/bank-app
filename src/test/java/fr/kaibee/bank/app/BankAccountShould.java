package fr.kaibee.bank.app;

import fr.kaibee.bank.app.exceptions.AmountToDepositOrWithdrawMustBeStrictlyPositiveException;
import fr.kaibee.bank.app.exceptions.InsufficientAccountBalanceException;
import fr.kaibee.bank.app.valueobjects.AccountId;
import fr.kaibee.bank.app.valueobjects.Money;
import fr.kaibee.bank.app.valueobjects.Operation;
import fr.kaibee.bank.app.valueobjects.OperationType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BankAccountShould {
    private static final String UTC = "UTC";

    private AccountId accountId;
    private Account account;
    private Money initialBalance;
    private Clock fixedClock;

    @BeforeEach
    void setUp() {
        accountId = new AccountId(UUID.randomUUID());
        initialBalance = new Money(100.20);
        fixedClock = Clock.fixed(Instant.parse("2023-01-03T10:15:30.00Z"), ZoneId.of(UTC));
        account = new Account(accountId, initialBalance, fixedClock);
    }

    @Test
    void works() {
        org.assertj.core.api.Assertions.assertThat(true).isTrue();
    }

    @Test
    void have_the_right_account_information_after_deposit_operation() {
        Money moneyToDeposit = new Money(100.5);

        account.depositMoney(moneyToDeposit);

        Money expectedAccountBalance =new Money(200.70);
        Assertions.assertEquals(expectedAccountBalance, account.getBalance());
        Assertions.assertEquals(1, account.getOperations().size());

        Operation depositOp = account.getOperations().get(0);
        Assertions.assertEquals(OperationType.DEPOSIT, depositOp.getOperationType());
        Assertions.assertEquals(expectedAccountBalance, depositOp.getBalance());
        Assertions.assertEquals(moneyToDeposit, depositOp.getOperationAmount());
        Assertions.assertEquals(fixedClock.instant().atZone(ZoneId.of(UTC)), depositOp.getOperationDate());
    }

    @Test
    void throw_an_exception_when_money_to_deposit_is_negative(){
        Money negativeAmount = new Money(-50);

        AmountToDepositOrWithdrawMustBeStrictlyPositiveException amountToDepositMustBeStrictlyPositiveException = Assertions.assertThrows(
                AmountToDepositOrWithdrawMustBeStrictlyPositiveException.class,
                () -> account.depositMoney(negativeAmount));

        Assertions.assertEquals("The amount to deposit must be strictly positive", amountToDepositMustBeStrictlyPositiveException.getMessage());
        assertEquals(initialBalance, account.getBalance());
        assertTrue(account.getOperations().isEmpty());
    }

    @Test
    void throw_an_exception_when_money_to_deposit_is_zero(){
        Money zeroAmount = new Money(0);

        AmountToDepositOrWithdrawMustBeStrictlyPositiveException amountToDepositMustBeStrictlyPositiveException = Assertions.assertThrows(
                AmountToDepositOrWithdrawMustBeStrictlyPositiveException.class,
                () -> account.depositMoney(zeroAmount));

        Assertions.assertEquals("The amount to deposit must be strictly positive", amountToDepositMustBeStrictlyPositiveException.getMessage());
        assertEquals(initialBalance, account.getBalance());
        assertTrue(account.getOperations().isEmpty());
    }

    @Test
    void have_the_right_account_information_after_withdraw_operation() {
        Money moneyToWithdraw = new Money(50);

        account.withdrawMoney(moneyToWithdraw);

        Money expectedAccountBalance = new Money(50.20);
        Assertions.assertEquals(expectedAccountBalance, account.getBalance());
        Assertions.assertEquals(1, account.getOperations().size());

        Operation withdrawalOp = account.getOperations().get(0);
        Assertions.assertEquals(OperationType.WITHDRAWAL, withdrawalOp.getOperationType());
        Assertions.assertEquals(expectedAccountBalance, withdrawalOp.getBalance());
        Assertions.assertEquals(moneyToWithdraw, withdrawalOp.getOperationAmount());
        Assertions.assertEquals(fixedClock.instant().atZone(ZoneId.of(UTC)), withdrawalOp.getOperationDate());
    }

    @Test
    void throw_an_exception_when_money_to_withdraw_is_negative() {
        Money negativeAmount = new Money(-50);

        AmountToDepositOrWithdrawMustBeStrictlyPositiveException amountToWithdrawMustBeStrictlyPositiveException = Assertions.assertThrows(
                AmountToDepositOrWithdrawMustBeStrictlyPositiveException.class,
                () -> account.withdrawMoney(negativeAmount));

        assertEquals("The amount to withdraw must be strictly positive", amountToWithdrawMustBeStrictlyPositiveException.getMessage());
        assertEquals(initialBalance, account.getBalance());
        assertTrue(account.getOperations().isEmpty());
    }

    @Test
    void throw_an_exception_when_account_balance_is_insufficient_to_make_a_withdrawal() {
        Money amountToWithdraw = new Money(250);

        InsufficientAccountBalanceException insufficientAccountBalanceException= Assertions.assertThrows(
                InsufficientAccountBalanceException.class,
                () -> account.withdrawMoney(amountToWithdraw));

        assertEquals("The account balance is insufficient to make the withdrawal", insufficientAccountBalanceException.getMessage());
        assertEquals(initialBalance, account.getBalance());
        assertTrue(account.getOperations().isEmpty());
    }

    @Test
    void let_customer_withdraw_all_its_saving() {
        account.withdrawMoney(initialBalance);

        Money expectedAccountBalance = new Money(0);
        Assertions.assertEquals(expectedAccountBalance, account.getBalance());

        Operation withdrawalOp = account.getOperations().get(0);
        Assertions.assertEquals(OperationType.WITHDRAWAL, withdrawalOp.getOperationType());
        Assertions.assertEquals(expectedAccountBalance, withdrawalOp.getBalance());
        Assertions.assertEquals(initialBalance, withdrawalOp.getOperationAmount());
        Assertions.assertEquals(fixedClock.instant().atZone(ZoneId.of(UTC)), withdrawalOp.getOperationDate());
    }

}
