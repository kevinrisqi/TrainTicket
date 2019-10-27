package com.example.trainticket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements  BottomNavigationView.OnNavigationItemSelectedListener,
        PemesananFragment.OnFragmentInteractionListener,ScheduleFragment.OnFragmentInteractionListener,ResultFragment.OnFragmentInteractionListener,
    InformationFragment.OnFragmentInteractionListener{
    private ResultFragment resultFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        loadFragment(new PemesananFragment());
        resultFragment = new ResultFragment();

    }


    private boolean loadFragment(Fragment fragment) {
        if(fragment != null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout, fragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()){
            case R.id.action_ticket:
                fragment = new PemesananFragment();
                break;
            case R.id.action_schedule:
                fragment = new ScheduleFragment();
                break;
            case R.id.action_info:
                fragment = new InformationFragment();
                break;
            default:fragment=null;
        }
        return loadFragment(fragment);

    }

    @Override
    public void onSubmitClicked(int total) {
        String simpan = String.valueOf(total);
        ResultFragment.setInfo("Silakan Lakukan Pembayaran ke Kasir sebesar : " +"Rp."+ simpan);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, resultFragment)
                .commit();
    }
}
