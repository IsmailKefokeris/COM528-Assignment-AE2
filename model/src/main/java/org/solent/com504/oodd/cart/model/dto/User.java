package org.solent.com504.oodd.cart.model.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.persistence.Embedded;
import org.solent.com504.oodd.bank.model.dto.CreditCard;
import org.solent.com504.oodd.password.PasswordUtils;

/**
 *
 * @author ismai
 */
@Entity
public class User {

    private Long id;

    private String firstName = "";

    private String secondName = "";

    private String username = "";

    private String password = "";

    private String hashedPassword = "";

    private Address address;

    private UserRole userRole;

    private Boolean enabled = true;
    
    private CreditCard cardDetails = null;

    /**
     *
     * @return
     */
    @Embedded
    public CreditCard getCardDetails() {
        return cardDetails;
    }

    /**
     *
     * @param cardDetails
     */
    public void setCardDetails(CreditCard cardDetails) {
        this.cardDetails = cardDetails;
    }

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
    public UserRole getUserRole() {
        return userRole;
    }

    /**
     *
     * @param userRole
     */
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     */
    @Embedded
    public Address getAddress() {
        return address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     */
    public String getSecondName() {
        return secondName;
    }

    /**
     *
     * @param secondName
     */
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    /**
     *
     * @return
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     *
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    // passwords not saved in database only passwordhash is saved

    /**
     *
     * @return
     */
    @Transient
    public String getPassword() {
        return password;
    }

    // generate hashed password to save in database

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
        setHashedPassword(PasswordUtils.hashPassword(password));
    }

    /**
     *
     * @param checkPassword
     * @return
     */
    public boolean isValidPassword(String checkPassword) {
        if (checkPassword == null || getHashedPassword() == null) {
            return false;
        }
        return PasswordUtils.checkPassword(checkPassword, getHashedPassword());
    }

    /**
     *
     * @return
     */
    public String getHashedPassword() {
        return hashedPassword;
    }

    /**
     *
     * @param hashedPassword
     */
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    // no password or hashed password
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", firstName=" + firstName + ", secondName=" + secondName + ", username=" + username + ", password=NOT SHOWN, address=" + address + ", userRole=" + userRole + '}';
    }

}
