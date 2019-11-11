package com.promobileapp.chiasenhac.view.activity;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.promobileapp.chiasenhac.R;
import com.promobileapp.chiasenhac.adapter.PagerAdapter;
import com.promobileapp.chiasenhac.base.BaseActivity;
import com.promobileapp.chiasenhac.view.fragment.category.CategoryFragment;
import com.promobileapp.chiasenhac.view.fragment.home.HomeFragment;
import com.promobileapp.chiasenhac.view.fragment.newalbum.NewAlbumFragment;
import com.promobileapp.chiasenhac.view.fragment.newsong.NewSongFragment;
import com.promobileapp.chiasenhac.widget.SwipeViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @BindView(R.id.pager)
    SwipeViewPager viewPager;

    @BindView(R.id.tabLayout)
    BottomNavigationView navigation;

    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initTab();
    }

    public void initTab() {
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new HomeFragment(), getString(R.string.home));
        pagerAdapter.addFragment(new CategoryFragment(), getString(R.string.category));
        pagerAdapter.addFragment(new NewAlbumFragment(), getString(R.string.new_album));
        pagerAdapter.addFragment(new NewSongFragment(), getString(R.string.new_song));
        viewPager.setAdapter(pagerAdapter);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.nav_category:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.nav_newAlbum:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.nav_newSong:
                        viewPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });

    }
}