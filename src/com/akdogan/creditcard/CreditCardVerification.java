package com.akdogan.creditcard;

public class CreditCardVerification {
    public static final String VERSION = "1.0.0"; // Version of the Database
    private static boolean debug = false;

    public static final CreditCardType[] cardTypes = new CreditCardType[]{
            new CreditCardType(CreditCardIssuer.MASTERCARD, 16, new NumberRange[]{
               new NumberRange(2221, 2720),
               new NumberRange(51, 54)
            }),
            new CreditCardType(CreditCardIssuer.AMERICAN_EXPRESS, 15, new NumberRange[]{
                    new NumberRange(34, 34),
                    new NumberRange(37, 37)
            }),
            new CreditCardType(CreditCardIssuer.VISA,16, new NumberRange[]{
                    new NumberRange(4, 4)
            } ),
            new CreditCardType(CreditCardIssuer.JCB, 16, new NumberRange[]{
                    new NumberRange(3528, 3589)
            })

    };

    /**
     * Checks if the provided cardNumber matches the provided issuer
     * @param cardNumber cardNumber to check
     * @param issuer Issuer to check against the provided cardnumber. When issuer UNKNOWN is provided, the result will
     *               be true if the cardnumber does not match any issuer in the database
     * @return true, if and only if the issuer matches the cardnumber. False if the cardnumber matches another issuer
     * or if the cardnumber does not match any issuer in the database;
     */
    public static boolean checkIssuer(long cardNumber, CreditCardIssuer issuer){
        boolean positive = false;
        boolean unknown = true;
        int i = 0;
        while (unknown && i < cardTypes.length){
            int j = 0;
            while (unknown && j < cardTypes[i].getRanges()){
                if (checkRange(cardNumber, cardTypes[i].getNumberRange(j) )){
                    unknown = false;
                    if (cardTypes[i].getIssuer() == issuer && cardTypes[i].getLength() == countDigits(cardNumber)){
                        positive = true;
                    }
                }
                else j++;
            }
            i++;
        }
        return (positive || (unknown && issuer == CreditCardIssuer.UNKNOWN ));
    }

    /**
     * Returns the issuer matching the provided cardnumber
     * @param cardNumber cardnumber to check against
     * @return card issuer of the provided card according to the database
     */
    public static CreditCardIssuer calculateIssuer(long cardNumber){
        int i = 0;
        while ( i < cardTypes.length){
            int j = 0;
            while ( j < cardTypes[i].getRanges()){
                if (checkRange(cardNumber, cardTypes[i].getNumberRange(j))){
                    return cardTypes[i].getIssuer();
                }
                else j++;
            }
            i++;
        }
        return CreditCardIssuer.UNKNOWN;
    }

    /**
     * Checks if the checksum of the provided card is valid. This does not mean the card itself is valid
     * @param cardNumber cardnumber to be checked
     * @return true if the checksum of the cardnumber is valid. False otherwise.
     */
    public static boolean isValid(long cardNumber)
    {
        int sum = 0;
        int count = 0;
        while (cardNumber > 0) {
            int digit = (int)(cardNumber % 10);
            count++;
            if (count % 2 == 1) sum += digit;
            else if (digit < 5) sum += 2 * digit;
            else sum += 2 * digit - 9;
            cardNumber /= 10;
        }
        return sum % 10 == 0;
    }

    private static int getFirstDigits(long number, int digits){
        return (int) ( number / Math.pow(10, countDigits(number) - digits));
    }

    private static int countDigits(long number){
        return (int) (Math.log10(number)+1);
    }

    private static boolean checkRange(long number, NumberRange range){
        int issuerNumber = getFirstDigits(number, countDigits(range.getCeiling()));
        return (range.isInRange(issuerNumber) ? true : false);
    }

}
