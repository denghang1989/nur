package szszhospital.cn.com.mobilenurse.fragemt;

import android.graphics.Rect;
import android.view.View;

import com.nightonke.boommenu.Animation.BoomEnum;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.remote.response.Order;

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
        if (mOrderList != null && mOrderList.size() > 0) {
            switch (index) {
                case 0:
                    List<Order> orders = mOrderList.stream().filter(order -> !"D".equals(order.OrdStatusCode)).collect(Collectors.toList());
                    mAdapter.setNewData(orders);
                    break;
                case 1:
                    mAdapter.setNewData(mOrderList);
                    break;
                default:
                    break;
            }
            mCurrentIndex = index;
        }
    }
}
