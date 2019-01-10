package com.restwebservice.cathibot.dao;

import com.restwebservice.cathibot.model.TaxFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDao  extends CrudRepository<TaxFile, Integer> {
    TaxFile findByFileId(int fileId);
    TaxFile deleteByFileId(int fileId);
}
