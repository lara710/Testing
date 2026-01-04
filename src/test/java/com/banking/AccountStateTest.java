package com.banking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccountStateTest {

    // ST-01: Closed -> Deposit -> Fail
    @Test
    void testDepositInClosedState() {
        Account account = new Account(0, "Closed");
        assertFalse(account.deposit(100));
    }

    // ST-02: Suspended -> Withdraw -> Fail
    @Test
    void testWithdrawInSuspendedState() {
        Account account = new Account(100, "Suspended");
        assertFalse(account.withdraw(50));
    }

    // ST-03: Verified -> Full Access
    @Test
    void testNormalOperations() {
        Account account = new Account(100, "Verified");
        assertTrue(account.deposit(50));
        assertTrue(account.withdraw(50));
    }

    // ST-04: Verify
    @Test
    void testVerify(){
        Account account = new Account(100,"Unverified");
        AccountService service = new AccountService();
        service.verifyAccount(account);
        assertEquals("Verified", account.getStatus());
        assertTrue(account.withdraw(10));
    }

    // ST-05: Verified -> Suspended
    @Test
    void testTransitionVerifiedToSuspended() {
        Account account = new Account(100, "Verified");
        AccountService service = new AccountService();
        service.suspendAccount(account);
        assertEquals("Suspended", account.getStatus());
        // Verify constraint applied immediately
        assertFalse(account.withdraw(10));
    }
    
    // ST-06: Suspended -> Verified (Appeal)
    @Test
    void testTransitionSuspendedToVerified() {
        Account account = new Account(100, "Suspended");
        AccountService service = new AccountService();
        service.openFromAppeal(account);
        assertEquals("Verified", account.getStatus());
        // verify account works
        assertTrue(account.withdraw(10));
    }

    // ST-07: Suspended -> Closed
    @Test
    void testTransitionSuspendedToClosed() {
        Account account = new Account(100, "Suspended");
        AccountService service = new AccountService();
        service.closeAccount(account);
        assertEquals("Closed", account.getStatus());
        assertFalse(account.withdraw(10));
    }
    // ST-08: Verified -> Closed
    @Test
    void testTransitionVerifiedToClosed() {
        Account account = new Account(100, "Suspended");
        AccountService service = new AccountService();
        service.closeAccount(account);
        assertEquals("Closed", account.getStatus());
        assertFalse(account.withdraw(10));
    }
}
