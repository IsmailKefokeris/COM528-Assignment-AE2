/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.cart.model.service;

import org.solent.com504.oodd.cart.model.dto.ShoppingItem;
import java.util.List;
import org.solent.com504.oodd.cart.model.dto.CardDetails;
import org.solent.com504.oodd.cart.model.dto.User;

/**
 *
 * @author cgallen
 */
public interface ShoppingService {
    
        public List<ShoppingItem> getAvailableItems();
        
        public boolean purchaseItems(ShoppingCart shoppingCart,User user,CardDetails purchaserCard, CardDetails sellerCard);
        
        public ShoppingItem getNewItemByName(String uuid);

}
