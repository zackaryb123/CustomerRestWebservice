package com.restwebservice.cathibot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ALERT_MESSAGES_TABLE")
public class AlertMessages {
	
	@Id
	@Column(name="columnId")
	private int columnId;
	
	@Column
    private String textMessage;

    @Column
    private String emailMessage;
    
    public AlertMessages() {}
    
    public AlertMessages(String textMessage, String emailMessage) {
    	columnId = 1;
    	this.textMessage = textMessage;
    	this.emailMessage = emailMessage;
    }

	public int getColumnId() {
		return columnId;
	}

	public String getTextMessage() {
		return textMessage;
	}

	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}

	public String getEmailMessage() {
		return emailMessage;
	}

	public void setEmailMessage(String emailMessage) {
		this.emailMessage = emailMessage;
	}
    
    
	
}
