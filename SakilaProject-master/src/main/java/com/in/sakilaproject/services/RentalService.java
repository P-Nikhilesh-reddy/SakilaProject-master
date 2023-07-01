package com.in.sakilaproject.services;

import com.in.sakilaproject.entities.Rental;
import com.in.sakilaproject.respositories.RentalRepository;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RentalService {

    private RentalRepository rentalRepository;

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public List<Rental> getRentalsByCustomer(Integer id){
        return rentalRepository.getRentalByCustomerId(id);
    }

    public void addRental(int inventoryId, int customerId, Timestamp returnDate){
        LocalDateTime rentalDate = LocalDateTime.now();
        Rental rental = new Rental();
        rental.setInventoryId(inventoryId);
        rental.setCustomerId(customerId);
        rental.setRentalDate(Timestamp.valueOf(rentalDate));
        rental.setLastUpdate(Timestamp.valueOf(rentalDate));
        rental.setReturnDate(returnDate);
        rental.setStaffId(1); //requires default value
        rentalRepository.save(rental);
    }
}
