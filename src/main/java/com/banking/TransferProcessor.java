package com.banking;

public class TransferProcessor {

    public boolean transfer(Account from, Account to, double amount, TransactionProcessor processor) {

        if (processor.processTransaction(from, "WITHDRAW",amount)){
            return (processor.processTransaction(to,"DEPOSIT",amount));
        }
        return false;
    }
}
