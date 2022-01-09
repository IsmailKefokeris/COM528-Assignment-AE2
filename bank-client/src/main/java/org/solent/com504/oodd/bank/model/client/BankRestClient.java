package org.solent.com504.oodd.bank.model.client;

import org.solent.com504.oodd.bank.model.dto.CreditCard;
import org.solent.com504.oodd.bank.model.dto.TransactionReplyMessage;

/**
 *
 * @author ismai
 */
public interface BankRestClient {

    /**
     *
     * @param fromCard
     * @param toCard
     * @param amount
     * @return
     */
    public  TransactionReplyMessage transferMoney(CreditCard fromCard, CreditCard toCard, Double amount);

    /**
     *
     * @param fromCard
     * @param toCard
     * @param amount
     * @param userName
     * @param password
     * @return
     */
    public  TransactionReplyMessage transferMoney(CreditCard fromCard, CreditCard toCard, Double amount, String userName, String password);
}
