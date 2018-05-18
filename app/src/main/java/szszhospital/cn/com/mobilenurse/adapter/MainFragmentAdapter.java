package szszhospital.cn.com.mobilenurse.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import szszhospital.cn.com.mobilenurse.factory.FragmentFactory;
import szszhospital.cn.com.mobilenurse.db.ModuleTable;

public class MainFragmentAdapter extends FragmentPagerAdapter {

    public List<ModuleTable> mList;

    public MainFragmentAdapter(FragmentManager fm, List<ModuleTable> list) {
        super(fm);
        mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.newInstance(mList.get(position));
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mList == null ? super.getPageTitle(position) : mList.get(position).moduleDes;
    }
}
