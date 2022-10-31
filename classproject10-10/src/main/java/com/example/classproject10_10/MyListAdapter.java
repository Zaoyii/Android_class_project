package com.example.classproject10_10;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classproject10_10.DB.User;
import com.example.classproject10_10.DB.UserDao;

import java.util.ArrayList;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.Memo_ViewHolder> {
    ArrayList<User> arrayList;
    Context context;

    AlertDialog.Builder deleteDialog;
    AlertDialog.Builder updateDialog;
    UserDao userDao;

    public MyListAdapter(ArrayList<User> arrayList, Context context, UserDao userDao) {
        this.arrayList = arrayList;
        this.context = context;
        deleteDialog = new AlertDialog.Builder(context);
        updateDialog = new AlertDialog.Builder(context);
        this.userDao = userDao;
        init();
    }

    private void init() {

    }

    @NonNull
    @Override
    public Memo_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Memo_ViewHolder(View.inflate(context, R.layout.list_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull Memo_ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.username.setText(arrayList.get(position).getUsername());
        holder.phone.setText(arrayList.get(position).getPhone());
        System.out.println("-=-=-=-=-" + arrayList);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteDialog(holder.getAdapterPosition(), holder);
                deleteDialog.show();
            }
        });
    }

    public void showDeleteDialog(int position, Memo_ViewHolder holder) {

        deleteDialog.setTitle("删除此条通讯录").setMessage("确定要删除吗?").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                userDao.DeleteMemo(arrayList.get(position));
                arrayList.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                notifyItemRangeChanged(holder.getAdapterPosition(), arrayList.size());
                System.out.println("调用-=-=-=");
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "取消删除", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Memo_ViewHolder extends RecyclerView.ViewHolder {
        private TextView username;
        private TextView phone;
        private Button delete;

        public Memo_ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = (TextView) itemView.findViewById(R.id.username);
            phone = (TextView) itemView.findViewById(R.id.phone);
            delete = (Button) itemView.findViewById(R.id.delete);
        }
    }
}