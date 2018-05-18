package szszhospital.cn.com.mobilenurse.factory;

import szszhospital.cn.com.mobilenurse.base.BaseFragment;
import szszhospital.cn.com.mobilenurse.db.ModuleTable;
import szszhospital.cn.com.mobilenurse.fragemt.DispensingFragment;

public class FragmentFactory {

    public static BaseFragment newInstance(ModuleTable moduleTable) {
        BaseFragment fragment = null;
        switch (moduleTable.className) {
            case "DispensingFragment":
                fragment = DispensingFragment.newInstance();
                break;
        }
        return fragment;
    }
}
