package org.solent.com504.oodd.cart.model.dto;

import javax.persistence.Embeddable;

@Embeddable
public class CardDetails {

    private String name="";

    private String endDate="";

    private String cardNumber="";

    private String cvv="111";

    private String issueNumber="01";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardnumber) {
        this.cardNumber = cardnumber;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
    }

    @Override
    public String toString() {
        return "CreditCard{" + "name=" + name + ", endDate=" + endDate + ", cardnumber=" + cardNumber + ", cvv=NOT PRINTED" +  ", issueNumber=" + issueNumber + '}';
    }
    
}
