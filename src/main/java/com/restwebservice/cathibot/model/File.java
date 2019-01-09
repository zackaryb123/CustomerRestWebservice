package com.restwebservice.cathibot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "FILE_TABLE")
public class File {

    @Id
    @GeneratedValue
    @Column(name = "fileId")
    private int fileId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId")
    private Customer customer;

    @Column
    private String fileName;

    @Column
    private String dateReceived;
    @Column
    private String alertSent;
    @Column
    private int noRecords;
    @Column
    private double amount;
    @Column
    private String dateMoved;
    @Column
    private String status;

    public File() { }

    public File(Customer customer, String dateReceived, String alertSent, int noRecords, double amount, String dateMoved, String status, String fileName) {
        this.customer = customer;
        this.dateReceived = dateReceived;
        this.alertSent = alertSent;
        this.noRecords = noRecords;
        this.amount = amount;
        this.dateMoved = dateMoved;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getStatus() {
        return status;
    }

    public int getFileId() {
        return fileId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getDateReceived() {
        return dateReceived;
    }

    public String getAlertSent() {
        return alertSent;
    }

    public int getNoRecords() {
        return noRecords;
    }

    public double getAmount() {
        return amount;
    }

    public String getDateMoved() {
        return dateMoved;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setDateReceived(String dateReceived) {
        this.dateReceived = dateReceived;
    }

    public void setAlertSent(String alertSent) {
        this.alertSent = alertSent;
    }

    public void setNoRecords(int noRecords) {
        this.noRecords = noRecords;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDateMoved(String dateMoved) {
        this.dateMoved = dateMoved;
    }
}