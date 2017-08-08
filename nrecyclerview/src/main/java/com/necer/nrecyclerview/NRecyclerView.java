package com.necer.nrecyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/8.
 */

public class NRecyclerView extends RecyclerView{

    private List<View> mHeaderList;
    private List<View> mFooterList;


    private Adapter mAdapter;


    public NRecyclerView(Context context) {
        super(context);
    }

    public NRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }


    private void init() {
        mHeaderList = new ArrayList<>();
        mFooterList = new ArrayList<>();
    }

    public void addHeaderView(View headerView) {
        mHeaderList.add(headerView);
        mAdapter.notifyDataSetChanged();
    }


    public void addFooterView(View footerView) {
        mFooterList.add(footerView);
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void setAdapter(Adapter adapter) {
        mAdapter = new HeadFootAdapter(mHeaderList, mFooterList, adapter);
        super.setAdapter(mAdapter);
    }
}
