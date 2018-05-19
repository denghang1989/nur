package szszhospital.cn.com.mobilenurse.activity;

import android.app.Activity;
import android.content.Intent;

import com.blankj.utilcode.util.SizeUtils;

import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BaseActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActivityQrcodeBinding;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;

/**
 * 2018/5/20 00
 */
public class QRCodeActivity extends BaseActivity<ActivityQrcodeBinding> {

    private static final String KEY_QR_CODE = "qr_code";
    private String     mQrCode;
    private Disposable mDisposable;

    public static void startQRCodeActivity(Activity context, String billNumber) {
        Intent intent = new Intent(context, QRCodeActivity.class);
        intent.putExtra(KEY_QR_CODE, billNumber);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_qrcode;
    }

    @Override
    protected void init() {
        super.init();
        Intent intent = getIntent();
        mQrCode = intent.getStringExtra(KEY_QR_CODE);
    }

    @Override
    protected void initView() {
        mDataBinding.drugNumber.setText(mQrCode);
    }

    @Override
    protected void initData() {
        mDisposable = Observable.just(mQrCode).
                map(s -> QRCodeEncoder.syncEncodeQRCode(s, SizeUtils.dp2px(150)))
                .compose(RxUtil.rxSchedulerHelper())
                .subscribe(bitmap -> mDataBinding.drugImage.setImageBitmap(bitmap));
    }

    @Override
    protected void initEvent() {
        mDataBinding.toolbarCode.setNavigationOnClickListener(view -> finish());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
