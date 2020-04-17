package com.getzopop.business;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.getzopop.business.console.ConsoleFragment;
import com.getzopop.business.manage.ManageFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private TextView tvMenuTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        BottomNavigationView navFooter = findViewById(R.id.navFooter);
        tvMenuTitle = findViewById(R.id.tvMenuTitle);
        tvMenuTitle.setText(R.string.console);
        navFooter.setOnNavigationItemSelectedListener(this);
        navFooter.setSelectedItemId(R.id.f_m_console);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {

            case R.id.f_m_console:
                fragment = new ConsoleFragment();
                tvMenuTitle.setText(R.string.console);
                break;

            case R.id.f_m_manage:
                fragment = new ManageFragment();
                tvMenuTitle.setText(R.string.manage);
                break;

        }

        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_main, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
