package com.restwebservice.cathibot.controller;

import com.restwebservice.cathibot.dao.CustomerDao;
import com.restwebservice.cathibot.dao.FileDao;
import com.restwebservice.cathibot.model.Customer;
import com.restwebservice.cathibot.model.TaxFile;
import com.restwebservice.cathibot.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/files")
@CrossOrigin(origins = "*")
public class FileController {
    @Autowired
    FileDao fileDao;

    @Autowired
    MailService mailService;

    @Autowired
    CustomerDao customerDao;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<TaxFile>> getAll() {
        List<TaxFile> list = (List<TaxFile>) fileDao.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TaxFile> post(@RequestBody TaxFile taxFile) {
        TaxFile f = fileDao.save(taxFile);
        return new ResponseEntity<>(f, HttpStatus.OK);
    }

    @RequestMapping(path = "/{fileId}", method = RequestMethod.GET)
    public ResponseEntity<TaxFile> getById(@PathVariable int fileId) {
        TaxFile taxFile = fileDao.findByFileId(fileId);
        return new ResponseEntity<>(taxFile, HttpStatus.OK);
    }

    @RequestMapping(path = "/{fileId}", method = RequestMethod.DELETE)
    public ResponseEntity<TaxFile> deleteById(@PathVariable int fileId){
        TaxFile taxFile = fileDao.deleteByFileId(fileId);
        return new ResponseEntity<>(taxFile, HttpStatus.OK);
    }

    @RequestMapping(path = "/status/{customerId}/{fileId}", method = RequestMethod.PUT)
    public ResponseEntity<TaxFile> updateById(@PathVariable String customerId, @PathVariable int fileId, @RequestBody TaxFile taxFile) throws IOException, MessagingException {
        TaxFile findTaxFile = fileDao.findByFileId(fileId);

        findTaxFile.setCustomer(findTaxFile.getCustomer());
        findTaxFile.setDateReceived(findTaxFile.getDateReceived());
        findTaxFile.setAlertSent(findTaxFile.getAlertSent());
        findTaxFile.setNoRecords(findTaxFile.getNoRecords());
        findTaxFile.setAmount(findTaxFile.getAmount());
        findTaxFile.setDateMoved(findTaxFile.getDateMoved());

        findTaxFile.setStatus(taxFile.getStatus());
        TaxFile f = fileDao.save(findTaxFile);

        if(findTaxFile.getStatus().equals("Moved") && taxFile.getStatus().equals("Moved")){
            Customer customer = customerDao.findByCustomerId(customerId);
            mailService.movedFileMail(customer, findTaxFile);
        }

        return new ResponseEntity<>(f, HttpStatus.OK);
    }
}
