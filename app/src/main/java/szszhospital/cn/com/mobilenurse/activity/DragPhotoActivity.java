package szszhospital.cn.com.mobilenurse.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.BarUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;

import java.util.ArrayList;

import me.yokeyword.fragmentation.SwipeBackLayout;
import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.view.FixMultiViewPager;

import static szszhospital.cn.com.mobilenurse.utils.Contants.PHOTO_PATH;

/**
 * 2018/7/28 15
 */
public class DragPhotoActivity extends SwipeBackActivity {
    private static final String KEY_DATA  = "data";
    private static final String KEY_INDEX = "index";
    private ArrayList<String> mPhotoPaths;
    private int               mIndex;
    private FixMultiViewPager mViewPager;
    private PageIndicatorView mPageIndicatorView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BarUtils.setStatusBarAlpha(this,0);
        setContentView(R.layout.activity_drag_photo);
        getSwipeBackLayout().setEdgeOrientation(SwipeBackLayout.EDGE_LEFT);
        init();
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mPageIndicatorView.setSelection(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        mViewPager = findViewById(R.id.viewPager);
        mPageIndicatorView = findViewById(R.id.pageIndicatorView);
        mPageIndicatorView.setCount(mPhotoPaths.size());
        mPageIndicatorView.setSelection(mIndex);
        mPageIndicatorView.setAnimationType(AnimationType.THIN_WORM);
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mPhotoPaths.size();
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                NestedScrollView nestedScrollview = (NestedScrollView) LayoutInflater.from(DragPhotoActivity.this).inflate(R.layout.item_drag_photo, container,false);
                SubsamplingScaleImageView dragPhotoView = nestedScrollview.findViewById(R.id.dragPhotoView);
                Glide.with(DragPhotoActivity.this).asBitmap().load(PHOTO_PATH + mPhotoPaths.get(position)).into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        dragPhotoView.setImage(ImageSource.bitmap(resource));
                    }
                });
                container.addView(nestedScrollview);
                return nestedScrollview;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }


        });
        mViewPager.setCurrentItem(mIndex);
    }

    private void initData() {

    }

    private void init() {
        mPhotoPaths = getIntent().getStringArrayListExtra(KEY_DATA);
        mIndex = getIntent().getIntExtra(KEY_INDEX, 0);
    }
}
