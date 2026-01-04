package com.banking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccountBlackBoxTest {

    // BB01: Negative Deposit
    @Test
    void testDepositNegativeAmount() {
        Account account = new Account(100.0, "Verified");
        boolean result = account.deposit(-100);
        assertFalse(result, "Deposit of negative amount should return false");
        assertEquals(100.0, account.getBalance(), "Balance should remain unchanged");
    }

    // BB02: Valid Withdraw
    @Test
    void testWithdrawValidAmount() {
        Account account = new Account(100.0, "Verified");
        boolean result = account.withdraw(50);
        assertTrue(result, "Valid withdrawal should return true");
        assertEquals(50.0, account.getBalance(), "Balance should decrease by 50");
    }

    // BB03: Overdraft Prevention
    @Test
    void testWithdrawOverdraft() {
        Account account = new Account(100.0, "Verified");
        boolean result = account.withdraw(500);
        assertFalse(result, "Withdrawal exceeding balance should return false");
        assertEquals(100.0, account.getBalance(), "Balance should remain unchanged");
    }

    // BB04: Deposit in Closed State
    @Test
    void testDepositClosedAccount() {
        Account account = new Account(100.0, "Closed");
        boolean result = account.deposit(100);
        assertFalse(result, "Deposit to closed account should return false");
        assertEquals(100.0, account.getBalance(), "Balance should remain unchanged");
    }

    // BB05: Withdraw in Suspended State
    @Test
    void testWithdrawSuspendedAccount() {
        Account account = new Account(100.0, "Suspended");
        boolean result = account.withdraw(50);
        assertFalse(result, "Withdrawal from suspended account should return false");
        assertEquals(100.0, account.getBalance(), "Balance should remain unchanged");
    }

    // BVA-D-03: Just above zero deposit
    @Test
    void testDepositBoundaryPositive() {
        Account account = new Account(0.0, "Verified");
        boolean result = account.deposit(0.01);
        assertTrue(result);
        assertEquals(0.01, account.getBalance(), 0.0001);
    }
    
    // BVA-W-02: Exact balance withdraw
    @Test
    void testWithdrawExactBalance() {
        Account account = new Account(100.0, "Verified");
        boolean result = account.withdraw(100.0);
        assertTrue(result);
        assertEquals(0.0, account.getBalance(), 0.0001);
    }
}
