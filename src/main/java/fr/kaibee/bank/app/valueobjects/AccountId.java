package fr.kaibee.bank.app.valueobjects;

import java.util.UUID;

public class AccountId extends BaseId<UUID>{
    public AccountId(UUID value) {
        super(value);
    }
}
