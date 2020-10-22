package com.akdogan.creditcard;

class CreditCardType {
    private CreditCardIssuer issuer;
    private int length;
    private NumberRange[] issuerRange;

    CreditCardType(CreditCardIssuer issuer, int length,  NumberRange[] issuerRange){
        this.issuer = issuer;
        this.length = length;
        this.issuerRange = issuerRange;
    }

    CreditCardIssuer getIssuer() {
        return issuer;
    }


    NumberRange getNumberRange(int index){
        return this.issuerRange[index];
    }

    int getRanges(){
        return this.issuerRange.length;
    }


    public int getLength() {
        return length;
    }
}
