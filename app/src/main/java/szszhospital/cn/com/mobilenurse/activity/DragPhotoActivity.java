package szszhospital.cn.com.mobilenurse.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;

import java.util.ArrayList;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.view.DragPhotoView;
import szszhospital.cn.com.mobilenurse.view.FixMultiViewPager;

import static szszhospital.cn.com.mobilenurse.utils.Contants.PHOTO_PATH;

/**
 * 2018/7/28 15
 */
public class DragPhotoActivity extends AppCompatActivity {
    private static final String KEY_DATA  = "data";
    private static final String KEY_INDEX = "index";
    private ArrayList<String> mPhotoPaths;
    private int               mIndex;
    private FixMultiViewPager mViewPager;
    private PageIndicatorView mPageIndicatorView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_photo);
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
                DragPhotoView dragPhotoView = (DragPhotoView) LayoutInflater.from(DragPhotoActivity.this).inflate(R.layout.item_drag_photo, null);
                Glide.with(DragPhotoActivity.this).load(PHOTO_PATH + mPhotoPaths.get(position)).into(dragPhotoView);
                dragPhotoView.setOnExitListener((view, translateX, translateY, w, h) -> onBackPressed());
                container.addView(dragPhotoView);
                return dragPhotoView;
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
    }

    private void initData() {

    }

    private void init() {
        mPhotoPaths = getIntent().getStringArrayListExtra(KEY_DATA);
        mIndex = getIntent().getIntExtra(KEY_INDEX, 0);
    }
}
