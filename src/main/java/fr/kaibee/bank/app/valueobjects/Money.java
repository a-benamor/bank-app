package fr.kaibee.bank.app.valueobjects;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Money {

    private final BigDecimal amount;

    private Money(BigDecimal amount) {
        this.amount = amount;
    }

    public Money(double amount) {
        this.amount = setScale(BigDecimal.valueOf(amount));
    }

    public boolean isLessOrEqualToZero(){
        return this.amount.compareTo(BigDecimal.ZERO) <= 0 ;
    }

    public boolean isGreaterThan(Money money) {
        return this.amount.compareTo(money.getAmount())>0;
    }

    public Money add(Money money) {
        BigDecimal totalAmount = this.amount.add(money.getAmount());
        return new Money(setScale(totalAmount));
    }

    public Money subtract(Money money) {
        BigDecimal newAmount = this.amount.subtract(money.getAmount());
        return new Money(setScale(newAmount));
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount.compareTo(money.amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    private BigDecimal setScale(BigDecimal input) {
        return input.setScale(2, RoundingMode.HALF_EVEN);
    }

}
