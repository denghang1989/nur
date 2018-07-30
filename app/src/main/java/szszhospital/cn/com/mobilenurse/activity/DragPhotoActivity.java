package szszhospital.cn.com.mobilenurse.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.view.DragPhotoView;

/**
 * 2018/7/28 15
 */
public class DragPhotoActivity extends AppCompatActivity {
    private static final String KEY_DATA = "data";
    private ImageView     mBack;
    private DragPhotoView mDragPhotoView;
    private static final String http = "http://172.18.0.27/dhcemr";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_photo);
        String photoPath = getIntent().getStringExtra(KEY_DATA);
        mBack = findViewById(R.id.back);
        mDragPhotoView = findViewById(R.id.dragPhotoView);
        Glide.with(this).load(http + photoPath).into(mDragPhotoView);
        mBack.setOnClickListener(view -> onBackPressed());
        mDragPhotoView.setOnExitListener((view, translateX, translateY, w, h) -> onBackPressed());
    }
}
