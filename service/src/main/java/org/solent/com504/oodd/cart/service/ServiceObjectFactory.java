/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.cart.service;

import org.solent.com504.oodd.bank.client.impl.BankRestClientImpl;
import org.solent.com504.oodd.cart.model.service.ShoppingCart;
import org.solent.com504.oodd.cart.model.service.ShoppingService;

/**
 *
 * @author cgallen
 */
public class ServiceObjectFactory {
    
    static ShoppingService shoppingService = new ShoppingServiceImpl();
    
    static BankRestClientImpl bankClient = new BankRestClientImpl("http://com528bank.ukwest.cloudapp.azure.com:8080/bank/rest");
    
    // cannot instantiate
    private ServiceObjectFactory(){
        
    }
    
    /**
     *
     * @param bankClient
     */
    public static void setBankClient(BankRestClientImpl bankClient) {
        ServiceObjectFactory.bankClient = bankClient;
    }

    /**
     *
     * @return
     */
    public static BankRestClientImpl getBankClient() {
        return bankClient;
    }
    
    /**
     *
     * @return
     */
    public static ShoppingService getShoppingService(){
        return shoppingService;
    }
    
    /**
     *
     * @return
     */
    public static ShoppingCart getNewShoppingCart(){
        return new ShoppingCartImpl();
    }
    
}
