package szszhospital.cn.com.mobilenurse.entity.lisdetail;

public class Cell {
    private String  mId;
    private String  mData;
    private boolean isFlag;

    public Cell(String id) {
        this.mId = id;
    }

    public Cell(String id, String data) {
        mId = id;
        mData = data;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getData() {
        return mData;
    }

    public void setData(String data) {
        mData = data;
    }

    public boolean isFlag() {
        return isFlag;
    }

    public void setFlag(boolean flag) {
        isFlag = flag;
    }
}
