package com.example.mj_talk.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.mj_talk.R;
import com.google.android.material.tabs.TabLayout;


public class HomeFragment extends Fragment {

    HomeViewPager homeAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        homeAdapter = new HomeViewPager(getParentFragmentManager());

        ViewPager mViewPager = root.findViewById(R.id.home_viewpager);
        setupViewPager(mViewPager);

        TabLayout tabLayout = root.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        return root;
    }

    public void setupViewPager(ViewPager viewPager) {
        homeAdapter.addFragment(new CalenderFragment(), "달력");
        homeAdapter.addFragment(new SchedulerFragment(), "시간표");
        viewPager.setAdapter(homeAdapter);

    }

}
