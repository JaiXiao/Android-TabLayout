package com.example.jai_xiao.tablayouttest.activity;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;

import com.example.jai_xiao.tablayouttest.R;
import com.example.jai_xiao.tablayouttest.fragment.TabContentFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabTl;
    private ViewPager mContentVp;

    private List<String> tabIndicators;
    private List<Fragment> tabFragments;
    private ContentPagerAdapter contentAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabTl = (TabLayout)findViewById(R.id.tl_tab);
        mContentVp = (ViewPager)findViewById(R.id.vp_content);

        initContent();
        initTab();
    }

    // 初始化ViewPager的内容
    private void initContent(){
        tabIndicators = new ArrayList<>();
        tabFragments = new ArrayList<>();

        for(int i=0; i < 3; i++){
            tabIndicators.add("Fragment"+i);
        }
        for(String s: tabIndicators){
            tabFragments.add(TabContentFragment.newInstance(s));
        }
        contentAdapter = new ContentPagerAdapter(getSupportFragmentManager());
        mContentVp.setAdapter(contentAdapter);
    }

    // 初始化底部导航栏
    private void initTab(){
        mTabTl.setTabMode(TabLayout.MODE_FIXED);
        mTabTl.setSelectedTabIndicatorHeight(0);
        ViewCompat.setElevation(mTabTl, 10);
        mTabTl.setupWithViewPager(mContentVp);
        mTabTl.getTabAt(0).setCustomView(R.layout.item_tab_layout_home);
        mTabTl.getTabAt(1).setCustomView(R.layout.item_tab_layout_order);
        mTabTl.getTabAt(2).setCustomView(R.layout.item_tab_layout_me);

        mTabTl.getTabAt(0).getCustomView().setSelected(true);

    }

    class ContentPagerAdapter extends FragmentPagerAdapter{

        public ContentPagerAdapter(android.support.v4.app.FragmentManager fm){
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return tabFragments.get(position);
        }

        @Override
        public int getCount() {

            return tabIndicators.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return tabIndicators.get(position);
        }

    }

}
