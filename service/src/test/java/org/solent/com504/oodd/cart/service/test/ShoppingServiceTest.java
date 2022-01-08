/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.cart.service.test;

import java.util.UUID;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.solent.com504.oodd.cart.dao.impl.ShoppingItemCatalogRepository;
import org.solent.com504.oodd.cart.model.dto.ShoppingItem;
import org.solent.com504.oodd.cart.model.service.ShoppingService;
import org.solent.com504.oodd.cart.service.ServiceObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author cgallen
 */
public class ShoppingServiceTest {
    
    @Autowired
    private ShoppingItemCatalogRepository shoppingItemCatalogRepository;
    
    ShoppingService shoppingService = null;
    
    @Test
    public void test1() {
        assertNotNull(shoppingService);
    }
    
    @Test
    public void testFindingItemByName() {
        
        shoppingItemCatalogRepository.deleteAll();
        
        String uuid = UUID.randomUUID().toString();
        
        ShoppingItem house = new ShoppingItem();
        house.setName("house");
        house.setPrice(20000.00);
        house.setQuantity(0);
        house.setUuid(uuid);
        shoppingItemCatalogRepository.save(house);
        
        
        ShoppingItem hen = new ShoppingItem();
        hen.setName("hen");
        hen.setPrice(25.00);
        hen.setQuantity(0);
        hen.setUuid(UUID.randomUUID().toString());
        shoppingItemCatalogRepository.save(hen);
        
        ShoppingItem item1 = shoppingService.getNewItemByName(uuid);
        
        assertEquals(house, item1);
        
        
        shoppingItemCatalogRepository.deleteAll();
    }
}
