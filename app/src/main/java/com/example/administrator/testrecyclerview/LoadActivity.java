package com.example.administrator.testrecyclerview;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import com.example.administrator.testrecyclerview.adapter.LAdapter;
import com.necer.nrecyclerview.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 闫彬彬 on 2017/8/21.
 * QQ:619008099
 */

public class LoadActivity extends Activity {

    private GyRecyclerView recycler;
    private GyAdapter<String> adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        recycler = (GyRecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LAdapter(this);
        recycler.setAdapter(adapter);
        recycler.setOnLoadMoreListener(new GyRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                com.necer.nrecyclerview.Logg.d("加载更多");
            }
        });
    }

    public void add(View v) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            list.add("sssss::" + i);
        }
        adapter.setData(list,20);
    }
    public void add1(View v) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            list.add("sssss::" + i);
        }
        adapter.setData(list,5);
    }

    public void stop(View view) {
        recycler.stopLoadMore();

    }

    public void header(View view) {

        View header = LayoutInflater.from(this).inflate(R.layout.header, null);
        adapter.addHeaderView(header);
    }
    public void footer(View view) {
        View footer = LayoutInflater.from(this).inflate(R.layout.footer, null);
        adapter.addFooterView(footer);

    }
}
