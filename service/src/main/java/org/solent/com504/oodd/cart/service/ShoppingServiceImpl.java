/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.cart.service;

import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.oodd.bank.client.impl.BankRestClientImpl;
import org.solent.com504.oodd.bank.model.dto.BankTransactionStatus;
import org.solent.com504.oodd.bank.model.dto.CreditCard;
import org.solent.com504.oodd.bank.model.dto.TransactionReplyMessage;
import org.solent.com504.oodd.cart.dao.impl.InvoiceRepository;
import org.solent.com504.oodd.cart.dao.impl.ShoppingItemCatalogRepository;
import org.solent.com504.oodd.cart.dao.impl.UserRepository;
import org.solent.com504.oodd.cart.model.dto.Invoice;
import org.solent.com504.oodd.cart.model.service.ShoppingCart;
import org.solent.com504.oodd.cart.model.dto.ShoppingItem;
import org.solent.com504.oodd.cart.model.dto.User;
import org.solent.com504.oodd.cart.model.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author cgallen
 */
@Component
public class ShoppingServiceImpl implements ShoppingService {

    @Autowired
    private ShoppingItemCatalogRepository shoppingItemCatalogRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BankRestClientImpl restClient;

    private static final Logger LOG = LogManager.getLogger(ShoppingServiceImpl.class);

    /**
     *
     */
    public ShoppingServiceImpl() {
    }

    /**
     *
     * @return
     */
    @Override
    public List<ShoppingItem> getAvailableItems() {
        List<ShoppingItem> itemsList = shoppingItemCatalogRepository.findAll();
        return itemsList;
    }

    /**
     *
     * @param shoppingCart
     * @param user
     * @param purchaserCard
     * @param sellerCard
     * @return
     */
    @Override
    public boolean purchaseItems(ShoppingCart shoppingCart, User user, CreditCard purchaserCard, CreditCard sellerCard) {
        LOG.info("Purchasing Items");

        Double shoppingcartTotal = shoppingCart.getTotal();

//        Creating Invoice
        Invoice invoice1 = new Invoice();
        invoice1.setAmountDue(shoppingcartTotal);
        invoice1.setDateOfPurchase(new Date());
        invoice1.setPurchaser(user);
        invoice1.setPurchasedItems(shoppingCart.getShoppingCartItems());
        invoiceRepository.save(invoice1);

//        Transfering Money
        TransactionReplyMessage reply = restClient.transferMoney(purchaserCard, sellerCard, shoppingcartTotal);
        LOG.debug("transaction reply:" + reply);

        return BankTransactionStatus.SUCCESS.equals(reply.getStatus());

    }

    /**
     *
     * @param name
     * @return
     */
    @Override
    public ShoppingItem getNewItemByName(String name) {
//        ShoppingItem templateItem = itemMap.get(name);
//        
//        if(templateItem==null) return null;
//        
//        ShoppingItem item = new ShoppingItem();
//        item.setName(name);
//        item.setPrice(templateItem.getPrice());
//        item.setQuantity(0);
//        item.setUuid(UUID.randomUUID().toString());
        return null;
    }

}
