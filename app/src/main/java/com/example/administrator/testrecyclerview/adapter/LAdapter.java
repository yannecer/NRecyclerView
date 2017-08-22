package com.example.administrator.testrecyclerview.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.administrator.testrecyclerview.R;
import com.necer.nrecyclerview.GyAdapter;
import com.necer.nrecyclerview.NRecyclerViewHolder;


/**
 * Created by 闫彬彬 on 2017/8/21.
 * QQ:619008099
 */

public class LAdapter extends GyAdapter<String> {
    public LAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item;
    }

    @Override
    public void onBindData(NRecyclerViewHolder holder, String s, int position) {


        TextView textView = holder.getView(R.id.tv_);
        textView.setText(s);
    }
}
