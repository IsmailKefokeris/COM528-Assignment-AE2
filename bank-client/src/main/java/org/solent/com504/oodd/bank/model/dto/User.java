package org.solent.com504.oodd.bank.model.dto;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import org.solent.com504.oodd.password.PasswordUtils;

/**
 *
 * @author ismai
 */
@Embeddable
public class User {

    private String firstName = "";

    private String secondName = "";

    private String address = "";

    private String username = "";

    private String password = "";

    private String hashedPassword = "";

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
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
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
        return "User{" + "firstName=" + firstName + ", secondName=" + secondName + ", address=" + address + ", username=" + username + '}';
    }

}
