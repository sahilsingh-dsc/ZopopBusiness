package com.getzopop.business.account.post;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.getzopop.business.R;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URI;
import java.util.ArrayList;
import java.util.UUID;

public class CreateNewPostActivity extends AppCompatActivity implements View.OnClickListener {

    private MaterialCardView cardPostImage;
    private LinearLayout lvAddPhoto;
    private ImageView ivAddPhoto;
    private TextInputLayout tlPostDesc;
    private TextInputEditText etPostDesc;
    private TextInputLayout tlPostActionType;
    private AutoCompleteTextView actPostActionType;
    private TextInputLayout tlPostActionLink;
    private TextInputEditText etPostActionLink;
    private Switch switchPostAction;
    private MaterialButton btnPublishPost;
    private ImageView ivPhoto;
    private TextView tvPhoto;
    private boolean post_action = false;
    private Uri postImageURI = null;
    private String[] ACTION_TYPE = {"Book", "Order Online", "Buy", "Learn More", "Sign up"};
    private ArrayAdapter<String> actionAdapter;

    private FirebaseFirestore db;
    private FirebaseAuth firebaseAuth;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private String post_photo;
    private ImageView ivGoBack;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_post);
        initView();
    }

    private void initView() {
        cardPostImage = findViewById(R.id.cardPostImage);
        lvAddPhoto = findViewById(R.id.lvAddPhoto);
        ivAddPhoto = findViewById(R.id.ivAddPhoto);

        ivGoBack = findViewById(R.id.ivGoBack);
        ivGoBack.setOnClickListener(this);

        tlPostDesc = findViewById(R.id.tlPostDesc);
        etPostDesc = findViewById(R.id.etPostDesc);

        tlPostActionType = findViewById(R.id.tlPostActionType);
        actPostActionType = findViewById(R.id.actPostActionType);

        tlPostActionLink = findViewById(R.id.tlPostActionLink);
        etPostActionLink = findViewById(R.id.etPostActionLink);

        ivPhoto = findViewById(R.id.ivPhoto);
        tvPhoto = findViewById(R.id.tvPhoto);

        thereIsNoAction();
        db = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please Wait...");

        switchPostAction = findViewById(R.id.switchPostAction);
        switchPostAction.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    post_action = true;
                    thereIsAction();
                } else {
                    post_action = false;
                    thereIsNoAction();
                }
            }
        });

        etPostDesc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tlPostDesc.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        actPostActionType.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tlPostActionType.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etPostActionLink.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tlPostActionLink.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnPublishPost = findViewById(R.id.btnPublishPost);
        btnPublishPost.setOnClickListener(this);

        actionAdapter = new ArrayAdapter<>(CreateNewPostActivity.this, 
                R.layout.support_simple_spinner_dropdown_item, 
                ACTION_TYPE);
        actPostActionType.setAdapter(actionAdapter);
        
        cardPostImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == cardPostImage){
            ImagePicker.Companion.with(this)
                    .crop()	    			//Crop image(Optional), Check Customization for more option
                    .compress(1024)			//Final image size will be less than 1 MB(Optional)
                    .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                    .start();
        }
        
        if (v == btnPublishPost){
            doPostUIValidation();
        }

        if (v == ivGoBack){
            onBackPressed();
            finish();
        }

    }

    private void doPostUIValidation() {
        if (postImageURI == null){
            lvAddPhoto.requestFocus();
            lvAddPhoto.setBackground(getResources().getDrawable(R.drawable.image_dashed_border_error));
            ivPhoto.setImageResource(R.drawable.zp_error);
            tvPhoto.setTextColor(getResources().getColor(R.color.errorColor));
            return;
        }

        String post_description = etPostDesc.getText().toString();
        String post_action_type = actPostActionType.getText().toString();
        String post_action_link = etPostActionLink.getText().toString();

        if (post_description.isEmpty()){
            tlPostDesc.setErrorEnabled(true);
            tlPostDesc.setError("Post description required");
            return;
        }

        if (post_action){
            if (post_action_type.isEmpty()){
                tlPostActionType.setErrorEnabled(true);
                tlPostActionType.setError("Action type required");
                return;
            }

            if (post_action_link.isEmpty()){
                tlPostActionLink.setErrorEnabled(true);
                tlPostActionLink.setError("Action link required");
                return;
            }
        }

        progressDialog.setMessage("Publishing post...");
        progressDialog.show();
        doPublishPost(post_description, post_action, post_action_type, post_action_link);

    }

    private void doPublishPost(String post_description, boolean post_action, String post_action_type, String post_action_link) {
        CollectionReference collectionReference = db.collection("business_posts");
        DocumentReference documentReference = collectionReference.document();
        String post_id = documentReference.getId();

        Posts posts = new Posts();
        posts.setPost_id(post_id);
        posts.setPost_photo(post_photo);
        posts.setPost_description(post_description);
        posts.setPost_action(post_action);
        posts.setPost_action_type(post_action_type);
        posts.setPost_action_link(post_action_link);
        posts.setPost_action_type(post_action_type);
        posts.setPost_user_id(firebaseAuth.getCurrentUser().getUid());
        posts.setPost_user_name(Posts.POST_USER_NAME);
        posts.setPost_user_photo(Posts.POST_USER_PHOTO);
        documentReference.set(posts)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            progressDialog.dismiss();
                            Toast.makeText(CreateNewPostActivity.this, "Post published!", Toast.LENGTH_SHORT).show();
                            onBackPressed();
                            finish();
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(CreateNewPostActivity.this, "Post not published!, Please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(CreateNewPostActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            assert data != null;
            postImageURI = data.getData();
            ivAddPhoto.setImageURI(postImageURI);
            uploadPostPhoto(postImageURI);
            lvAddPhoto.setVisibility(View.GONE);
            ivAddPhoto.setVisibility(View.VISIBLE);
        }

    }

    private void thereIsAction(){
        tlPostActionLink.setVisibility(View.VISIBLE);
        tlPostActionType.setVisibility(View.VISIBLE);
    }

    private void thereIsNoAction(){
        tlPostActionLink.setVisibility(View.GONE);
        tlPostActionType.setVisibility(View.GONE);
    }

    private void uploadPostPhoto(Uri filePath) {

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();


        if (filePath != null) {
            progressDialog.setMessage("Uploading...");
            progressDialog.show();

            final StorageReference ref = storageReference.child("business/" + UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    post_photo = uri.toString();
                                    progressDialog.dismiss();
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(CreateNewPostActivity.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
    
    

}
