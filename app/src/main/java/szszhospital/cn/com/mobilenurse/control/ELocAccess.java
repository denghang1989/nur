package szszhospital.cn.com.mobilenurse.control;


import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;

public class ELocAccess implements LocAccess {
    @Override
    public void toolbarHandler(DrawerLayout layout) {
    }

    @Override
    public void openDrawer(DrawerLayout layout) {
        layout.openDrawer(Gravity.START);
    }
}
