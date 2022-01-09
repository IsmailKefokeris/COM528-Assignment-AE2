/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.cart.spring.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.oodd.bank.model.dto.CreditCard;
import org.solent.com504.oodd.cart.dao.impl.InvoiceRepository;
import org.solent.com504.oodd.cart.dao.impl.ShoppingItemCatalogRepository;
import org.solent.com504.oodd.cart.dao.impl.UserRepository;
import org.solent.com504.oodd.cart.model.dto.Invoice;
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

    //    ADMIN SECTION
    private static final String DEFAULT_ADMIN_USERNAME = "globaladmin";
    private static final String DEFAULT_ADMIN_PASSWORD = "globaladmin";
    
    private static final String DEFAULT_ADMIN_CARDNUM = "4285860000000021";
    private static final String DEFAULT_ADMIN_ENDDATE = "11/21";
    private static final String DEFAULT_ADMIN_CVV = "123";
    private static final String DEFAULT_ADMIN_ISSUENUM = "01";
    
    //    USER SECTION
    private static final String DEFAULT_USER_CARDNUM = "5133880000000012";
    private static final String DEFAULT_USER_ENDDATE = "11/21";
    private static final String DEFAULT_USER_CVV = "123";
    private static final String DEFAULT_USER_ISSUENUM = "01";

    private static final String DEFAULT_USER_PASSWORD = "user1234";
    private static final String DEFAULT_USER_USERNAME = "user1234";

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private InvoiceRepository invoiceRepository;
    
    @Autowired
    private ShoppingItemCatalogRepository shoppingItemCatalogRepository;

    /**
     *
     */
    @PostConstruct
    public void initDatabase() {
        LOG.debug("initialising database with startup values");

        
        // initialising admin and normal user if dont exist
        User adminUser = new User();
        adminUser.setUsername(DEFAULT_ADMIN_USERNAME);
        adminUser.setFirstName("default administrator");
        adminUser.setPassword(DEFAULT_ADMIN_PASSWORD);
        adminUser.setUserRole(UserRole.ADMINISTRATOR);
        
        // Initialising default admin card details
        CreditCard adminCardDetails = new CreditCard();
        adminCardDetails.setName(DEFAULT_ADMIN_USERNAME);
        adminCardDetails.setCardnumber(DEFAULT_ADMIN_CARDNUM);
        adminCardDetails.setEndDate(DEFAULT_ADMIN_ENDDATE);
        adminCardDetails.setCvv(DEFAULT_ADMIN_CVV);
        adminCardDetails.setIssueNumber(DEFAULT_ADMIN_ISSUENUM);
        adminUser.setCardDetails(adminCardDetails);

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
        
        // Initialising default user card details
        CreditCard userCardDetails = new CreditCard();
        userCardDetails.setName(DEFAULT_USER_USERNAME);
        userCardDetails.setCardnumber(DEFAULT_USER_CARDNUM);
        adminCardDetails.setEndDate(DEFAULT_USER_ENDDATE);
        userCardDetails.setCvv(DEFAULT_USER_CVV);
        userCardDetails.setIssueNumber(DEFAULT_USER_ISSUENUM);
        defaultUser.setCardDetails(userCardDetails);

        users = userRepository.findByUsername(DEFAULT_USER_USERNAME);
        if (users.isEmpty()) {
            userRepository.save(defaultUser);
            LOG.info("creating new default user:" + defaultUser);
        } else {
            LOG.info("defaultuser already exists. Not creating new :" + defaultUser);
        }
        
//        Adding Items
        List<ShoppingItem> itemList = Arrays.asList(
                new ShoppingItem("house", 20000.00),
                new ShoppingItem("hen", 5.00),
                new ShoppingItem("car", 5800.70),
                new ShoppingItem("wheel", 25.00),
                new ShoppingItem("door", 128.54),
                new ShoppingItem("bowl", 2.50),
                new ShoppingItem("ball", 10.00),
                new ShoppingItem("pet alligator", 65.00)
        );
        
        Double shoppingcartTotal = 0.0;
        
        for (ShoppingItem item : itemList) {
            shoppingItemCatalogRepository.save(item);
        }
        
        
        Invoice invoice1 = new Invoice();
        invoice1.setAmountDue(shoppingcartTotal);
        invoice1.setDateOfPurchase(new Date());
        invoice1.setPurchaser(defaultUser);
        invoice1.setPurchasedItems(itemList);
        invoiceRepository.save(invoice1);

        LOG.debug("database initialised");
    }
}
