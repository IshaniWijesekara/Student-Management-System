package com.esoft.student_management.repository;

import com.esoft.student_management.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Query("SELECT count(c.itemCode) FROM Item c")
    long getTotalItems();

    Item findByItemCode(String itemCode);

}
