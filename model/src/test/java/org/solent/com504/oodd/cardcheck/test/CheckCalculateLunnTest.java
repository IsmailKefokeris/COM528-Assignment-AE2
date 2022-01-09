/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.cardcheck.test;

import org.junit.Test;
import static org.junit.Assert.*;
import org.solent.com504.oodd.cardcheck.CalculateLunnDigit;
import org.solent.com504.oodd.cardcheck.CardValidationResult;
import org.solent.com504.oodd.cardcheck.RegexCardValidator;

/**
 *
 * @author cgallen
 */
public class CheckCalculateLunnTest {
    

    @Test
    public void checkCalculateLunn() {
        
        String pan =  "550000555555555"; // correct mastercard 5500005555555559

        String check = CalculateLunnDigit.calculateCheckDigit(pan);
        
        String ccNumber = pan+check;
        
        CardValidationResult result = RegexCardValidator.isValid(ccNumber);
        
        System.out.println("pan:"+pan
                + " ccNumber with check digit:"+ ccNumber);

        assertTrue(result.isValid());
                
    }
}
