package com.restwebservice.cathibot.service;
import com.restwebservice.cathibot.dao.CustomerDao;
import com.restwebservice.cathibot.dao.FileDao;
import com.restwebservice.cathibot.model.Customer;
import com.restwebservice.cathibot.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

@Service
public class FileService {
    @Autowired
    private FileDao fileDao;
    @Autowired
    private CustomerDao customerDao;

    public void updateFiles(HashMap<File,String> files){
        Set<File> fileKey = files.keySet();
        Iterator<File> itr = fileKey.iterator();
        while(itr.hasNext()){
            File f = itr.next();
            System.out.println(f);
            Customer customer = customerDao.findByCustomerId(files.get(f));
            f.setCustomer(customer);
            fileDao.save(f);
        }
    }
}
