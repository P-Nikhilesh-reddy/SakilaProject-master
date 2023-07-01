package com.in.sakilaproject.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.in.sakilaproject.entities.Rental;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
    Rental getRentalByRentalId(Integer id);
    
    @Query(value = "SELECT * FROM rental r INNER JOIN customer c ON r.customer_id = c.customer_id WHERE c.customer_id = :customerId",
            nativeQuery = true)
    List<Rental> getRentalByCustomerId(Integer customerId);

}
