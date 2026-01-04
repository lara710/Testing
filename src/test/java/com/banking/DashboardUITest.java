package com.banking;

import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class DashboardUITest {

    @Test
    void testDashboardHtmlStructure() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("src/main/resources/static/dashboard.html")));

        // Input Validation Logic
        assertTrue(content.contains("if (isNaN(amount) || amount <= 0)"), "Client-side amount validation missing");

        // State Behavior Logic (script check)
        assertTrue(content.contains("if (status === \"Suspended\""), "Suspended state handling missing in script");
        
        // Error Message Logic
        assertTrue(content.contains("Insufficient funds"), "Insufficient funds error message missing");

        // Withdraw button disabled when suspended
        assertTrue(content.contains("withdrawBtn.disabled = true"),
                "Withdraw button is not disabled for Suspended state");

        //  Deposit disabled when Closed
        assertTrue(
                content.contains("btnDeposit") && content.contains("disabled"),
                "Deposit button not disabled in Closed state"
        );


    }
}
