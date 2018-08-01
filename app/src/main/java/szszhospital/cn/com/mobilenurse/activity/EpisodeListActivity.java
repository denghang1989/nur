package szszhospital.cn.com.mobilenurse.activity;

import android.content.Context;
import android.content.Intent;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BasePresentActivity;

public class EpisodeListActivity extends BasePresentActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_episode_list;
    }

    public static void startEpisodeListActivity(Context context) {
        Intent intent = new Intent(context, EpisodeListActivity.class);
        context.startActivity(intent);
    }
}
