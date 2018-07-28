package szszhospital.cn.com.mobilenurse.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.view.DragPhotoView;

/**
 * 2018/7/28 15
 */
public class DragPhotoActivity extends AppCompatActivity {

    private ImageView     mBack;
    private DragPhotoView mDragPhotoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_photo);

        mBack = findViewById(R.id.back);
        mDragPhotoView = findViewById(R.id.dragPhotoView);

        mBack.setOnClickListener(view -> finish());

        mDragPhotoView.setOnExitListener((view, translateX, translateY, w, h) -> view.finishAnimationCallBack());
    }
}
