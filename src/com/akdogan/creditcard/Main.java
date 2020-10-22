package com.akdogan.creditcard;

public class Main {

    public static void main(String[] args) {
        // Checking if the Cardnumber is valid
        System.out.println(CreditCardVerification.isValid(4988080000000000L));
        // Get the issuer of the provided cardnumber
        CreditCardIssuer issuer = CreditCardVerification.calculateIssuer(4988080000000000L);
        System.out.println(issuer);
        // Check if the provided cardnumber and issuer match
        System.out.println(CreditCardVerification.checkIssuer(4988080000000000L, issuer));

    }
}
