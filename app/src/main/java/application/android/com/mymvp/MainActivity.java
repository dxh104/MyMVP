package application.android.com.mymvp;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import application.android.com.mymvp.adapter.MyAdapter;
import application.android.com.mymvp.bean.Data;
import application.android.com.mymvp.presenter.IDataPresenter;
import application.android.com.mymvp.presenter.impl.DataPresenterImpl;
import application.android.com.mymvp.view.IMainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IMainActivity {
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.btn_submit)
    Button btn_submit;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    private IDataPresenter presenter;
    public UIHandler uiHandler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initUI();
        presenter = new DataPresenterImpl(uiHandler, this);

    }

    private void initUI() {
        progressBar.setVisibility(View.INVISIBLE);
        btn_submit.setOnClickListener(this);
        uiHandler = new UIHandler(this);

    }

    @Override
    public void onClick(View v) {
        showLoading();
//        System.exit(0);
    }

    /**
     * 显示进度条
     */
    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        btn_submit.setEnabled(false);
        presenter.getData();
    }

    /**
     * 隐藏进度条
     */
    @Override
    public void dismissLoading() {
        progressBar.setVisibility(View.INVISIBLE);
        btn_submit.setEnabled(true);
    }

    /**
     * 显示更新失败
     */
    @Override
    public void showError() {
        Toast.makeText(getApplicationContext(), "数据获取失败", Toast.LENGTH_SHORT).show();

    }

    /**
     * 更新UI
     *
     * @param Data
     */
    @Override
    public void setData(ArrayList<Data> dataArrayList) {
        MyAdapter myAdapter = new MyAdapter(dataArrayList, this);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(myAdapter);
    }

    private static class UIHandler extends Handler {
        private WeakReference<MainActivity> reference = null;

        public UIHandler(MainActivity mainActivity) {
            reference = new WeakReference<>(mainActivity);//弱引用MainActivity-->直接在下面加载数据适配器(依赖mainActivity)
        }

        public void handleMessage(Message msg) {
            int what = msg.what;
            MainActivity mainActivity = reference.get();
            mainActivity.dismissLoading();//隐藏进度条
            ArrayList<Data> data;
            switch (what) {
                //0x123:代表数据更新成功，进行刷新数据操作
                case 0x123:
                    data = (ArrayList<Data>) msg.obj;
                    mainActivity.setData(data);
//                    Toast.makeText(mainActivity, data.get(0).getImagePath(), Toast.LENGTH_SHORT).show();
                    break;
                //0x123：数据更新失败，提升用户
                case 0x000:
                    mainActivity.showError();
                    break;
            }
            super.handleMessage(msg);
        }
    }

}
