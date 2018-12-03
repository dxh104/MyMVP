package application.android.com.mymvp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import application.android.com.mymvp.R;
import application.android.com.mymvp.bean.Data;
import application.android.com.mymvp.viewholder.MyViewHolder;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{
    private ArrayList<Data> arrayList;
    private Context context;

    public MyAdapter(ArrayList<Data> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.article_item, viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Data data = arrayList.get(i);
        myViewHolder.article_item_tv.setText(data.getTitle());
        Glide.with(context).load(data.getImagePath()).into(myViewHolder.article_item_iv);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
