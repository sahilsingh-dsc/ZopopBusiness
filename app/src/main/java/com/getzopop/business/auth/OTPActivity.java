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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.getzopop.business.MainActivity;
import com.getzopop.business.R;
import com.getzopop.business.util.AppConstant;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;


public class OTPActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressBar progressBar;
    private TextInputEditText etOTPCode;
    private TextInputLayout ilOTPCode;
    private MaterialButton btnVerifyOTP;
    private FirebaseAuth mAuth;
    private LinearLayout lvOTP;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private String mAuthVerificationId;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        init();
    }

    private void init() {
        progressBar = findViewById(R.id.progressBar);
        etOTPCode = findViewById(R.id.etOTPCode);
        ilOTPCode = findViewById(R.id.ilOTPCode);
        btnVerifyOTP = findViewById(R.id.btnVerifyOTP);
        lvOTP = findViewById(R.id.lvOTP);

        btnVerifyOTP.setOnClickListener(this);
        etOTPCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String otp_code = etOTPCode.getText().toString();
                if (!otp_code.isEmpty()){
                    ilOTPCode.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        mAuth = FirebaseAuth.getInstance();

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(OTPActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                Snackbar.make(lvOTP, "Verification Failed, try again.", Snackbar.LENGTH_LONG).show();
                progressBar.setVisibility(View.INVISIBLE);
                btnVerifyOTP.setEnabled(true);
                onBackPressed();
                finish();
            }

            @Override
            public void onCodeSent(@NonNull final String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
            }

        };

        intent = getIntent();
        doPhoneValidation(intent.getStringExtra("mobile_number"));

    }

    @Override
    public void onClick(View v) {
        if (v == btnVerifyOTP){
            etOTPCode.clearFocus();
            hideKeyboard(OTPActivity.this);
            uiValidation();
        }
    }

    private void uiValidation() {
        String otp_code = etOTPCode.getText().toString();
        if (TextUtils.isEmpty(otp_code)){
            etOTPCode.requestFocus();
            ilOTPCode.setErrorEnabled(true);
            ilOTPCode.setError("6 digit OTP is required.");
            return;
        }
        if (!TextUtils.isDigitsOnly(otp_code)){
            etOTPCode.requestFocus();
            ilOTPCode.setErrorEnabled(true);
            ilOTPCode.setError("Invalid OTP.");
            return;
        }
        if (otp_code.length() != 6){
            etOTPCode.requestFocus();
            ilOTPCode.setErrorEnabled(true);
            ilOTPCode.setError("Invalid OTP.");
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        btnVerifyOTP.setEnabled(false);
        doOTPValidation(otp_code);
    }

    private void doOTPValidation(String otp_code) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mAuthVerificationId, otp_code);
        signInWithPhoneAuthCredential(credential);

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(OTPActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.INVISIBLE);
                            Snackbar.make(lvOTP, "Login Success", Snackbar.LENGTH_LONG);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    startActivity(new Intent(OTPActivity.this, MainActivity.class));
                                    finish();
                                }
                            }, AppConstant.MAIN_DELAY);
                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Snackbar.make(lvOTP, "Invalid OTP", Snackbar.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }

    private void doPhoneValidation(String mobile_number) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(mobile_number, 60, TimeUnit.SECONDS, OTPActivity.this, mCallbacks);
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
