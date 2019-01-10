package com.restwebservice.cathibot.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "CUSTOMER_TABLE")
public class Customer {

    @Id
    @Column(name = "customerId")
    private String customerId;

    @Column
    private String customerEmail;

    @OneToMany(mappedBy = "customer")
    private List<TaxFile> taxFiles;

    @Column
    private int receivedFiles;

    @Column
    private Date dateTransfered;

    @Column
    private String alarmDateInitial;

    @Column
    private String alarmDateFifth;

    @Column
    private String alarmDateSeventh;

    public Customer() { }

    public Customer(List<TaxFile> taxFiles, int receivedFiles, String alarmDateInitial, String alarmDateFifth, String alarmDateSeventh, String customerEmail, Date dateTransfered) {
        this.taxFiles = taxFiles;
        this.receivedFiles = receivedFiles;
        this.alarmDateInitial = alarmDateInitial;
        this.alarmDateFifth = alarmDateFifth;
        this.alarmDateSeventh = alarmDateSeventh;
    }

    public Date getDateTransfered() {
        return dateTransfered;
    }

    public void setDateTransfered(Date dateTransfered) {
        this.dateTransfered = dateTransfered;
    }

    public String getCustomerEmail() {
        return customerEmail;
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

    public String getCustomerId() {
        return customerId;
    }

    public List<TaxFile> getTaxFiles() {
        return taxFiles;
    }

    public int getReceivedFiles() {
        return receivedFiles;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setTaxFiles(List<TaxFile> taxFiles) {
        this.taxFiles = taxFiles;
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

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", taxFiles=" + taxFiles +
                ", receivedFiles=" + receivedFiles +
                ", dateTransfered=" + dateTransfered +
                ", alarmDateInitial='" + alarmDateInitial + '\'' +
                ", alarmDateFifth='" + alarmDateFifth + '\'' +
                ", alarmDateSeventh='" + alarmDateSeventh + '\'' +
                '}';
    }
}