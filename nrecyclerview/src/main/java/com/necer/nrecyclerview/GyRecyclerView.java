package com.necer.nrecyclerview;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by 闫彬彬 on 2017/8/18.
 * QQ:619008099
 */
public class GyRecyclerView extends RecyclerView {

    private View loadFooterView;
    private GyAdapter gyAdapter;
    private boolean isLoading;


    private ProgressBar pb_loading;
    private TextView tv_message;



    public GyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        loadFooterView = LayoutInflater.from(context).inflate(R.layout.layout_load_more, null);
        pb_loading = (ProgressBar) loadFooterView.findViewById(R.id.pb_loading);
        tv_message = (TextView) loadFooterView.findViewById(R.id.tv_message);

        addScrollListener();
    }

    private void addScrollListener() {
        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                boolean canScrollVertically = ViewCompat.canScrollVertically(GyRecyclerView.this, 1);
                if (!canScrollVertically && !isLoading) {
                    loading();
                    //加载
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                }
            }
        });
    }

    public void stopLoadMore() {
        loadingStop();
    }

    private void loading() {
        isLoading = true;
        pb_loading.setVisibility(VISIBLE);
        tv_message.setText("加载更多···");
    }

    private void loadingStop() {
        isLoading = false;
        pb_loading.setVisibility(GONE);
        tv_message.setText("已无更多");
    }


    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        this.gyAdapter = (GyAdapter) adapter;
    }

    public void addLoadFooter() {
        gyAdapter.addFooterView(loadFooterView);
    }

    public void removeLoadFooter() {
        gyAdapter.removeFooterView();
    }

    public OnLoadMoreListener onLoadMoreListener;
    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }


    public interface OnLoadMoreListener{
        void onLoadMore();
    }
}
