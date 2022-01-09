package org.solent.com504.oodd.bank.model.dto;

/**
 *
 * @author ismai
 */
public class TransactionRequestMessage {

    private CreditCard fromCard;

    private CreditCard toCard;

    private Double amount;

    /**
     *
     * @return
     */
    public CreditCard getFromCard() {
        return fromCard;
    }

    /**
     *
     * @param fromCard
     */
    public void setFromCard(CreditCard fromCard) {
        this.fromCard = fromCard;
    }

    /**
     *
     * @return
     */
    public CreditCard getToCard() {
        return toCard;
    }

    /**
     *
     * @param toCard
     */
    public void setToCard(CreditCard toCard) {
        this.toCard = toCard;
    }

    /**
     *
     * @return
     */
    public Double getAmount() {
        return amount;
    }

    /**
     *
     * @param amount
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransactionRequestMessage{" + "fromCard=" + fromCard + ", toCard=" + toCard + ", amount=" + amount + '}';
    }
    
    
}
