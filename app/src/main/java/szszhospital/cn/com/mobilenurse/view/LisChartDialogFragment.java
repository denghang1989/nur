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
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        mChart.setDrawGridBackground(false);
        mChart.setHighlightPerDragEnabled(true);
        mChart.setPinchZoom(true);
        //设置背景颜色
        mChart.setBackgroundColor(Color.WHITE);
        //设置XY轴动画
        mChart.animateXY(1500,1500, Easing.EasingOption.EaseInSine, Easing.EasingOption.EaseInSine);
        mChart.setViewPortOffsets(0f, 0f, 0f, 0f);
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
                            initChartData(lisChartData,mChart);
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

    private void initChartData(List<LisChartData> lisChartData, LineChart chart) {
        LisChartData maxData = Collections.max(lisChartData, new Comparator<LisChartData>() {
            @Override
            public int compare(LisChartData o1, LisChartData o2) {
                return o2.Result.compareToIgnoreCase(o1.Result);
            }
        });

        LisChartData minData = Collections.min(lisChartData, new Comparator<LisChartData>() {
            @Override
            public int compare(LisChartData o1, LisChartData o2) {
                return o2.Result.compareToIgnoreCase(o1.Result);
            }
        });

        //设置取值范围最大值，最小值
        setRangeLimit(lisChartData);

        //设置X轴

        //设置Y轴

        //设置数据

        ArrayList<Entry> yValues = new ArrayList<>();
        for (int i = 0; i < lisChartData.size(); i++) {
            String result = lisChartData.get(i).Result;
            yValues.add(new Entry(i, RegexUtil.getDoubleValue(result)));
        }

        LineDataSet set;
        if (mChart.getData() != null && mChart.getData().getDataSetCount() > 0) {
            set = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set.setValues(yValues);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            //设置值给图表
            set = new LineDataSet(yValues, "");
            //设置图标不显示
            set.setDrawIcons(false);
            //设置Y值使用左边Y轴的坐标值
            set.setAxisDependency(YAxis.AxisDependency.LEFT);
            //设置折线宽度
            set.setLineWidth(1f);
            //设置折现点圆点半径
            set.setCircleRadius(4f);

            //设置显示十字线，必须显示十字线，否则MarkerView不生效
            set.setHighlightEnabled(true);
            //设置是否在数据点中间显示一个孔
            set.setDrawCircleHole(false);

            //设置填充
            //设置允许填充
            set.setDrawFilled(true);
            set.setFillAlpha(50);

            LineData data = new LineData(set);
            //设置不显示数据点的值
            data.setDrawValues(false);

            mChart.setData(data);
            mChart.invalidate();
        }
    }

    /**
     * //设置取值范围最大值，最小值
     * @param lisChartData
     */
    private void setRangeLimit(List<LisChartData> lisChartData) {
        YAxis rightAxis = mChart.getAxisRight();
        LisChartData chartData = lisChartData.get(0);
        String[] strings = chartData.RefRanges.split("-");
        Float min = Float.valueOf(strings[0]);
        Float max = Float.valueOf(strings[1]);

    }

    private void setChartXAxis(List<LisChartData> lisChartData) {
        ArrayList<String> list = new ArrayList<>();
        //自定义设置横坐标
        IAxisValueFormatter xValueFormatter = new FastBrowserXValueFormatter(list);
        //X轴
        XAxis xAxis = mChart.getXAxis();
        //设置线为虚线
        //xAxis.enableGridDashedLine(10f, 10f, 0f);
        //设置字体大小10sp
        xAxis.setTextSize(10f);
        //设置X轴字体颜色
//        xAxis.setTextColor(ColorAndImgUtils.FAST_BW_TEXT_COLOR);
        //设置从X轴发出横线
        xAxis.setDrawGridLines(true);
//        xAxis.setGridColor(ColorAndImgUtils.GRID_COLOR);
        //设置网格线宽度
        xAxis.setGridLineWidth(1);
        //设置显示X轴
        xAxis.setDrawAxisLine(true);
        //设置X轴显示的位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置自定义X轴值
        xAxis.setValueFormatter(xValueFormatter);
        //一个界面显示6个Lable，那么这里要设置11个
        xAxis.setLabelCount(6);
        //设置最小间隔，防止当放大时出现重复标签
        xAxis.setGranularity(1f);
        //设置为true当一个页面显示条目过多，X轴值隔一个显示一个
        xAxis.setGranularityEnabled(true);
        //设置X轴的颜色
//        xAxis.setAxisLineColor(ColorAndImgUtils.GRID_COLOR);
        //设置X轴的宽度
        xAxis.setAxisLineWidth(1f);
    }

    private class FastBrowserXValueFormatter implements IAxisValueFormatter {
        public FastBrowserXValueFormatter(ArrayList<String> list) {
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return "";
        }
    }
}
