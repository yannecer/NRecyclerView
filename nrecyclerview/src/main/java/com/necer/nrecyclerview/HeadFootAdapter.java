package com.necer.nrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/8.
 */

public class HeadFootAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<View> mHeaderList;
    private List<View> mFooterList;

    private List<Integer> mHeaderViewTypes;
    private List<Integer> mFooterViewTypes;

    private RecyclerView.Adapter mAdapter;
    private static final int ITEM_VIEW_TYPE_HEADER_OR_FOOTER_INDEX = 1000000;

    public HeadFootAdapter(List<View> headerList, List<View> footerList, RecyclerView.Adapter adapter) {
        this.mHeaderList = headerList;
        this.mFooterList = footerList;
        this.mAdapter = adapter;

        mHeaderViewTypes = new ArrayList<>();
        mFooterViewTypes = new ArrayList<>();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //头部
        if (mHeaderViewTypes.contains(viewType)) {
            View headerView = mHeaderList.get(viewType - ITEM_VIEW_TYPE_HEADER_OR_FOOTER_INDEX);
            headerView.setLayoutParams(getLayoutParams());
            return new HeadFootViewHolder(headerView);
        }

        //脚部
        if (mFooterViewTypes.contains(viewType)) {
            int index = viewType - ITEM_VIEW_TYPE_HEADER_OR_FOOTER_INDEX - mAdapter.getItemCount() - mHeaderList.size();
            View footerView = mFooterList.get(index);
            if (footerView != null) {
                footerView.setLayoutParams(getLayoutParams());
                return new HeadFootViewHolder(footerView);
            }
        }

        return mAdapter.onCreateViewHolder(parent, viewType);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (!(holder instanceof HeadFootViewHolder)) {
            mAdapter.onBindViewHolder(holder,position-mHeaderList.size());
        }
    }

    @Override
    public int getItemCount() {
        return mHeaderList.size() + mAdapter.getItemCount() + mFooterList.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (mHeaderList.size() > 0 && position < mHeaderList.size()) {
            mHeaderViewTypes.add(position + ITEM_VIEW_TYPE_HEADER_OR_FOOTER_INDEX);
            return position + ITEM_VIEW_TYPE_HEADER_OR_FOOTER_INDEX;
        }

        if (mFooterList.size() > 0 && position >= mHeaderList.size() + mAdapter.getItemCount()) {
            mFooterViewTypes.add(position + ITEM_VIEW_TYPE_HEADER_OR_FOOTER_INDEX);
            return position + ITEM_VIEW_TYPE_HEADER_OR_FOOTER_INDEX;
        }

        return mAdapter.getItemViewType(position);
    }

    static class HeadFootViewHolder extends RecyclerView.ViewHolder {
        public HeadFootViewHolder(View itemView) {
            super(itemView);
        }
    }


    private ViewGroup.LayoutParams getLayoutParams() {
        return new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}

