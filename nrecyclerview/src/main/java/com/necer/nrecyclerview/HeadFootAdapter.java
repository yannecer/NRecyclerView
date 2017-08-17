package com.necer.nrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2017/8/8.
 */

public class HeadFootAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private SparseArray mHeaderSparseArray;
    private SparseArray mFooterSparseArray;
    private RecyclerView.Adapter mAdapter;
    private NRecyclerView mNRecyclerView;

    public static final int ITEM_VIEW_TYPE_REFRESH_INDEX = 30000000;

    public HeadFootAdapter(SparseArray headerSparseArray, SparseArray footerSparseArray, RecyclerView.Adapter adapter) {
        this.mHeaderSparseArray = headerSparseArray;
        this.mFooterSparseArray = footerSparseArray;
        this.mAdapter = adapter;
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.mNRecyclerView = (NRecyclerView) recyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderSparseArray.get(viewType) != null) {
            View headerView = (View) mHeaderSparseArray.get(viewType);
            headerView.setLayoutParams(getLayoutParams());
            return new HeadFootViewHolder(headerView);
        } else if (mFooterSparseArray.get(viewType) != null) {
            View footerView = (View) mFooterSparseArray.get(viewType);
            footerView.setLayoutParams(getLayoutParams());
            return new HeadFootViewHolder(footerView);
        } else {
            return mAdapter.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (!(holder instanceof HeadFootViewHolder)) {
            mAdapter.onBindViewHolder(holder, position - mHeaderSparseArray.size());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mNRecyclerView.getOnItemClickListener() != null) {
                        mNRecyclerView.getOnItemClickListener().onItemClick(mNRecyclerView, holder.itemView, position - mHeaderSparseArray.size());
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mHeaderSparseArray.size() + mAdapter.getItemCount() + mFooterSparseArray.size();
    }

    @Override
    public int getItemViewType(int position) {

        int numHeaders = mHeaderSparseArray.size() ;
        if (position < numHeaders) {
            return mHeaderSparseArray.keyAt(position);
        } else if (position >= (numHeaders + mAdapter.getItemCount())) {
            return mFooterSparseArray.keyAt(position - (numHeaders + mAdapter.getItemCount()));
        } else {
            return mAdapter.getItemViewType(position - numHeaders);
        }
    }

    private ViewGroup.LayoutParams getLayoutParams() {
        return new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    static class HeadFootViewHolder extends RecyclerView.ViewHolder {
        public HeadFootViewHolder(View itemView) {
            super(itemView);
        }
    }


}

