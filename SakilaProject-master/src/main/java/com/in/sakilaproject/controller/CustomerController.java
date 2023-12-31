package com.in.sakilaproject.controller;

import com.in.sakilaproject.entities.*;
import com.in.sakilaproject.services.CustomerService;
import com.in.sakilaproject.services.FilmService;
import com.in.sakilaproject.services.InventoryService;
import com.in.sakilaproject.services.RentalService;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import java.sql.Timestamp;
//import java.time.Instant;
//import java.time.LocalDate;

@RestController
public class CustomerController {

    private CustomerService customerService;
    private RentalService rentalService;
    private InventoryService inventoryService;
    private FilmService filmService;

    @Autowired
    public CustomerController(CustomerService customerService, RentalService rentalService, InventoryService inventoryService, FilmService filmService) {
        this.customerService = customerService;
        this.rentalService = rentalService;
        this.inventoryService = inventoryService;
        this.filmService = filmService;
    }

    @GetMapping("/customer")
    public String currentUser(ModelMap modelMap, HttpServletRequest request) {
        String email = request.getRemoteUser();
        Customer customer = customerService.getCustomerByEmail(email);
        List<Order> ordersList = new ArrayList<>();
        if(customer != null){
            List<Rental> customersRentals = rentalService.getRentalsByCustomer(customer.getCustomerId());
            for(Rental rental : customersRentals){
                int inventoryId = rental.getInventoryId();
                Inventory inventory = inventoryService.getInventoriesById(inventoryId);
                Film film = filmService.getFilmByID(inventory.getFilmId());
                Order order = new Order(customer, film, rental);
                ordersList.add(order);
            }
        }
        modelMap.addAttribute("orders", ordersList);
        modelMap.addAttribute("customer", customer);
        return "customer/customer";
    }

    @GetMapping("/api/customers")
    public String getCustomers(ModelMap modelMap,
                               @RequestParam(value = "firstName", defaultValue = "ALL CUSTOMERS") String firstNameFilter,
                               @RequestParam(value = "lastName", defaultValue = "ALL CUSTOMERS") String lastNameFilter) {
        List<Customer> customers;
        if (firstNameFilter.equals("ALL CUSTOMERS") && lastNameFilter.equals("ALL CUSTOMERS")) {
            customers = customerService.getAllCustomers();
        } else if (lastNameFilter.equals("ALL CUSTOMERS")){
            customers = customerService.getCustomersByFirstName(firstNameFilter);
        } else if (firstNameFilter.equals("ALL CUSTOMERS")) {
            customers = customerService.getCustomersByLastName(lastNameFilter);
        } else {
            customers = customerService.getCustomersByFullName(firstNameFilter, lastNameFilter);
        }

        modelMap.addAttribute("customers", customers);
        modelMap.addAttribute("allCustomers", customerService.getAllCustomers());
        return "api/customers";
    }


    @GetMapping("/api/view/customers/{id}")
    public String showUsersRentalHistory(ModelMap modelMap,
                                         @PathVariable(name = "id") int id) {
        Customer customer = customerService.getCustomerByID(id);
        List<Order> ordersList = new ArrayList<>();
        if (customer != null) {
            List<Rental> customersRentals = rentalService.getRentalsByCustomer(id);
            for (Rental rental : customersRentals) {
                int inventoryId = rental.getInventoryId();
                Inventory inventory = inventoryService.getInventoriesById(inventoryId);
                Film film = filmService.getFilmByID(inventory.getFilmId());
                Order order = new Order(customer, film, rental);
                ordersList.add(order);
            }
        }
        modelMap.addAttribute("history", ordersList);
        modelMap.addAttribute("customer", customer);
        return "api/customerDetails";
    }


}
