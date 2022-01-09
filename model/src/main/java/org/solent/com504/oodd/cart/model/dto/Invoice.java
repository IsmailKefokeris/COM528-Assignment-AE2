package org.solent.com504.oodd.cart.model.dto;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author ismai
 */
@Entity
public class Invoice {

    private Long id;

    private String invoiceNumber;

    private Date dateOfPurchase;

    private Double amountDue;

    private List<ShoppingItem> purchasedItems;

    private User purchaser;

    /**
     *
     * @return
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    /**
     *
     * @param invoiceNumber
     */
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    /**
     *
     * @return
     */
    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    /**
     *
     * @param dateOfPurchase
     */
    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    /**
     *
     * @return
     */
    public Double getAmountDue() {
        return amountDue;
    }

    /**
     *
     * @param amountDue
     */
    public void setAmountDue(Double amountDue) {
        this.amountDue = amountDue;
    }

    /**
     *
     * @return
     */
    @OneToMany
    public List<ShoppingItem> getPurchasedItems() {
        return purchasedItems;
    }

    /**
     *
     * @param purchasedItems
     */
    public void setPurchasedItems(List<ShoppingItem> purchasedItems) {
        this.purchasedItems = purchasedItems;
    }

    /**
     *
     * @return
     */
    @OneToOne
    public User getPurchaser() {
        return purchaser;
    }

    /**
     *
     * @param purchaser
     */
    public void setPurchaser(User purchaser) {
        this.purchaser = purchaser;
    }

}
