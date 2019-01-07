package com.restwebservice.cathibot.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CUSTOMER_TABLE")
public class Customer {

    @Id
    @GeneratedValue
    @Column(name = "customerId")
    private int customerId;

    @OneToMany(mappedBy = "customer")
    private List<File> files;

    @Column
    private int receivedFiles;

    @Column
    private String alarmDateInitial;

    @Column
    private String alarmDateFifth;

    @Column
    private String alarmDateSeventh;

    public Customer() { }

    public Customer(List<File> files, int receivedFiles, String alarmDateInitial, String alarmDateFifth, String alarmDateSeventh) {
        this.files = files;
        this.receivedFiles = receivedFiles;
        this.alarmDateInitial = alarmDateInitial;
        this.alarmDateFifth = alarmDateFifth;
        this.alarmDateSeventh = alarmDateSeventh;
    }

    public String getAlarmDateInitial() {
        return alarmDateInitial;
    }

    public String getAlarmDateFifth() {
        return alarmDateFifth;
    }

    public String getAlarmDateSeventh() {
        return alarmDateSeventh;
    }

    public int getCustomerId() {
        return customerId;
    }

    public List<File> getFiles() {
        return files;
    }

    public int getReceivedFiles() {
        return receivedFiles;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public void setReceivedFiles(int receivedFiles) {
        this.receivedFiles = receivedFiles;
    }

    public void setAlarmDateInitial(String alarmDateInitial) {
        this.alarmDateInitial = alarmDateInitial;
    }

    public void setAlarmDateFifth(String alarmDateFifth) {
        this.alarmDateFifth = alarmDateFifth;
    }

    public void setAlarmDateSeventh(String alarmDateSeventh) {
        this.alarmDateSeventh = alarmDateSeventh;
    }
}