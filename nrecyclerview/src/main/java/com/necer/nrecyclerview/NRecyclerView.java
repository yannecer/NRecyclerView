package com.necer.nrecyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
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
    private Adapter mOldAdapter;
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


/*


    final private AdapterDataObserver observer = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            checkIfEmpty();
        }
    };
    private void checkIfEmpty() {
        if (mEmptyView != null && getAdapter() != null) {
            final boolean emptyViewVisible =
                    getAdapter().getItemCount() == 0;
            mEmptyView.setVisibility(emptyViewVisible ? VISIBLE : GONE);
            setVisibility(emptyViewVisible ? GONE : VISIBLE);
        }
    }
*/



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

        this.mOldAdapter = adapter;
       /* final Adapter oldAdapter = getAdapter();
        if (oldAdapter != null) {
            oldAdapter.unregisterAdapterDataObserver(observer);
        }*/
        mAdapter = new HeadFootAdapter(mHeaderSparseArray, mFooterSparseArray, adapter);
        super.setAdapter(mAdapter);
       /* mAdapter.registerAdapterDataObserver(observer);
        checkIfEmpty();*/


    }




    @Override
    public void setLayoutManager(LayoutManager layout) {
        if (layout instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layout;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int spanCount = gridLayoutManager.getSpanCount();
                    if (position < mHeaderSparseArray.size() || position >= (mHeaderSparseArray.size() + mOldAdapter.getItemCount())) {
                        return spanCount;
                    } else {
                        return 1;
                    }
                }
            });
        }
        super.setLayoutManager(layout);
    }

    @Override
    public Adapter getAdapter() {
      //  return super.getAdapter();
        return mAdapter;
    }

    public int getHeaderViewCount() {
        return mHeaderSparseArray.size();
    }
    public int getFooterViewCount() {
        return mFooterSparseArray.size();
    }


    public void setEmptyView(View emptyView) {
        mEmptyView = emptyView;
        mEmptyView.setVisibility(VISIBLE);
        setVisibility(GONE);

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
