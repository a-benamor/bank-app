package fr.kaibee.bank.app.valueobjects;

import java.util.Objects;

public class Operation {
    private final OperationId operationId;

    public Operation(OperationId operationId) {
        this.operationId = operationId;
    }

    public OperationId getOperationId() {
        return operationId;
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
}
