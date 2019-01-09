package com.restwebservice.cathibot.controller;

import com.restwebservice.cathibot.dao.CustomerDao;
import com.restwebservice.cathibot.dao.FileDao;
import com.restwebservice.cathibot.model.Customer;
import com.restwebservice.cathibot.model.File;
import com.restwebservice.cathibot.service.FileService;
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
    public ResponseEntity<List<File>> getAll() {
        List<File> list = (List<File>) fileDao.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<File> post(@RequestBody File file) {
        File f = fileDao.save(file);
        return new ResponseEntity<>(f, HttpStatus.OK);
    }

    @RequestMapping(path = "/{fileId}", method = RequestMethod.GET)
    public ResponseEntity<File> getById(@PathVariable int fileId) {
        File file = fileDao.findByFileId(fileId);
        return new ResponseEntity<>(file, HttpStatus.OK);
    }

    @RequestMapping(path = "/{fileId}", method = RequestMethod.DELETE)
    public ResponseEntity<File> deleteById(@PathVariable int fileId){
        File file = fileDao.deleteByFileId(fileId);
        return new ResponseEntity<>(file, HttpStatus.OK);
    }

    @RequestMapping(path = "/status/{customerId}/{fileId}", method = RequestMethod.PUT)
    public ResponseEntity<File> updateById(@PathVariable String customerId, @PathVariable int fileId, @RequestBody File file) throws IOException, MessagingException {
        File findFile = fileDao.findByFileId(fileId);

        findFile.setCustomer(findFile.getCustomer());
        findFile.setDateReceived(findFile.getDateReceived());
        findFile.setAlertSent(findFile.getAlertSent());
        findFile.setNoRecords(findFile.getNoRecords());
        findFile.setAmount(findFile.getAmount());
        findFile.setDateMoved(findFile.getDateMoved());

        findFile.setStatus(file.getStatus());
        File f = fileDao.save(findFile);

        if(findFile.getStatus().equals("Moved") && file.getStatus().equals("Moved")){
            Customer customer = customerDao.findByCustomerId(customerId);
            mailService.movedFileMail(customer, findFile);
        }

        return new ResponseEntity<>(f, HttpStatus.OK);
    }
}
