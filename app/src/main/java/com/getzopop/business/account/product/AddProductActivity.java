package com.getzopop.business.account.product;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.getzopop.business.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddProductActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivGoBack;
    private TextView tvAddCategory;
    private TextView tvAddAttribute;
    private TextInputLayout ilProductBarcode;
    private TextInputEditText etProductBarcode;
    private TextInputLayout ilProductName;
    private TextInputEditText etProductName;
    private TextInputLayout ilMRPprice;
    private TextInputEditText etMRPPrice;
    private TextInputLayout ilPurchasePrice;
    private TextInputEditText etPurchasePrice;
    private TextInputEditText etSalePrice;
    private TextInputLayout ilProductPrice;
    private TextInputEditText etProductInStock;
    private TextInputLayout ilSelectProductCategory;
    private AutoCompleteTextView selectProductCategory;
    private TextInputLayout ilSelectProductAttribute;
    private AutoCompleteTextView selectProductAttribute;
    private TextInputLayout ilProductSupplier;
    private AutoCompleteTextView selectProductSupplier;
    private MaterialButton btnAddProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        initView();
    }

    private void initView() {
        ivGoBack = findViewById(R.id.ivGoBack);
        tvAddCategory = findViewById(R.id.tvAddCategory);
        tvAddAttribute = findViewById(R.id.tvAddAttribute);
        etProductBarcode = findViewById(R.id.etProductBarcode);
        etProductName = findViewById(R.id.etProductName);
        etMRPPrice = findViewById(R.id.etMRPPrice);
        etPurchasePrice = findViewById(R.id.etPurchasePrice);
        etSalePrice = findViewById(R.id.etSalePrice);
        etProductInStock = findViewById(R.id.etProductInStock);
        selectProductCategory = findViewById(R.id.selectProductCategory);
        selectProductAttribute = findViewById(R.id.selectProductAttribute);
        selectProductSupplier = findViewById(R.id.selectProductSupplier);
        btnAddProduct = findViewById(R.id.btnAddProduct);
        ilProductBarcode = findViewById(R.id.ilProductBarcode);
        ilProductName = findViewById(R.id.ilProductName);
        ilSelectProductCategory = findViewById(R.id.ilSelectProductCategory);
        ilSelectProductAttribute = findViewById(R.id.ilSelectProductAttribute);
        ilProductSupplier = findViewById(R.id.ilProductSupplier);
        ilMRPprice = findViewById(R.id.ilMRPprice);
        ilProductPrice = findViewById(R.id.ilProductPrice);
        ilPurchasePrice = findViewById(R.id.ilPurchasePrice);
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
