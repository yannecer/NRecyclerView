package com.necer.nrecyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewParent;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/8.
 */

public class NRecyclerView extends RecyclerView{

    private Adapter mAdapter;
    private SparseArray mHeaderSparseArray;
    private SparseArray mFooterSparseArray;
    private static final int ITEM_VIEW_TYPE_HEADER_INDEX = 10000000;
    private static final int ITEM_VIEW_TYPE_FOOTER_INDEX = 20000000;

    public NRecyclerView(Context context) {
        super(context);
        init();
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
        mHeaderSparseArray = new SparseArray();
        mFooterSparseArray = new SparseArray();
    }

    public void addHeaderView(View headerView) {
        mHeaderSparseArray.put(mHeaderSparseArray.size()+ITEM_VIEW_TYPE_HEADER_INDEX,headerView);
        mAdapter.notifyDataSetChanged();
    }


    public void addFooterView(View footerView) {
        mFooterSparseArray.put(mFooterSparseArray.size() + ITEM_VIEW_TYPE_FOOTER_INDEX, footerView);
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void setAdapter(Adapter adapter) {
        mAdapter = new HeadFootAdapter(mHeaderSparseArray, mFooterSparseArray, adapter);
        super.setAdapter(mAdapter);
    }

    public int getHeaderViewCount() {
        return mHeaderSparseArray.size();
    }
    public int getFooterViewCount() {
        return mFooterSparseArray.size();
    }
}
