package com.necer.nrecyclerview;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
/**
 * Created by 闫彬彬 on 2017/8/18.
 * QQ:619008099
 */
public abstract class LoadMoreAdapter<T> extends RecyclerView.Adapter<NRecyclerViewHolder>{

    private List<T> dataList;
    private Context context;

    private LoadMoreRecyclerView loadMoreRecyclerView;

    public LoadMoreAdapter(Context context,List<T> dataList) {
        this.context = context;
        this.dataList = dataList;

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.loadMoreRecyclerView = (LoadMoreRecyclerView) recyclerView;
    }

    @Override
    public NRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(context).inflate(getLayoutId(viewType), parent, false);
        NRecyclerViewHolder viewHolder = new NRecyclerViewHolder(contentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NRecyclerViewHolder holder, int position) {
        onBindData(holder, dataList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public abstract int getLayoutId(int viewType);
    public abstract void  onBindData(NRecyclerViewHolder holder,T t,int position);

    /**
     * 给adapter设置，数据
     * @param list
     */
    public void setData(List<T> list,int totalCount) {
        dataList.clear();
        dataList.addAll(list);

        if (dataList.size() < totalCount) {
            loadMoreRecyclerView.addLoadFooter();
        } else {
            loadMoreRecyclerView.removeLoadFooter();
        }
        notifyDataSetChanged();
    }

    public void addData(List<T> list) {
        dataList.addAll(dataList.size(), list);
        notifyDataSetChanged();
    }

}
