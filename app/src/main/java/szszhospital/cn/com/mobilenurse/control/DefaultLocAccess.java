package szszhospital.cn.com.mobilenurse.control;


import android.support.v4.widget.DrawerLayout;

public class DefaultLocAccess implements LocAccess {
    @Override
    public void toolbarHandler(DrawerLayout layout) {
        layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    @Override
    public void openDrawer(DrawerLayout layout) {
    }
}
