package com.banking;

public class CreditService {
    
    private static final int MIN_SCORE = 700;

    public boolean checkEligibility(int creditScore) {
        if (creditScore < 0 || creditScore > 850) {
            throw new IllegalArgumentException("Invalid credit score range");
        }
        return creditScore >= MIN_SCORE;
    }
}
