package application.android.com.mymvp.presenter;

import java.util.ArrayList;

import application.android.com.mymvp.bean.Data;

public interface IDataListener {
    /**
     * 记录成功的回调
     */
    void onSuccess(ArrayList<Data> data);

    /**
     * 记录失败的回调
     */
    void onFailed();
}
