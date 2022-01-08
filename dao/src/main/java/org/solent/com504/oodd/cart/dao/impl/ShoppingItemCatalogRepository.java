package org.solent.com504.oodd.cart.dao.impl;

import java.util.List;
import org.solent.com504.oodd.cart.model.dto.ShoppingItem;
import org.solent.com504.oodd.cart.model.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingItemCatalogRepository  extends JpaRepository<ShoppingItem,Long>{
    
//    @Query("select item from ShoppingItem item where item.name = :name")
//    public List<ShoppingItem> findByName(@Param("name")String name);
    
//    @Query("select item from ShoppingItem item where item.id = :id")
//    public List<ShoppingItem> findByID(@Param("id")String id);
    
}
