package com.banking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CreditScoreTest {

    // PRE-IMPLEMENTATION: This class would fail to compile before CreditService exists.
    @Test
    void testCreditScoreAboveThreshold() {
        CreditService service = new CreditService();
        assertTrue(service.checkEligibility(750), "Score of 750 should be approved");
    }

    @Test
    void testCreditScoreBelowThreshold() {
        CreditService service = new CreditService();
        assertFalse(service.checkEligibility(500), "Score of 500 should be rejected");
    }

    @Test
    void testCreditScoreBoundary() {
        CreditService service = new CreditService();
        assertTrue(service.checkEligibility(700), "Score of 700 should be approved (inclusive)");
        assertFalse(service.checkEligibility(699), "Score of 699 should be rejected");
    }
}
