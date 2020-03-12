package szszhospital.cn.com.mobilenurse.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

import szszhospital.cn.com.mobilenurse.factory.FragmentFactory;
import szszhospital.cn.com.mobilenurse.remote.response.LocAccess;

public class MainFragmentAdapter extends FragmentStatePagerAdapter {

    public List<LocAccess> mList;

    public MainFragmentAdapter(FragmentManager fm, List<LocAccess> list) {
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
        return mList == null ? super.getPageTitle(position) : mList.get(position).Title;
    }
}
