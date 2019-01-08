package com.restwebservice.cathibot.dao;

import com.restwebservice.cathibot.model.AlertMessages;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessagesDao extends CrudRepository<AlertMessages, Integer> {
	AlertMessages findByColumnId(int columnId);
}
