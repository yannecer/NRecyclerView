package com.necer.nrecyclerview;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
/**
 * Created by 闫彬彬 on 2017/8/18.
 * QQ:619008099
 */
public class LoadMoreRecyclerView extends NRecyclerView {
    private View loadFooterView;
    public LoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        loadFooterView = LayoutInflater.from(context).inflate(R.layout.layout_load_more, null);
        addScrollListener();
    }

    private void addScrollListener() {
        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }
        });
    }

    public void addLoadFooter() {
        addFooterView(loadFooterView);
    }
    public void removeLoadFooter() {
        removeFooterView(loadFooterView);
    }
}
