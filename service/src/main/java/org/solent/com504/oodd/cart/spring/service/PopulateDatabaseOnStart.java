/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.cart.spring.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.oodd.cart.dao.impl.ShoppingItemCatalogRepository;
import org.solent.com504.oodd.cart.dao.impl.UserRepository;
import org.solent.com504.oodd.cart.model.dto.ShoppingItem;
import org.solent.com504.oodd.cart.model.dto.User;
import org.solent.com504.oodd.cart.model.dto.UserRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author cgallen
 */
@Component
public class PopulateDatabaseOnStart {

    private static final Logger LOG = LogManager.getLogger(PopulateDatabaseOnStart.class);

    private static final String DEFAULT_ADMIN_USERNAME = "globaladmin";
    private static final String DEFAULT_ADMIN_PASSWORD = "globaladmin";

    private static final String DEFAULT_USER_PASSWORD = "user1234";
    private static final String DEFAULT_USER_USERNAME = "user1234";
    
    @Autowired
    private ShoppingItemCatalogRepository shoppingItemCatalogRepository;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void initDatabase() {
        LOG.debug("initialising database with startup values");

        // initialising admin and normal user if dont exist
        User adminUser = new User();
        adminUser.setUsername(DEFAULT_ADMIN_USERNAME);
        adminUser.setFirstName("default administrator");
        adminUser.setPassword(DEFAULT_ADMIN_PASSWORD);
        adminUser.setUserRole(UserRole.ADMINISTRATOR);

        List<User> users = userRepository.findByUsername(DEFAULT_ADMIN_USERNAME);
        if (users.isEmpty()) {
            userRepository.save(adminUser);
            LOG.info("creating new default admin user:" + adminUser);
        } else {
            LOG.info("default admin user already exists. Not creating new :" + adminUser);
        }

        User defaultUser = new User();
        defaultUser.setUsername(DEFAULT_USER_USERNAME);
        defaultUser.setFirstName("default user");
        defaultUser.setPassword(DEFAULT_USER_PASSWORD);
        defaultUser.setUserRole(UserRole.CUSTOMER);

        users = userRepository.findByUsername(DEFAULT_USER_USERNAME);
        if (users.isEmpty()) {
            userRepository.save(defaultUser);
            LOG.info("creating new default user:" + defaultUser);
        } else {
            LOG.info("defaultuser already exists. Not creating new :" + defaultUser);
        }
        
        shoppingItemCatalogRepository.deleteAll();
        
        ShoppingItem house = new ShoppingItem();
        house.setName("house");
        house.setPrice(20000.00);
        house.setQuantity(1);
        house.setUuid(UUID.randomUUID().toString());
        LOG.info("creating new item:" + house);
        shoppingItemCatalogRepository.save(house);
        
        
        ShoppingItem hen = new ShoppingItem();
        hen.setName("hen");
        hen.setPrice(25.00);
        hen.setQuantity(1);
        hen.setUuid(UUID.randomUUID().toString());
        LOG.info("creating new item:" + hen);
        shoppingItemCatalogRepository.save(hen);
        
        
        ShoppingItem car = new ShoppingItem();
        car.setName("car");
        car.setPrice(5000.00);
        car.setQuantity(1);
        car.setUuid(UUID.randomUUID().toString());
        LOG.info("creating new item:" + car);
        shoppingItemCatalogRepository.save(car);
        
        
        ShoppingItem book = new ShoppingItem();
        book.setName("book");
        book.setPrice(10.00);
        book.setQuantity(1);
        book.setUuid(UUID.randomUUID().toString());
        LOG.info("creating new item:" + book);
        shoppingItemCatalogRepository.save(book);
        
        
        ShoppingItem pen = new ShoppingItem();
        pen.setName("pen");
        pen.setPrice(5.00);
        pen.setQuantity(1);
        pen.setUuid(UUID.randomUUID().toString());
        LOG.info("creating new item:" + pen);
        shoppingItemCatalogRepository.save(pen);
        
        
        ShoppingItem alligator = new ShoppingItem();
        alligator.setName("alligator");
        alligator.setPrice(123.42);
        alligator.setQuantity(1);
        alligator.setUuid(UUID.randomUUID().toString());
        LOG.info("creating new item:" + alligator);
        shoppingItemCatalogRepository.save(alligator);
        
        LOG.info("Items Created:" + shoppingItemCatalogRepository.findAll());

        LOG.debug("database initialised");
    }
}
