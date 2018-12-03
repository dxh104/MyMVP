package application.android.com.mymvp.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import application.android.com.mymvp.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public ImageView article_item_iv;
    public TextView article_item_tv;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        article_item_iv= itemView.findViewById(R.id.article_item_iv);
        article_item_tv= itemView.findViewById(R.id.article_item_tv);
    }
}
