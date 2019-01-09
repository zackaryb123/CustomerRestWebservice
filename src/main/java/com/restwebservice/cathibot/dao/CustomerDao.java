package com.restwebservice.cathibot.dao;

import com.restwebservice.cathibot.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDao  extends CrudRepository<Customer, String> {
    Customer findByCustomerId(String customerId);
    Customer deleteByCustomerId(int customerId);
}
