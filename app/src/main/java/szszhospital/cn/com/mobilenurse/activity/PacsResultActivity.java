package szszhospital.cn.com.mobilenurse.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BasePresentActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActivityPacsResultBinding;
import szszhospital.cn.com.mobilenurse.mvp.contract.PacsResultContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.PacsResultPresenter;
import szszhospital.cn.com.mobilenurse.remote.response.PacsOrder;
import szszhospital.cn.com.mobilenurse.remote.response.PacsResult;

public class PacsResultActivity extends BasePresentActivity<ActivityPacsResultBinding, PacsResultPresenter> implements PacsResultContract.View {

    private static final String KEY_DATA = "data";
    private static final String TAG      = "PacsResultActivity";
    private PacsOrder mPacsOrder;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pacs_result;
    }

    public static void startPacsResultActivity(Context context, PacsOrder pacsorder) {
        Intent intent = new Intent(context, PacsResultActivity.class);
        intent.putExtra(KEY_DATA, (Parcelable) pacsorder);
        context.startActivity(intent);
    }

    @Override
    protected void init() {
        super.init();
        mPacsOrder = getIntent().getParcelableExtra(KEY_DATA);
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getPacsResult(mPacsOrder.TOEOrderDr);
    }

    @Override
    protected PacsResultPresenter initPresenter() {
        return new PacsResultPresenter();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showPacsResult(PacsResult pacsResult) {
        Log.d(TAG, "showPacsResult: " + pacsResult.toString());
    }
}
