/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.bank.model.dto;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ismai
 */
public class CreditCardTest {
    
    /**
     *
     */
    public CreditCardTest() {
    }
    
    /**
     *
     */
    @BeforeClass
    public static void setUpClass() {
    }
    
    /**
     *
     */
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     *
     */
    @Before
    public void setUp() {
    }
    
    /**
     *
     */
    @After
    public void tearDown() {
    }

    /**
     * Test of getName method, of class CreditCard.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String name = "barnie";
        CreditCard instance = new CreditCard();
        instance.setName(name);
        String result = instance.getName();
        assertEquals(name, result);
    }

    /**
     * Test of setName method, of class CreditCard.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "barnie";
        CreditCard instance = new CreditCard();
        instance.setName(name);
        assertEquals(instance.getName(), name);
    }

    /**
     * Test of getEndDate method, of class CreditCard.
     */
    @Test
    public void testGetEndDate() {
        System.out.println("getEndDate");
        String date = "11/21";
        CreditCard instance = new CreditCard();
        instance.setEndDate(date);
        assertEquals(date, instance.getEndDate());
    }

    /**
     * Test of setEndDate method, of class CreditCard.
     */
    @Test
    public void testSetEndDate() {
        System.out.println("setEndDate");
        String date = "11/21";
        CreditCard instance = new CreditCard();
        instance.setEndDate(date);
        assertEquals(date, instance.getEndDate());
        
    }

    /**
     * Test of getCardnumber method, of class CreditCard.
     */
    @Test
    public void testGetCardnumber() {
        System.out.println("getCardnumber");
        String cardnum = "1111111111111";
        CreditCard instance = new CreditCard();
        instance.setCardnumber(cardnum);
        assertEquals(cardnum, instance.getCardnumber());
    }

    /**
     * Test of setCardnumber method, of class CreditCard.
     */
    @Test
    public void testSetCardnumber() {
        System.out.println("setCardnumber");
        String cardnum = "1111111111111";
        CreditCard instance = new CreditCard();
        instance.setCardnumber(cardnum);
        assertEquals(cardnum, instance.getCardnumber());
    }

    /**
     * Test of getCvv method, of class CreditCard.
     */
    @Test
    public void testGetCvv() {
        System.out.println("getCvv");
        String cvv = "123";
        CreditCard instance = new CreditCard();
        instance.setCvv(cvv);
        assertEquals(cvv, instance.getCvv());
    }

    /**
     * Test of setCvv method, of class CreditCard.
     */
    @Test
    public void testSetCvv() {
        System.out.println("setCvv");
        String cvv = "123";
        CreditCard instance = new CreditCard();
        instance.setCvv(cvv);
        assertEquals(cvv, instance.getCvv());
    }

    /**
     * Test of getIssueNumber method, of class CreditCard.
     */
    @Test
    public void testGetIssueNumber() {
        System.out.println("getIssueNumber");
        String issueNum = "01";
        CreditCard instance = new CreditCard();
        instance.setCardnumber(issueNum);
        assertEquals(issueNum, instance.getIssueNumber());
    }

    /**
     * Test of setIssueNumber method, of class CreditCard.
     */
    @Test
    public void testSetIssueNumber() {
        System.out.println("setIssueNumber");
        String issueNum = "01";
        CreditCard instance = new CreditCard();
        instance.setCardnumber(issueNum);
        assertEquals(issueNum, instance.getIssueNumber());
    }
}
