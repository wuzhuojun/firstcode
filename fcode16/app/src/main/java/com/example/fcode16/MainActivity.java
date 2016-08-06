package com.example.fcode16;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.fcode16.fragment.FragmentA;
import com.example.fcode16.fragment.FragmentB;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(android.R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolBar);

        mViewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new FragmentA();
                    case 1:
                        return new FragmentB();
                    default:
                        return new FragmentA();
                }
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "Page 1";
                    case 1:
                        return "Page 2";
                    default:
                        return "Page 1";
                }
            }
        });
        tabLayout.setupWithViewPager(mViewPager);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
