package com.getzopop.business.account.post;

import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

@IgnoreExtraProperties
public class Posts {
    public static final String POST_USER_NAME = "Where's My Burger";
    public static final String POST_USER_PHOTO = "https://firebasestorage.googleapis.com/v0/b/getzopop-app.appspot.com/o/app_constant_images%2Fwmb.png?alt=media&token=a449351d-29ee-4c38-86d8-6a9dc2667ab4";

    @ServerTimestamp
    private Date post_timestamp;
    private String post_id;
    private String post_photo;
    private String post_description;
    private boolean post_action;
    private String post_action_type;
    private String post_action_link;
    private String post_user_id;
    private String post_user_name;
    private String post_user_photo;

    public Posts() {
    }

    public Posts(Date post_timestamp, String post_id, String post_photo, String post_description, boolean post_action, String post_action_type, String post_action_link, String post_user_id, String post_user_name, String post_user_photo) {
        this.post_timestamp = post_timestamp;
        this.post_id = post_id;
        this.post_photo = post_photo;
        this.post_description = post_description;
        this.post_action = post_action;
        this.post_action_type = post_action_type;
        this.post_action_link = post_action_link;
        this.post_user_id = post_user_id;
        this.post_user_name = post_user_name;
        this.post_user_photo = post_user_photo;
    }

    public Date getPost_timestamp() {
        return post_timestamp;
    }

    public void setPost_timestamp(Date post_timestamp) {
        this.post_timestamp = post_timestamp;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getPost_photo() {
        return post_photo;
    }

    public void setPost_photo(String post_photo) {
        this.post_photo = post_photo;
    }

    public String getPost_description() {
        return post_description;
    }

    public void setPost_description(String post_description) {
        this.post_description = post_description;
    }

    public boolean isPost_action() {
        return post_action;
    }

    public void setPost_action(boolean post_action) {
        this.post_action = post_action;
    }

    public String getPost_action_type() {
        return post_action_type;
    }

    public void setPost_action_type(String post_action_type) {
        this.post_action_type = post_action_type;
    }

    public String getPost_action_link() {
        return post_action_link;
    }

    public void setPost_action_link(String post_action_link) {
        this.post_action_link = post_action_link;
    }

    public String getPost_user_id() {
        return post_user_id;
    }

    public void setPost_user_id(String post_user_id) {
        this.post_user_id = post_user_id;
    }

    public String getPost_user_name() {
        return post_user_name;
    }

    public void setPost_user_name(String post_user_name) {
        this.post_user_name = post_user_name;
    }

    public String getPost_user_photo() {
        return post_user_photo;
    }

    public void setPost_user_photo(String post_user_photo) {
        this.post_user_photo = post_user_photo;
    }
}
