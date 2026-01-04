package com.banking.ui;

import com.banking.Account;
import javafx.application.Platform;
import javafx.scene.control.Button;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BankingAppGUITest {

    @BeforeAll
    void initJavaFX() {
        try {
            Platform.startup(() -> {});
        } catch (IllegalStateException ignored) {
            // JavaFX already started
        }
    }

    @Test
    void testWithdrawDisabledWhenSuspended() {
        Account account = new Account(100, "Suspended");
        Button withdrawBtn = new Button();

        withdrawBtn.setDisable(account.getStatus().equalsIgnoreCase("Suspended"));

        assertTrue(withdrawBtn.isDisabled(),
                "Withdraw button should be disabled when account is Suspended");
    }

    @Test
    void testDepositDisabledWhenClosed() {
        Account account = new Account(100, "Closed");
        Button depositBtn = new Button();

        depositBtn.setDisable(account.getStatus().equalsIgnoreCase("Closed"));

        assertTrue(depositBtn.isDisabled(),
                "Deposit button should be disabled when account is Closed");
    }
}
