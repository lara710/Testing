package com.banking;

public class TransactionProcessor {

    public boolean processTransaction(Account account, String type, double amount) {

        if (account == null || type == null || account.getStatus() == "Unverified") {
            return false;
        }

        if (type.equalsIgnoreCase("DEPOSIT")) {
            return account.deposit(amount);
        }

        if (type.equalsIgnoreCase("WITHDRAW")) {
            return account.withdraw(amount);
        }

        return false;
    }
}
