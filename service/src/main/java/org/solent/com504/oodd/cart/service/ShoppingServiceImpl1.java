/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.cart.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.solent.com504.oodd.cart.dao.impl.ShoppingItemCatalogRepository;
import org.solent.com504.oodd.cart.model.service.ShoppingCart;
import org.solent.com504.oodd.cart.model.dto.ShoppingItem;
import org.solent.com504.oodd.cart.model.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author cgallen
 */
@Component
public class ShoppingServiceImpl1 implements ShoppingService {
    
    @Autowired
    private ShoppingItemCatalogRepository shoppingItemCatalogRepository;

    @Override
    public List<ShoppingItem> getAvailableItems() {
        List<ShoppingItem> itemList = shoppingItemCatalogRepository.findAll();
        return itemList;
    }

    @Override
    public boolean purchaseItems(ShoppingCart shoppingCart) {
        System.out.println("purchased items");
        for (ShoppingItem shoppingItem : shoppingCart.getShoppingCartItems()) {
            System.out.println(shoppingItem);
        }

        return true;
    }

    @Override
    public ShoppingItem getNewItemByName(String name) {
        List<ShoppingItem> shoppingItems = shoppingItemCatalogRepository.findAll();
        
        for (ShoppingItem item : shoppingItems) {
            if (item.getName() == name) {
                return item;
            }
        }
        return null;
    }
    
}
