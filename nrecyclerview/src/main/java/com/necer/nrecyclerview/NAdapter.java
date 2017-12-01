package com.necer.nrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

/**
 * Created by necer on 2017/12/1.
 */

public abstract class NAdapter extends RecyclerView.Adapter<NViewHolder> {


    private Context mContext;
    private List mList;


    public NAdapter(Context context, List list) {

        mContext = context;
        mList = list;
    }

    @Override
    public NViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(mContext).inflate(getLayout(), parent,false);

        return new NViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(NViewHolder holder, final int position) {
        onBind(holder,position);
        final View itemView = holder.itemView;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(itemView,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public abstract int getLayout();

    public abstract void onBind(NViewHolder holder, int position);

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
