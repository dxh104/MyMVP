package application.android.com.mymvp.presenter.impl;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import java.util.ArrayList;
import application.android.com.mymvp.bean.Data;
import application.android.com.mymvp.model.IDataModel;
import application.android.com.mymvp.model.impl.DataModelImpl;
import application.android.com.mymvp.presenter.IDataListener;
import application.android.com.mymvp.presenter.IDataPresenter;

public class DataPresenterImpl implements IDataListener,IDataPresenter {
    private Handler handler;
    private IDataModel dataModel;

    public DataPresenterImpl(Handler handler, Context context) {
        this.handler = handler;
        dataModel = new DataModelImpl(context);
    }


    @Override
    public void onSuccess(ArrayList<Data> data) {
        Message msg = handler.obtainMessage();
        msg.obj = data;
        msg.what = 0x123;
        handler.sendMessage(msg);
    }

    @Override
    public void onFailed() {
    handler.sendEmptyMessage(0x000);
    }

    @Override
    public void getData() {
        dataModel.loadData(this);
    }
}
