package com.necer.nrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 闫彬彬 on 2017/8/18.
 * QQ:619008099
 */
public abstract class GyAdapter<T> extends RecyclerView.Adapter<NRecyclerViewHolder> {

    protected List<T> dataList;
    protected Context context;

    private static final int FOOTER_INDEX = 200000;
    private static final int HEADER_INDEX = 100000;
    private View headerView;
    private View footerView;


    private GyRecyclerView gyRecyclerView;

    public GyAdapter(Context context) {
        this.context = context;
        dataList = new ArrayList<>();
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.gyRecyclerView = (GyRecyclerView) recyclerView;
    }

    @Override
    public NRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == HEADER_INDEX && headerView != null) {
            headerView.setLayoutParams(getLayoutParams());
            return new NRecyclerViewHolder(headerView);
        } else if (viewType == FOOTER_INDEX && footerView != null) {
            footerView.setLayoutParams(getLayoutParams());
            return new NRecyclerViewHolder(footerView);
        } else {
            View contentView = LayoutInflater.from(context).inflate(getLayoutId(viewType), parent, false);
            return new NRecyclerViewHolder(contentView);
        }
    }

    @Override
    public void onBindViewHolder(NRecyclerViewHolder holder, int position) {


        if (getItemViewType(position) != HEADER_INDEX && getItemViewType(position) != FOOTER_INDEX) {
            int i = position - (headerView == null ? 0 : 1);
            onBindData(holder, dataList.get(i), i);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size() + (headerView == null ? 0 : 1) + (footerView == null ? 0 : 1);
    }

    public abstract int getLayoutId(int viewType);

    public abstract void onBindData(NRecyclerViewHolder holder, T t, int position);

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && headerView != null) {
            return HEADER_INDEX;
        } else if (position >= dataList.size() + (headerView == null ? 0 : 1) && footerView != null) {
            return FOOTER_INDEX;
        } else {
            return super.getItemViewType(position);
        }
    }


    public void addHeaderView(View headerView) {
        this.headerView = headerView;
        notifyDataSetChanged();
    }


    public void addFooterView(View footerView) {
        this.footerView = footerView;
        notifyDataSetChanged();
    }

    public void removeHeaderView() {
        this.headerView = null;
        notifyDataSetChanged();
    }

    public void removeFooterView() {
        this.footerView = null;
        notifyDataSetChanged();
    }


    /**
     * 给adapter设置，数据
     *
     * @param list
     */
    public void setData(List<T> list, int totalCount) {
        dataList.clear();
        dataList.addAll(list);
        if (dataList.size() < totalCount) {
              gyRecyclerView.addLoadFooter();
        } else {
             gyRecyclerView.removeLoadFooter();
        }
        notifyDataSetChanged();
    }

    public void addData(List<T> list) {
        dataList.addAll(dataList.size(), list);
        notifyDataSetChanged();
    }

    private ViewGroup.LayoutParams getLayoutParams() {
        return new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}
