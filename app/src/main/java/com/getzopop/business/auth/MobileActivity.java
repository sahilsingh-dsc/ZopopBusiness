package com.getzopop.business.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.getzopop.business.R;
import com.getzopop.business.util.AppConstant;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class MobileActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressBar progressBar;
    private TextInputEditText etMobileNumber;
    private TextInputLayout ilMobileNumber;
    private MaterialButton btnSendOTP;
    private FirebaseAuth mAuth;
    private LinearLayout lvMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile);
        init();
    }

    private void init() {
        progressBar = findViewById(R.id.progressBar);
        etMobileNumber = findViewById(R.id.etMobileNumber);
        ilMobileNumber = findViewById(R.id.ilMobileNumber);
        btnSendOTP = findViewById(R.id.btnSendOTP);
        lvMobile = findViewById(R.id.lvMobile);

        btnSendOTP.setOnClickListener(this);
        etMobileNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String mobile_number = etMobileNumber.getText().toString();
                if (!mobile_number.isEmpty()){
                    ilMobileNumber.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v == btnSendOTP){
            etMobileNumber.clearFocus();
            hideKeyboard(MobileActivity.this);
            uiValidation();
        }
    }

    private void uiValidation() {
        String mobile_number = etMobileNumber.getText().toString();
        if (TextUtils.isEmpty(mobile_number)){
            etMobileNumber.requestFocus();
            ilMobileNumber.setErrorEnabled(true);
            ilMobileNumber.setError("10 digit mobile number is required.");
            return;
        }

        if (!TextUtils.isDigitsOnly(mobile_number)){
            etMobileNumber.requestFocus();
            ilMobileNumber.setErrorEnabled(true);
            ilMobileNumber.setError("Invalid mobile number.");
            return;
        }

        if (mobile_number.length() != 10){
            etMobileNumber.requestFocus();
            ilMobileNumber.setErrorEnabled(true);
            ilMobileNumber.setError("Invalid mobile number.");
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        btnSendOTP.setEnabled(false);
        doPhoneValidation("+91"+mobile_number);

    }

    private void doPhoneValidation(final String s) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.INVISIBLE);
                btnSendOTP.setEnabled(true);
                Intent intent = new Intent(MobileActivity.this, OTPActivity.class);
                intent.putExtra("mobile_number", s);
                startActivity(intent);
            }
        }, AppConstant.CODE_SENT_DELAY);
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
