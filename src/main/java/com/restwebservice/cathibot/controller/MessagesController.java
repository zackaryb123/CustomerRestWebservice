package com.restwebservice.cathibot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.restwebservice.cathibot.dao.MessagesDao;
import com.restwebservice.cathibot.model.AlertMessages;
import com.restwebservice.cathibot.model.Customer;

@RestController
@RequestMapping(path = "/messages")
@CrossOrigin(origins = "*")
public class MessagesController {
	
	@Autowired
	MessagesDao messagesDao;
	
	@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<AlertMessages> getAlertMessages() {
        AlertMessages alerts = messagesDao.findByColumnId(1);
        return new ResponseEntity<>(alerts, HttpStatus.OK);
    }
	
	@RequestMapping(path = "/update", method = RequestMethod.PUT)
	public ResponseEntity<AlertMessages> updateAlertMessages(@RequestBody AlertMessages am)
	{
		AlertMessages ret = messagesDao.save(am);
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
}
