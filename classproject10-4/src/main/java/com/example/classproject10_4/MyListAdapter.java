package com.example.classproject10_4;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUpdateDialog(holder.getAdapterPosition(), holder);
                updateDialog.show();
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

    public void showUpdateDialog(int position, Memo_ViewHolder holder) {
        EditText phone = new EditText(context);
        phone.setText(arrayList.get(position).getPhone());
        phone.setHint("输入新的手机号");
        phone.setMaxLines(1);
        phone.setMaxLines(11);
        phone.setInputType(InputType.TYPE_CLASS_NUMBER);
        updateDialog.setTitle("更新手机号").setView(phone).setCancelable(false).setMessage("确定要更新吗?").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                User user = arrayList.get(position);
                user.setPhone(phone.getText().toString().trim());
                userDao.updateMemo(user);
                arrayList.remove(holder.getAdapterPosition());
                arrayList.add(user);
                notifyItemRangeChanged(holder.getAdapterPosition(), arrayList.size());
                System.out.println("调用-=-=-=");
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "取消更新", Toast.LENGTH_SHORT).show();
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
        private Button update;

        public Memo_ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = (TextView) itemView.findViewById(R.id.username);
            phone = (TextView) itemView.findViewById(R.id.phone);
            delete = (Button) itemView.findViewById(R.id.delete);
            update = (Button) itemView.findViewById(R.id.update);

        }
    }
}