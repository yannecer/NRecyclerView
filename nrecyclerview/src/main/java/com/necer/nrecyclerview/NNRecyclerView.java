package com.necer.nrecyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


/**
 * 加载更多RecyclerView
 * Created by necer on 2017/12/1.
 */

public class NNRecyclerView  extends NRecyclerView{

    private View loadMoreView;
    private ProgressBar progressBar;
    private TextView tvMessage;

    private boolean isLoading;

    public NNRecyclerView(Context context) {
        this(context,null);
    }

    public NNRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NNRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        loadMoreView = LayoutInflater.from(context).inflate(R.layout.layout_load_more, null);
        progressBar = (ProgressBar) loadMoreView.findViewById(R.id.pb_);
        tvMessage = (TextView) loadMoreView.findViewById(R.id.tv_message);


        setLayoutManager(new LinearLayoutManager(context));

        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                //动态添加脚
                if ( !isLoading && !hasFooter() && dy > 0) {
                   addFooterView(loadMoreView);
                }

                boolean contentToBottom = RefreshLoadMoreUtil.isContentToBottom(NNRecyclerView.this);//状态为滑动到底部或者数据为铺满屏幕

                //自动加载更多
                if (!isLoading && hasFooter() && contentToBottom) {
                    startLoadMore();
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                }

            }
        });
    }




    private boolean hasFooter() {
        return mFooterSparseArray.size() != 0;
    }


    @Override
    public void addFooterView(View footerView) {
        super.addFooterView(footerView);
        if (mFooterSparseArray.size() > 1) {
            throw new RuntimeException("已经有脚布局");
        }
    }

    private void startLoadMore() {
        isLoading = true;
        progressBar.setVisibility(VISIBLE);
        tvMessage.setText("正在加载···");
    }

    private void stopLoadMore() {
        isLoading = false;
        progressBar.setVisibility(GONE);
        tvMessage.setText("暂无更多");
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    private OnLoadMoreListener onLoadMoreListener;
    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }


}
