package com.getzopop.business.account.product;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.getzopop.business.R;

public class AddProductActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivGoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        initView();
    }

    private void initView() {
        ivGoBack = findViewById(R.id.ivGoBack);
        ivGoBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == ivGoBack){
            onBackPressed();
            finish();
        }
    }
}
