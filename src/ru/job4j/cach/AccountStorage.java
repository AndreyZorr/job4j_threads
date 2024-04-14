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
        if (getById(fromId).isEmpty() || getById(toId).isEmpty()) {
            return false;
        }
        if (accounts.get(fromId).amount() < amount) {
            return false;
        }
        accounts.put(fromId, new Account(fromId, accounts.get(fromId).amount() - amount));
        accounts.put(toId, new Account(toId, accounts.get(toId).amount() + amount));
        return true;

    }

}
