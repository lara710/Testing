package com.banking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTest {

    @Test
    void testFullDepositFlow() {
        Account account = new Account(100, "Verified");
        ClientController controller = new ClientController();

        String response = controller.handleTransactionRequest(account, "DEPOSIT", 200);

        assertEquals("Success: DEPOSIT of 200.0 completed.", response);
        assertEquals(300, account.getBalance());
    }

    @Test
    void testWithdrawBlockedWhenSuspended() {
        Account account = new Account(500, "Suspended");
        ClientController controller = new ClientController();

        String response = controller.handleTransactionRequest(account, "WITHDRAW", 100);

        assertTrue(response.contains("Failure"));
        assertEquals(500, account.getBalance());
    }
    @Test
    void testTransferThroughController() {
        Account from = new Account(500, "Verified");
        Account to = new Account(200, "Verified");
        ClientController controller = new ClientController();

        String response = controller.handleTransferRequest(from, to, 150);

        assertEquals("Success: Transfer of 150.0 completed.", response);
        assertEquals(350, from.getBalance());
        assertEquals(350, to.getBalance());
    }

    @Test
    void testTransferBlockedWhenSuspended() {
        Account from = new Account(500, "Suspended");
        Account to = new Account(200, "Verified");
        ClientController controller = new ClientController();

        String response = controller.handleTransferRequest(from, to, 100);

        assertTrue(response.contains("Failure"));
        assertEquals(500, from.getBalance());
        assertEquals(200, to.getBalance());
    }
}
