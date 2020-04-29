package com.getzopop.business.account.post;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.getzopop.business.R;
import com.google.android.material.button.MaterialButton;

public class ManagePostActivity extends AppCompatActivity implements View.OnClickListener {

    private MaterialButton btnCreateNewPost;
    private ImageView ivGoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_post);
        initView();
    }

    private void initView() {
        btnCreateNewPost = findViewById(R.id.btnCreateNewPost);
        ivGoBack = findViewById(R.id.ivGoBack);

        btnCreateNewPost.setOnClickListener(this);
        ivGoBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnCreateNewPost){
            startActivity(new Intent(ManagePostActivity.this, CreateNewPostActivity.class));
        }

        if (v ==ivGoBack){
            onBackPressed();
            finish();
        }

    }
}
