package org.oms.customer;

import java.util.Date;

public class Customer {

    private int customerId;
    private String customerFirstName;
    private String customerLastName;
    private String customerEmail;
    private long customerPhone;
    private int addressId;
    private Date customerCreationDate;
    private String customerUsername;
    private char customerGender;

    public Customer(int customerId, String customerFirstName, String customerLastName, String customerEmail, long customerPhone, int addressId, Date customerCreationDate, String customerUsername, char customerGender) {
        this.customerId = customerId;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.addressId = addressId;
        this.customerCreationDate = customerCreationDate;
        this.customerUsername = customerUsername;
        this.customerGender = customerGender;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public long getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(long customerPhone) {
        this.customerPhone = customerPhone;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public Date getCustomerCreationDate() {
        return customerCreationDate;
    }

    public void setCustomerCreationDate(Date customerCreationDate) {
        this.customerCreationDate = customerCreationDate;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public char getCustomerGender() {
        return customerGender;
    }

    public void setCustomerGender(char customerGender) {
        this.customerGender = customerGender;
    }


}
