package com.example.mj_talk.ui.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.mj_talk.R;

public class HomeViewPager extends AppCompatActivity {
    private int MAX_COUNT = 2; // 화면 갯수
    Fragment current_fragment; // 처음에 보여준 화면(기준)

    HomeFragment homeFragment;
    CalenderFragment calenderFragment;
//    SchedulerFragment schedulerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        calenderFragment = new CalenderFragment();
        homeFragment = new HomeFragment();
//        schedulerFragment = new SchedulerFragment();
        current_fragment = homeFragment;

        ViewPager viewPager = findViewById(R.id.home_viewpager);
        viewPager.setAdapter(new adapter(getSupportFragmentManager()));
    }

    private class adapter extends FragmentPagerAdapter {
        public adapter(FragmentManager fm) {
            super(fm);
        }
            @Override
            public Fragment getItem(int position) {
                if(position < 0 || MAX_COUNT <= position)
                    return null;
                switch (position) {
                    case 0:
                        current_fragment = homeFragment;
                        break;
                    case 1:
                        current_fragment = calenderFragment;
                        break;
                }
                return current_fragment;
            }

            @Override
        public int getCount() {
            return MAX_COUNT;
            }
    }
}
