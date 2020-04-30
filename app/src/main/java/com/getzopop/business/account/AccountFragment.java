package com.getzopop.business.account;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.getzopop.business.R;
import com.getzopop.business.account.post.ManagePostActivity;
import com.getzopop.business.account.product.ManageProductActivity;
import com.getzopop.business.account.profile.ViewProfileActivity;

public class AccountFragment extends Fragment implements View.OnClickListener {

    private View view;
    private LinearLayout lhManagePost;
    private LinearLayout lhManageProduct;
    private TextView tvViewEditProfile;

    public AccountFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_account, container, false);
        initView();
        return view;
    }

    private void initView() {
        lhManagePost = view.findViewById(R.id.lhManagePost);
        lhManageProduct = view.findViewById(R.id.lhManageProduct);
        tvViewEditProfile = view.findViewById(R.id.tvViewEditProfile);

        lhManagePost.setOnClickListener(this);
        lhManageProduct.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == lhManagePost){
            startActivity(new Intent(getContext(), ManagePostActivity.class));
        }

        if (v == lhManageProduct){
            startActivity(new Intent(getContext(), ManageProductActivity.class));
        }

        if (v == tvViewEditProfile){
            startActivity(new Intent(getContext(), ViewProfileActivity.class));
        }

    }
}
