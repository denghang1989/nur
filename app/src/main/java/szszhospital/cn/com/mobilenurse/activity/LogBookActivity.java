package szszhospital.cn.com.mobilenurse.activity;

import android.app.Activity;
import android.content.Intent;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BaseActivity;
import szszhospital.cn.com.mobilenurse.fragemt.LogBookFragment;

public class LogBookActivity extends BaseActivity {

    public static void StartLogBookActivity(Activity activity){
        activity.startActivity(new Intent(activity, LogBookActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_log_book;
    }

    @Override
    protected void initView() {
        super.initView();
        loadRootFragment(R.id.content,LogBookFragment.newInstance());
    }
}
