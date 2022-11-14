package com.zcyi.classproject11_14;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.zcyi.classproject11_14.databinding.ActivityMainBinding;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    MyViewModel viewModel;

    Retrofit retrofit;
    MyService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MyViewModel.class);
        binding.setVm(viewModel);
        binding.setLifecycleOwner(this);
        retrofit = new Retrofit.Builder().baseUrl("http://10.177.7.73:8080")
                .addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient()).build();
        service = retrofit.create(MyService.class);
        getJson();

    }

    private void getJson() {
        Call<ArrayList<Goods>> json = service.getJson();
        json.enqueue(new Callback<ArrayList<Goods>>() {
            @Override
            public void onResponse(Call<ArrayList<Goods>> call, Response<ArrayList<Goods>> response) {
                ArrayList<Goods> body = response.body();
                System.out.println(body.size());
                System.out.println(response.body());
                binding.list.setAdapter(new MyListAdapter(body, getApplicationContext()));
                binding.list.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
            }

            @Override
            public void onFailure(Call<ArrayList<Goods>> call, Throwable t) {

            }
        });

    }
}