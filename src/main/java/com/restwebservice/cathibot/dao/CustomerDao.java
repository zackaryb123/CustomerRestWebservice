package com.restwebservice.cathibot.dao;

import com.restwebservice.cathibot.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao  extends CrudRepository<Customer, Integer> {
    Customer findByCustomerId(int claimId);
    Customer deleteByCustomerId(int claimId);
}
