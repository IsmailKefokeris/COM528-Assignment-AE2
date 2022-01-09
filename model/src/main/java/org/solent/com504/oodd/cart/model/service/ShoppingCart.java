/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.cart.model.service;

import org.solent.com504.oodd.cart.model.dto.ShoppingItem;
import java.util.List;

/**
 *
 * @author cgallen
 */
public interface ShoppingCart {

    /**
     *
     * @return
     */
    public List<ShoppingItem> getShoppingCartItems();
    
    /**
     *
     * @param shoppingItem
     */
    public void addItemToCart(ShoppingItem shoppingItem);
    
    /**
     *
     * @param itemUuid
     */
    public void removeItemFromCart(String itemUuid);
    
    /**
     *
     * @return
     */
    public double getTotal();
    
}
