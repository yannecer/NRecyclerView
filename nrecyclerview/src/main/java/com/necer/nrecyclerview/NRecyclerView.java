package com.necer.nrecyclerview;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by 闫彬彬 on 2017/8/8.
 */

public class NRecyclerView extends RecyclerView{

    private Adapter mAdapter;
    private Adapter mInnerAdapter;
    private SparseArray mHeaderSparseArray;
    private SparseArray mFooterSparseArray;
    private static final int ITEM_VIEW_TYPE_HEADER_INDEX = 10000000;
    private static final int ITEM_VIEW_TYPE_FOOTER_INDEX = 20000000;
    private View mEmptyView;

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

    private AdapterDataObserver dataObserver = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            if (mEmptyView != null && mInnerAdapter!= null) {
                boolean emptyViewVisible = mInnerAdapter.getItemCount() == 0;
                mEmptyView.setVisibility(emptyViewVisible ? VISIBLE : GONE);
                setVisibility(emptyViewVisible ? GONE : VISIBLE);
            }
            mAdapter.notifyDataSetChanged();
        }
    };

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
        if (mInnerAdapter != null) {
            mInnerAdapter.unregisterAdapterDataObserver(dataObserver);
        }
        this.mInnerAdapter = adapter;
        mAdapter = new HeadFootAdapter(mHeaderSparseArray, mFooterSparseArray, adapter);
        super.setAdapter(mAdapter);

        mInnerAdapter.registerAdapterDataObserver(dataObserver);
        dataObserver.onChanged();
    }


    @Override
    public void setLayoutManager(LayoutManager layout) {
        if (layout instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layout;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int spanCount = gridLayoutManager.getSpanCount();
                    if (position < mHeaderSparseArray.size() || position >= (mHeaderSparseArray.size() + mInnerAdapter.getItemCount())) {
                        return spanCount;
                    } else {
                        return 1;
                    }
                }
            });
        }
        super.setLayoutManager(layout);
    }

    public int getHeaderViewCount() {
        return mHeaderSparseArray.size();
    }
    public int getFooterViewCount() {
        return mFooterSparseArray.size();
    }


    public void setEmptyView(View emptyView) {
        mEmptyView = emptyView;
        dataObserver.onChanged();
    }

    public View getEmptyView() {
        return mEmptyView;
    }


    private OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(RecyclerView recyclerView, View itemView, int position);
    }

}
