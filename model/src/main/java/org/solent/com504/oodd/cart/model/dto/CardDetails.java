package org.solent.com504.oodd.cart.model.dto;

import javax.persistence.Embeddable;

/**
 *
 * @author ismai
 */
@Embeddable
public class CardDetails {

    private String name="";

    private String endDate="";

    private String cardNumber="";

    private String cvv="111";

    private String issueNumber="01";

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     *
     * @param endDate
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     *
     * @return
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     *
     * @param cardnumber
     */
    public void setCardNumber(String cardnumber) {
        this.cardNumber = cardnumber;
    }

    /**
     *
     * @return
     */
    public String getCvv() {
        return cvv;
    }

    /**
     *
     * @param cvv
     */
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    /**
     *
     * @return
     */
    public String getIssueNumber() {
        return issueNumber;
    }

    /**
     *
     * @param issueNumber
     */
    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
    }

    @Override
    public String toString() {
        return "CreditCard{" + "name=" + name + ", endDate=" + endDate + ", cardnumber=" + cardNumber + ", cvv=NOT PRINTED" +  ", issueNumber=" + issueNumber + '}';
    }
    
}
