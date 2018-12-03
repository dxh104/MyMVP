package application.android.com.mymvp.model;

import application.android.com.mymvp.presenter.IDataListener;

public interface IDataModel {
    void loadData(IDataListener listener);//加载图片文字
}
