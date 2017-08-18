package com.necer.nrecyclerview;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by necer on 2016/8/12.
 */
public class NRecyclerViewHolder extends RecyclerView.ViewHolder{
    private SparseArray<View> mViews;
    private View mItemView;

    public NRecyclerViewHolder(View itemView) {
        super(itemView);
        mItemView = itemView;//这个是item的布局文件，layout
        mViews = new SparseArray<>();
    }

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mItemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T)view;
    }

}
