package org.solent.com504.oodd.bank.model.dto;

import javax.persistence.Embeddable;

/**
 *
 * @author ismai
 */
@Embeddable
public class CreditCard {

    private String name="";

    private String endDate="";

    private String cardnumber="";

    private String cvv="111";

    private String issueNumber="01";

    /**
     *
     */
    public CreditCard() {
    }

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
    public String getCardnumber() {
        return cardnumber;
    }

    /**
     *
     * @param cardnumber
     */
    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
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
        return "CreditCard{" + "name=" + name + ", endDate=" + endDate + ", cardnumber=" + cardnumber + ", cvv=NOT PRINTED" +  ", issueNumber=" + issueNumber + '}';
    }
    
    
    
    
}
