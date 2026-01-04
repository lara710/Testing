package com.banking;

public class AccountService {

    private TransactionProcessor processor;
    private TransferProcessor transfer;

    public AccountService() {
        this.processor = new TransactionProcessor();
        this.transfer= new TransferProcessor();
    }

    public boolean performTransaction(Account account, String type, double amount) {
        return processor.processTransaction(account, type, amount);
    }
    public boolean performTransfer(Account from, Account to, double amount) {
        return transfer.transfer(from, to, amount,processor);
    }

    public void suspendAccount(Account account) {
       if (account.getStatus().equals("Verified")){
        account.setStatus("Suspended"); }
    }

    public void verifyAccount(Account account) {
        if (account.getStatus().equals("Unverified")){
            account.setStatus("Verified"); }
    }

    public void openFromAppeal(Account account){
        if (account.getStatus().equals("Suspended")){
            account.setStatus("Verified");
        }
    }

    public void closeAccount(Account account) {
        if (account.getStatus().equals("Suspended") || account.getStatus().equals("Verified")){
            account.setStatus("Closed");
        }
    }
}
