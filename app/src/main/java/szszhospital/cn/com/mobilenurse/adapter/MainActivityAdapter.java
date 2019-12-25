package szszhospital.cn.com.mobilenurse.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import szszhospital.cn.com.mobilenurse.fragemt.MainFragment;

/**
 * 目前开发一个模块
 */
public class MainActivityAdapter extends FragmentPagerAdapter {
    public MainActivityAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return MainFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 1;
    }
}
