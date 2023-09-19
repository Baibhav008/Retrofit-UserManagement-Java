package com.example.retrouser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ViewPager2 viewPager;
    BottomNavigationView bottomNavigationView;
    ArrayList<Fragment> fragmentArrayList =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        bottomNavigationView=findViewById(R.id.bottomNav);
        viewPager=findViewById(R.id.pager);
        fragmentArrayList.add(new DashboardFragment());
        fragmentArrayList.add(new UsersFragment());
        fragmentArrayList.add(new ProfileFragment());

        FragmentAdapter adapter = new FragmentAdapter(this,fragmentArrayList);
        viewPager.setAdapter(adapter);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position)
                {
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.dashboard);
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.users);
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.profile);
                        break;

                }

                super.onPageSelected(position);
            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.dashboard:
                        viewPager.setCurrentItem(0);
                        break;

                    case R.id.users:
                        viewPager.setCurrentItem(1);
                        break;

                    case R.id.profile:
                        viewPager.setCurrentItem(2);
                        break;

                }


                return true;
            }
        });


    }
}