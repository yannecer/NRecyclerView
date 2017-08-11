package com.example.administrator.testrecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.administrator.testrecyclerview.R;
import com.necer.nrecyclerview.Logg;

import java.util.List;

/**
 * Created by Administrator on 2017/8/8.
 */

public class AAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private List<String> list;
    public AAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.list = list;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item, parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ItemViewHolder holder1 = (ItemViewHolder) holder;
        holder1.textView.setText(">>>>>"+list.get(position));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ItemViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_);

        }
    }
}

