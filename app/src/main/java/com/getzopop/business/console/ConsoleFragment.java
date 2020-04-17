package com.getzopop.business.console;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.getzopop.business.R;
import com.getzopop.business.customer.view.AddCustomerActivity;

public class ConsoleFragment extends Fragment implements View.OnClickListener {

    private View view;
    private LinearLayout lvAddCustomer;

    public ConsoleFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_console, container, false);
        init();
        return view;
    }

    private void init() {
        lvAddCustomer = (LinearLayout) view.findViewById(R.id.lvAddCustomer);

        lvAddCustomer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == lvAddCustomer){
            startActivity(new Intent(getContext(), AddCustomerActivity.class));
        }
    }
}
