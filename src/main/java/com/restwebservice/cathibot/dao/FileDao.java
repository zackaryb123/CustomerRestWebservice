package com.restwebservice.cathibot.dao;

import com.restwebservice.cathibot.model.File;
import org.springframework.data.repository.CrudRepository;

public interface FileDao  extends CrudRepository<File, Integer> {
    File findByFileId(int fileId);
    File deleteByFileId(int fileId);
}
