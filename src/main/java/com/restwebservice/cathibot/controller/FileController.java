package com.restwebservice.cathibot.controller;

import com.restwebservice.cathibot.dao.FileDao;
import com.restwebservice.cathibot.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/files")
@CrossOrigin(origins = "*")
public class FileController {
    @Autowired
    FileDao fileDao;

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

    @RequestMapping(path = "/{fileId}", method = RequestMethod.PUT)
    public ResponseEntity<File> updateById(@PathVariable int fileId, @RequestBody File file){
        File findFile = fileDao.findByFileId(fileId);
        findFile.setCustomer(file.getCustomer());
        findFile.setDateReceived(file.getDateReceived());
        findFile.setAlertSent(file.getAlertSent());
        findFile.setNoRecords(file.getNoRecords());
        findFile.setAmount(file.getAmount());
        findFile.setDateMoved(file.getDateMoved());
        File f = fileDao.save(findFile);
        return new ResponseEntity<>(f, HttpStatus.OK);
    }
}
