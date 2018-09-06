package szszhospital.cn.com.mobilenurse.fragemt;

import android.graphics.Rect;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.nightonke.boommenu.Animation.BoomEnum;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import szszhospital.cn.com.mobilenurse.R;

/**
 * 长期医嘱
 */
public class SOrdersFragment extends BaseOrdersFragment implements OnBMClickListener {

    private static int[]    imageResources = new int[]{
            R.drawable.icon_other_order,
            R.drawable.icon_all_order
    };
    private static String[] normalTextRes  = new String[]{
            "当前医嘱", "全部医嘱"
    };

    private int mCurrentIndex = 0;

    public static SOrdersFragment newInstance() {
        return new SOrdersFragment();
    }

    @Override
    protected String getOrderType() {
        return "S";
    }

    @Override
    protected void initView() {
        super.initView();
        initMenu();
    }

    private void initMenu() {
        mDataBinding.top.setVisibility(View.VISIBLE);
        mDataBinding.top.setBoomEnum(BoomEnum.LINE);
        mDataBinding.top.setButtonEnum(ButtonEnum.TextOutsideCircle);
        mDataBinding.top.setPiecePlaceEnum(PiecePlaceEnum.DOT_2_1);
        mDataBinding.top.setButtonPlaceEnum(ButtonPlaceEnum.SC_2_1);
        for (int i = 0; i < mDataBinding.top.getPiecePlaceEnum().pieceNumber(); i++) {
            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                    .imagePadding(new Rect(20, 20, 20, 20))
                    .normalImageRes(imageResources[i])
                    .listener(this)
                    .normalText(normalTextRes[i])
                    .textSize(16);
            mDataBinding.top.addBuilder(builder);
        }
    }

    @Override
    public void onBoomButtonClick(int index) {
        if (mCurrentIndex != index) {
            switch (index) {
                case 0:
                    initData();
                    break;
                case 1:
                    ToastUtils.showShort("开发规划中");
                    break;
                default:
                    break;
            }
            mCurrentIndex = index;
        }
    }
}
