package ru.job4j.cach;

import java.util.HashMap;
import java.util.Optional;

public class AccountStorage {
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        return accounts.putIfAbsent(account.id(), account) == null;
    }

    public synchronized boolean update(Account account) {
        return accounts.replace(account.id(), account) != null;
    }

    public synchronized void delete(int id) {
        accounts.remove(id);
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        Optional<Account> getByFromId = getById(fromId);
        Optional<Account> getByToId = getById(toId);
        if (getByFromId.isEmpty() || getByToId.isEmpty()) {
            return false;
        }
        int amountFrom = getByFromId.get().amount();
        int amountTo = getByToId.get().amount();
        if (amountFrom < amount) {
            return false;
        }
        accounts.put(fromId, new Account(fromId, amountFrom - amount));
        accounts.put(toId, new Account(toId, amountTo + amount));
        return true;

    }

}
