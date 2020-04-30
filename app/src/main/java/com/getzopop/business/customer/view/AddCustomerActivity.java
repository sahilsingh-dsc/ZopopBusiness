package com.getzopop.business.customer.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.getzopop.business.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddCustomerActivity extends AppCompatActivity implements View.OnClickListener {

private ImageView ivGoBack;
private TextView tvMenuTitle;
private RadioGroup rgGender;
private RadioGroup rgCustomer;
private RadioButton rbSimpleCustomer;
private RadioButton rbAdvanceCustomer;
private TextInputEditText etName;
private TextInputEditText etMobile;
private TextInputEditText etEmail;
private TextInputEditText etDOB;
private TextInputEditText etDOA;
private TextInputEditText etAddress;
private MaterialButton btnAdd;
private TextInputLayout ilEmail;
private TextInputLayout ilName;
private TextInputLayout ilMobile;
private TextInputLayout ilDOB;
private TextInputLayout ilDOA;
private TextInputLayout ilAddress;
private String customer_type = "simple";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        init();
    }

    private void init() {
        ivGoBack = findViewById(R.id.ivGoBack);
        tvMenuTitle = findViewById(R.id.tvMenuTitle);
        rgGender = findViewById(R.id.rgGender);
        rgCustomer = findViewById(R.id.rgCustomer);
        rbSimpleCustomer = findViewById(R.id.rbSimpleCustomer);
        rbAdvanceCustomer = findViewById(R.id.rbAdvanceCustomer);
        etName = findViewById(R.id.etName);
        etMobile = findViewById(R.id.etMobile);
        etEmail = findViewById(R.id.etEmail);
        etDOB = findViewById(R.id.etDOB);
        etDOA = findViewById(R.id.etDOA);
        etAddress = findViewById(R.id.etAddress);
        btnAdd = findViewById(R.id.btnAdd);
        ilEmail = findViewById(R.id.ilEmail);
        ilName = findViewById(R.id.ilName);
        ilMobile = findViewById(R.id.ilMobile);
        ilDOB = findViewById(R.id.ilDOB);
        ilDOA = findViewById(R.id.ilDOA);
        ilAddress = findViewById(R.id.ilAddress);

        rbSimpleCustomer.setChecked(true);
        simpleCustomerView();

        rbSimpleCustomer.setOnClickListener(this);
        rbAdvanceCustomer.setOnClickListener(this);

        btnAdd.setOnClickListener(this);

    }

    private void simpleCustomerView(){
        rgGender.setVisibility(View.GONE);
        ilEmail.setVisibility(View.GONE);
        ilDOB.setVisibility(View.GONE);
        ilDOA.setVisibility(View.GONE);
        ilAddress.setVisibility(View.GONE);

    }

    private void advanceCustomerView(){
        rgGender.setVisibility(View.VISIBLE);
        ilEmail.setVisibility(View.VISIBLE);
        ilDOB.setVisibility(View.VISIBLE);
        ilDOA.setVisibility(View.VISIBLE);
        ilAddress.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        if (view == rbSimpleCustomer){
            customer_type = "simple";
            simpleCustomerView();
        }
        if (view == rbAdvanceCustomer){
            customer_type = "advance";
            advanceCustomerView();
        }
        if (view == btnAdd){
            if (customer_type.equals("simple")){
                simpleUIValidation();
            } else if (customer_type.equals("advance")){
                advanceUIValidation();
            }
        }
    }

    private void advanceUIValidation() {
        addCustomerToDB();
    }

    private void simpleUIValidation() {
        addCustomerToDB();
    }

    private void addCustomerToDB() {

    }



}
