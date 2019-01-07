package com.restwebservice.cathibot.controller;

import com.restwebservice.cathibot.dao.CustomerDao;
import com.restwebservice.cathibot.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/customers")
@CrossOrigin(origins = "*")
public class CustomerController {
    @Autowired
    CustomerDao customerDao;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getAllClaim() {
        List<Customer> list = (List<Customer>) customerDao.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Customer> postClaim(@RequestBody Customer customer) {
        Customer c = customerDao.save(customer);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @RequestMapping(path = "/{customerId}", method = RequestMethod.GET)
    public ResponseEntity<Customer> getClaimById(@PathVariable int customerId) {
        Customer customer = customerDao.findByCustomerId(customerId);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @RequestMapping(path = "/{customerId}", method = RequestMethod.DELETE)
    public ResponseEntity<Customer> deleteClaimById(@PathVariable int customerId){
        Customer customer = customerDao.deleteByCustomerId(customerId);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @RequestMapping(path = "/{customerId}", method = RequestMethod.PUT)
    public ResponseEntity<Customer> updateClaimById(@PathVariable int customerId, @RequestBody Customer customer){
        Customer findCustomer = customerDao.findByCustomerId(customerId);
        findCustomer.setFiles(customer.getFiles());
        findCustomer.setReceivedFiles(customer.getReceivedFiles());
        Customer c = customerDao.save(findCustomer);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @RequestMapping(path = "/alarm1/{customerId}", method = RequestMethod.PUT)
    public ResponseEntity<Customer> updateClaimById1(@PathVariable int customerId, @RequestBody Customer customer){
        Customer findCustomer = customerDao.findByCustomerId(customerId);
        findCustomer.setAlarmDateInitial(customer.getAlarmDateInitial());
        Customer c = customerDao.save(findCustomer);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @RequestMapping(path = "/alarm5/{customerId}", method = RequestMethod.PUT)
    public ResponseEntity<Customer> updateClaimById5(@PathVariable int customerId, @RequestBody Customer customer){
        Customer findCustomer = customerDao.findByCustomerId(customerId);
        findCustomer.setAlarmDateFifth(customer.getAlarmDateFifth());
        Customer c = customerDao.save(findCustomer);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @RequestMapping(path = "/alarm7/{customerId}", method = RequestMethod.PUT)
    public ResponseEntity<Customer> updateClaimById7(@PathVariable int customerId, @RequestBody Customer customer){
        Customer findCustomer = customerDao.findByCustomerId(customerId);
        findCustomer.setAlarmDateSeventh(customer.getAlarmDateSeventh());
        Customer c = customerDao.save(findCustomer);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }
}
