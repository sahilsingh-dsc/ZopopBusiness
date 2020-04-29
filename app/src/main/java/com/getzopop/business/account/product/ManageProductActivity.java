package com.getzopop.business.account.product;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.getzopop.business.R;
import com.google.android.material.button.MaterialButton;

public class ManageProductActivity extends AppCompatActivity implements View.OnClickListener {

    private MaterialButton btnAddProduct;
    private ImageView ivGoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_product);
        initView();
    }

    private void initView() {
        btnAddProduct = findViewById(R.id.btnAddProduct);
        ivGoBack = findViewById(R.id.ivGoBack);
        btnAddProduct.setOnClickListener(this);
        ivGoBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnAddProduct){
            startActivity(new Intent(ManageProductActivity.this, AddProductActivity.class));
        }
        if (v == ivGoBack){
            onBackPressed();
            finish();
        }

    }
}
