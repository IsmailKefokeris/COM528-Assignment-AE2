/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.cart.model.dto;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author cgallen
 */
@Entity
public class ShoppingItem {
    
    private Long id;
    private String uuid=null;
    private String name=null;
    private Integer quantity=0;
    private Double price=0.0;
    
    /**
     *
     */
    public ShoppingItem(){
        
    }

    /**
     *
     * @param name
     * @param price
     */
    public ShoppingItem(String name, Double price) {
        this.name = name;
        this.price = price;
        this.uuid = UUID.randomUUID().toString();
    }

//    public ShoppingItem(String house, double d, int i) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

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
    public String getUuid() {
        return uuid;
    }

    /**
     *
     * @param uuuid
     */
    public void setUuid(String uuuid) {
        this.uuid = uuuid;
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
    public Integer getQuantity() {
        return quantity;
    }

    /**
     *
     * @param quantity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     *
     * @return
     */
    public Double getPrice() {
        return price;
    }

    /**
     *
     * @param price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ShoppingItem{" + "uuuid=" + uuid + ", name=" + name + ", quantity=" + quantity + ", price=" + price + '}';
    }
    
            
    
}
