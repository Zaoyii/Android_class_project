package com.zcyi.classproject11_14;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.Memo_ViewHolder> {

    ArrayList<Goods> list;
    Context context;
    String baseUrl = "http://10.177.7.73:8080/myweb/goods";

    public MyListAdapter(ArrayList<Goods> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyListAdapter.Memo_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Memo_ViewHolder(View.inflate(context, R.layout.list_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull MyListAdapter.Memo_ViewHolder holder, int position) {
        System.out.println(list.get(position).getGoodsPic()+"-=-===-=-=");
        Glide.with(context).load(baseUrl + list.get(position).getGoodsPic()).fitCenter().into(holder.imageView);

        holder.title.setText(list.get(position).getGoodsName());
        holder.count.setText(list.get(position).getCount());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Memo_ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        TextView count;

        public Memo_ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.img);
            count = itemView.findViewById(R.id.count);
        }
    }
}
