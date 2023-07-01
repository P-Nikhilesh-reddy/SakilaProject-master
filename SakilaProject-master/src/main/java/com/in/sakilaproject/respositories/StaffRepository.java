package com.in.sakilaproject.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//import com.in.sakilaproject.entities.Customer;
//import com.in.sakilaproject.entities.Film;
import com.in.sakilaproject.entities.Staff;

public interface StaffRepository extends JpaRepository<Staff, Integer> {

    @Query(value = "SELECT * FROM staff s WHERE s.username = :username",
            nativeQuery = true)
    Staff getStaffByUsername(String username);

}
