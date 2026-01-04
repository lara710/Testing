package com.banking;

public class ClientController {

    private AccountService accountService;

    public ClientController() {
        this.accountService = new AccountService();
    }

    public String handleTransactionRequest(Account account, String type, double amount) {
        boolean success = accountService.performTransaction(account, type, amount);

        if (success) {
            return "Success: " + type + " of " + amount + " completed.";
        } else {
            return "Failure: Could not process " + type;
        }
    }
    public String handleTransferRequest(Account from, Account to, double amount) {
        boolean success = accountService.performTransfer(from, to, amount);

        if (success) {
            return "Success: Transfer of " + amount + " completed.";
        } else {
            return "Failure: Transfer could not be completed.";
        }
    }

}
