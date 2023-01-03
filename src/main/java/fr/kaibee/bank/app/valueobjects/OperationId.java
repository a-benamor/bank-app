package fr.kaibee.bank.app.valueobjects;

import java.util.UUID;

public class OperationId extends BaseId<UUID> {
    public OperationId(UUID value) {
        super(value);
    }
}
