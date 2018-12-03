package application.android.com.mymvp.view;

import java.util.ArrayList;
import application.android.com.mymvp.bean.Data;

public interface IMainActivity {
    /**
     * 显示进度条
     */
    void showLoading();

    /**
     *隐藏进度条
     */
    void dismissLoading();

    /**
     * 显示更新失败
     */
    void showError();

    /**
     * 更新UI
     * @param Data
     */
    void setData(ArrayList<Data> dataArrayList);
}
