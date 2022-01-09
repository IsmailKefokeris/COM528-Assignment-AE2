/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.cart.model.service;

import org.solent.com504.oodd.cart.model.dto.ShoppingItem;
import java.util.List;
import org.solent.com504.oodd.bank.model.dto.CreditCard;
import org.solent.com504.oodd.cart.model.dto.User;

/**
 *
 * @author cgallen
 */
public interface ShoppingService {
    
    /**
     *
     * @return
     */
    public List<ShoppingItem> getAvailableItems();
        
    /**
     *
     * @param shoppingCart
     * @param user
     * @param purchaserCard
     * @param sellerCard
     * @return
     */
    public boolean purchaseItems(ShoppingCart shoppingCart,User user,CreditCard purchaserCard, CreditCard sellerCard);
        
    /**
     *
     * @param uuid
     * @return
     */
    public ShoppingItem getNewItemByName(String uuid);

}
