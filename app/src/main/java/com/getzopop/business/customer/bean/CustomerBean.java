package com.getzopop.business.customer.bean;

import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

@IgnoreExtraProperties
public class CustomerBean {
    private String customer_id;
    private String customer_name;
    private String customer_mobile;
    private String customer_email;
    private String customer_dob;
    private String customer_ansy;
    private String customer_gender;
    private String customer_address;
    @ServerTimestamp
    private Date customer_timestamp;

    public CustomerBean() {
    }

    public CustomerBean(String customer_id, String customer_name, String customer_mobile, String customer_email, String customer_dob, String customer_ansy, String customer_gender, String customer_address, Date customer_timestamp) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.customer_mobile = customer_mobile;
        this.customer_email = customer_email;
        this.customer_dob = customer_dob;
        this.customer_ansy = customer_ansy;
        this.customer_gender = customer_gender;
        this.customer_address = customer_address;
        this.customer_timestamp = customer_timestamp;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_mobile() {
        return customer_mobile;
    }

    public void setCustomer_mobile(String customer_mobile) {
        this.customer_mobile = customer_mobile;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getCustomer_dob() {
        return customer_dob;
    }

    public void setCustomer_dob(String customer_dob) {
        this.customer_dob = customer_dob;
    }

    public String getCustomer_ansy() {
        return customer_ansy;
    }

    public void setCustomer_ansy(String customer_ansy) {
        this.customer_ansy = customer_ansy;
    }

    public String getCustomer_gender() {
        return customer_gender;
    }

    public void setCustomer_gender(String customer_gender) {
        this.customer_gender = customer_gender;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public Date getCustomer_timestamp() {
        return customer_timestamp;
    }

    public void setCustomer_timestamp(Date customer_timestamp) {
        this.customer_timestamp = customer_timestamp;
    }
}
