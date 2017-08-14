package com.example.administrator.testrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.example.administrator.testrecyclerview.adapter.AAdapter;
import com.necer.nrecyclerview.*;
import com.necer.nrecyclerview.Logg;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private NRecyclerView recycler;

    private AAdapter aAdapter;
    private List<String> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = (NRecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
       // recycler.setLayoutManager(new GridLayoutManager(this, 5));
        list = new ArrayList<>();

        for (int i = 0; i < 40; i++) {
            list.add("iii::" + i);
        }
        aAdapter = new AAdapter(this, list);
        recycler.setAdapter(aAdapter);

        recycler.setOnItemClickListener(new NRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView recyclerView, View itemView, int position) {
                Logg.d("positionposition:::" + position);
            }
        });

        View view = findViewById(R.id.tv_no);
        recycler.setEmptyView(view);

      /*  View header = LayoutInflater.from(this).inflate(R.layout.header, null);
        View header1 = LayoutInflater.from(this).inflate(R.layout.header, null);
        View header2 = LayoutInflater.from(this).inflate(R.layout.header, null);
        View footer = LayoutInflater.from(this).inflate(R.layout.footer, null);
        View footer1 = LayoutInflater.from(this).inflate(R.layout.footer, null);
        View footer2 = LayoutInflater.from(this).inflate(R.layout.footer, null);


        recycler.addHeaderView(header);
        recycler.addHeaderView(header1);
        recycler.addHeaderView(header2);

        recycler.addFooterView(footer);
        recycler.addFooterView(footer1);
        recycler.addFooterView(footer2);*/

    }

    public void header(View view) {
        View header = LayoutInflater.from(this).inflate(R.layout.header, null);

        TextView tv_header = (TextView) header.findViewById(R.id.tv_header);
        tv_header.setText("头部：：" + recycler.getHeaderViewCount());
        recycler.addHeaderView(header);
    }
    public void footer(View view) {
        View footer = LayoutInflater.from(this).inflate(R.layout.footer, null);
        TextView tv_footer = (TextView) footer.findViewById(R.id.tv_footer);
        tv_footer.setText("脚部：：" + recycler.getFooterViewCount());
        recycler.addFooterView(footer);
    }

    public void empty(View view) {
        list.clear();
        /*list.remove(0);
        list.remove(1);*/

        aAdapter.notifyDataSetChanged();

    }


}
