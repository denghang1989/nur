package szszhospital.cn.com.mobilenurse.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ScreenUtils;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.response.LisChartData;
import szszhospital.cn.com.mobilenurse.remote.response.LisOrderDetail;
import szszhospital.cn.com.mobilenurse.utils.RegexUtil;

public class LisChartDialogFragment extends DialogFragment {
    public static final  String TAG  = "LisChartDialogFragment";
    private static final String DATA = "data";
    private Toolbar        mToolbar;
    private LineChart      mChart;
    private LisOrderDetail mLisOrderDetail;
    private Disposable     mDisposable;

    public static LisChartDialogFragment newInstance(LisOrderDetail lisOrderDetail) {
        Bundle args = new Bundle();
        args.putParcelable(DATA, lisOrderDetail);
        LisChartDialogFragment fragment = new LisChartDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLisOrderDetail = getArguments().getParcelable(DATA);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lis_chart, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mToolbar = view.findViewById(R.id.toolbar);
        mToolbar.setTitle(mLisOrderDetail.TestCodeName);
        mChart = view.findViewById(R.id.chart);
        setChartProperties();
        setXAxisProperties();
        setYAxisProperties();
    }

    private void setYAxisProperties() {
        YAxis axisLeft = mChart.getAxisLeft();
        axisLeft.setSpaceBottom(10);
        axisLeft.setSpaceTop(20);
        YAxis axisRight = mChart.getAxisRight();
        axisRight.setEnabled(false);
    }

    private void setXAxisProperties() {
        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setEnabled(true);
        xAxis.setDrawGridLines(true);
        xAxis.setGranularity(1f);
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setLabelRotationAngle(15f);
    }

    private void setChartProperties() {
        //设置描述文本不显示
        mChart.getDescription().setEnabled(false);
        //设置是否显示表格背景
        mChart.setDrawGridBackground(true);
        //设置是否可以触摸
        mChart.setTouchEnabled(true);
        mChart.setDragDecelerationFrictionCoef(0.9f);
        //设置是否可以拖拽
        mChart.setDragEnabled(true);
        //设置是否可以缩放
        mChart.setScaleEnabled(false);
        mChart.setHighlightPerDragEnabled(true);
        mChart.setPinchZoom(true);
        //设置背景颜色
        mChart.setBackgroundColor(Color.WHITE);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initEvent();
    }

    private void initEvent() {
        mToolbar.setNavigationOnClickListener(v -> dismiss());
    }

    private void initData() {
        mDisposable = ApiService.Instance().getService().getLisChartData(mLisOrderDetail.ReportDR, mLisOrderDetail.TestCodeDR)
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.httpHandleResponse())
                .subscribe(new Consumer<List<LisChartData>>() {
                    @Override
                    public void accept(List<LisChartData> lisChartData) throws Exception {
                        if (lisChartData != null && lisChartData.size() > 0) {
                            Collections.sort(lisChartData);
                            initChartData(lisChartData);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(ScreenUtils.getScreenWidth(), ScreenUtils.getScreenHeight() * 2 / 3);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    private void initChartData(List<LisChartData> lisChartData) {
        String[] strings = lisChartData.get(0).RefRanges.split("-");
        //提取数字
        Float minLimit = RegexUtil.getDoubleValue(strings[0]);
        Float maxLimit = RegexUtil.getDoubleValue(strings[1]);
        //设置取值范围最大值，最小值
        setRangeLimit(minLimit, maxLimit);
        //设置折线
        List<Entry> entryList = new ArrayList<>();
        List<String> xStrings = new ArrayList<>();
        float maxResult = maxLimit;
        float minResult = minLimit;
        for (int i = 0; i < lisChartData.size(); i++) {
            LisChartData data = lisChartData.get(i);
            float result = RegexUtil.getDoubleValue(data.Result);
            Entry entry = new Entry(i, result);
            entryList.add(entry);
            xStrings.add(data.ReportAuthDateTime);
            if (result > maxResult) {
                maxResult = result;
            } else if (result < minResult) {
                minResult = result;
            }
        }
        // 获取top
        float top = Math.max(maxLimit, maxResult);
        // 获取bottom
        float bottom = Math.min(minLimit, minResult);
        float space = (maxLimit - minLimit) / 20;
        bottom = Math.max(bottom - space, 0);
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setAxisMaximum(top + space * 3);
        leftAxis.setAxisMinimum(bottom);

        LineDataSet dataSet = new LineDataSet(entryList, mLisOrderDetail.TestCodeName);
        dataSet.setLineWidth(2f);
        dataSet.setCircleRadius(4f);
        dataSet.setHighLightColor(Color.RED); // 设置点击某个点时，横竖两条线的颜色
        dataSet.setDrawValues(true); // 是否在点上绘制Value
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(12f);
        dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);

        LineData data = new LineData(dataSet);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setValueFormatter((value, axis) -> xStrings.get((int) value));

        mChart.setData(data);
        mChart.invalidate();

    }

    /**
     * @param min
     * @param max
     */
    private void setRangeLimit(float min, float max) {
        LimitLine minLimitLine = new LimitLine(min, "Lower Limit：" + min);
        minLimitLine.setLineColor(getResources().getColor(R.color.blue));
        minLimitLine.enableDashedLine(10f, 10f, 0f);
        minLimitLine.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);

        LimitLine maxLimitLine = new LimitLine(max, "Upper Limit：" + max);
        maxLimitLine.setLineColor(getResources().getColor(R.color.red));
        maxLimitLine.enableDashedLine(10f, 10f, 0f);
        maxLimitLine.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.addLimitLine(minLimitLine);
        leftAxis.addLimitLine(maxLimitLine);
    }

}
