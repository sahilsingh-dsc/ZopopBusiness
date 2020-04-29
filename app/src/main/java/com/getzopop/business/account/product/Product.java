package com.getzopop.business.account.product;

import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.ArrayList;
import java.util.Date;

@IgnoreExtraProperties
public class Product {
    @ServerTimestamp
    private Date timestamp;
    private String active;
    private String category_id;
    private String product_category_name;
    private String id;
    private String name;
    private ArrayList<String> images;
    private String desc;
    private String unit;
    private String sale_price;
    private String mrp_price;

    public Product() {
    }

    public Product(Date timestamp, String active, String category_id, String product_category_name, String id, String name, ArrayList<String> images, String desc, String unit, String sale_price, String mrp_price) {
        this.timestamp = timestamp;
        this.active = active;
        this.category_id = category_id;
        this.product_category_name = product_category_name;
        this.id = id;
        this.name = name;
        this.images = images;
        this.desc = desc;
        this.unit = unit;
        this.sale_price = sale_price;
        this.mrp_price = mrp_price;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getProduct_category_name() {
        return product_category_name;
    }

    public void setProduct_category_name(String product_category_name) {
        this.product_category_name = product_category_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSale_price() {
        return sale_price;
    }

    public void setSale_price(String sale_price) {
        this.sale_price = sale_price;
    }

    public String getMrp_price() {
        return mrp_price;
    }

    public void setMrp_price(String mrp_price) {
        this.mrp_price = mrp_price;
    }
}
