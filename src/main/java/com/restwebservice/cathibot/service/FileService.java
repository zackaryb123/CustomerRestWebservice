package com.restwebservice.cathibot.service;
import com.restwebservice.cathibot.dao.CustomerDao;
import com.restwebservice.cathibot.dao.FileDao;
import com.restwebservice.cathibot.model.Customer;
import com.restwebservice.cathibot.model.TaxFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FileService {
    @Autowired
    private FileDao fileDao;

    @Autowired
    private CustomerDao customerDao;

    public void updateFiles(HashMap<TaxFile, String> taxFiles){
        Set<TaxFile> fileKey = taxFiles.keySet();
        Iterator<TaxFile> itr = fileKey.iterator();
        while(itr.hasNext()){
            TaxFile f = itr.next();
            Customer customer = customerDao.findByCustomerId(taxFiles.get(f));
            f.setCustomer(customer);
            fileDao.save(f);
        }
    }
}
