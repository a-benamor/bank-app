package fr.kaibee.bank.app.valueobjects;

import java.time.ZonedDateTime;
import java.util.Objects;

public class Operation {
    private final OperationId operationId;
    private final Money balance;
    private final Money operationAmount;
    private final OperationType operationType;
    private final ZonedDateTime operationDate;

    private Operation(Builder builder) {
        operationId = builder.operationId;
        balance = builder.balance;
        operationAmount = builder.operationAmount;
        operationType = builder.operationType;
        operationDate = builder.operationDate;
    }


    public OperationId getOperationId() {
        return operationId;
    }

    public Money getBalance() {
        return balance;
    }

    public Money getOperationAmount() {
        return operationAmount;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public ZonedDateTime getOperationDate() {
        return operationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return operationId.equals(operation.operationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operationId);
    }

    public static final class Builder {
        private OperationId operationId;
        private Money balance;
        private Money operationAmount;
        private OperationType operationType;
        private ZonedDateTime operationDate;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder operationId(OperationId val) {
            operationId = val;
            return this;
        }

        public Builder balance(Money val) {
            balance = val;
            return this;
        }

        public Builder operationAmount(Money val) {
            operationAmount = val;
            return this;
        }

        public Builder operationType(OperationType val) {
            operationType = val;
            return this;
        }

        public Builder operationDate(ZonedDateTime val) {
            operationDate = val;
            return this;
        }

        public Operation build() {
            return new Operation(this);
        }
    }
}
