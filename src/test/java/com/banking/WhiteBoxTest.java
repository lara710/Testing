package com.banking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WhiteBoxTest {

    private TransactionProcessor processor = new TransactionProcessor();
    private Account account = new Account(1000.0, "Verified");

    // WB-T-01: Null account
    @Test
    void testNullAccount() {
        assertFalse(processor.processTransaction(null, "DEPOSIT", 100));
    }

    // WB-T-02: Null type
    @Test
    void testNullType() {
        Account acc = new Account(100, "Verified");
        assertFalse(processor.processTransaction(acc, null, 100));
    }

    // WB-T-03: Unknown type
    @Test
    void testUnknownTypeBranch() {
        Account acc = new Account(100, "Verified");
        assertFalse(processor.processTransaction(acc, "TRANSFER", 50));
    }
    // WB-T-04: Unverified account
    @Test
    void testUnverified() {
        Account acc = new Account(100, "Unverified");
        assertFalse(processor.processTransaction(acc, "DEPOSIT", 50));
    }
    // WB-D-01: Closed account
    @Test
    void testDepositClosed() {
        Account acc = new Account(100, "Closed");
        assertFalse(processor.processTransaction(acc,"DEPOSIT",50));
    }

    // WB-D-02: Negative amount
    @Test
    void testDepositNegative() {
        Account acc = new Account(100, "Verified");
        assertFalse(processor.processTransaction(acc,"DEPOSIT",-10));
    }

    // WB-D-03: Valid deposit
    @Test
    void testDepositSuccess() {
        Account acc = new Account(100, "Verified");
        assertTrue(processor.processTransaction(acc,"DEPOSIT",50));
    }
    // WB-W-01: Closed account
    @Test
    void testWithdrawClosed() {
        Account acc = new Account(100, "Closed");
        assertFalse(processor.processTransaction(acc,"WITHDRAW",10));
    }

    // WB-W-02: Suspended account
    @Test
    void testWithdrawSuspended() {
        Account acc = new Account(100, "Suspended");
        assertFalse(processor.processTransaction(acc,"WITHDRAW",10));
    }

    // WB-W-03: Overdraft
    @Test
    void testWithdrawOverdraft() {
        Account acc = new Account(100, "Verified");
        assertFalse(processor.processTransaction(acc,"WITHDRAW",200));
    }

    // WB-W-04: Valid withdraw
    @Test
    void testWithdrawSuccess() {
        Account acc = new Account(100, "Verified");
        assertTrue(processor.processTransaction(acc,"WITHDRAW",50));
    }
    // WB-TR-01: Successful transfer
    @Test
    void testTransferSuccess() {
        TransactionProcessor processor = new TransactionProcessor();
        TransferProcessor transfer = new TransferProcessor();

        Account from = new Account(200, "Verified");
        Account to = new Account(100, "Verified");

        boolean result = transfer.transfer(from, to, 50, processor);

        assertTrue(result);
        assertEquals(150, from.getBalance());
        assertEquals(150, to.getBalance());
    }

    // WB-TR-02: Transfer fails due to insufficient balance
    @Test
    void testTransferWithdrawFails() {
        TransactionProcessor processor = new TransactionProcessor();
        TransferProcessor transfer = new TransferProcessor();

        Account from = new Account(30, "Verified");
        Account to = new Account(100, "Verified");

        boolean result = transfer.transfer(from, to, 50, processor);

        assertFalse(result);
        assertEquals(30, from.getBalance());
        assertEquals(100, to.getBalance());
    }

}
