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

    public Customer() { }

    public Customer(List<File> files, int receivedFiles) {
        this.files = files;
        this.receivedFiles = receivedFiles;
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
}