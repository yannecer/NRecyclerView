package com.example.administrator.testrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.example.administrator.testrecyclerview.adapter.AAdapter;
import com.necer.nrecyclerview.NRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NRecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = (NRecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        List<String> list = new ArrayList<>();




        for (int i = 0; i < 15; i++) {
            list.add("iii::" + i);
        }


        recycler.setAdapter(new AAdapter(this,list));

        View header = LayoutInflater.from(this).inflate(R.layout.header, null);
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
        recycler.addFooterView(footer2);
    }

    private void set() {

        ListView listView = new ListView(this);
       // listView.setOnItemClickListener();

    }

}
