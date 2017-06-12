package cn.net.sunet.ui.main.adapter;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.net.sunet.R;

/*
 * Author:    Leland
 * Email:     LelandACM@gmail.com
 * Website:   www.bestsrouce.net.cn
 * Date:      2017/5/8
 * Functions: 说明
 */

public abstract class BaseRVAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {
    private View noDataView;
    private View errorView;

    BaseRVAdapter(Activity context, RecyclerView recyclerView, @LayoutRes int layoutResId, @Nullable List<T>
            data) {
        super(layoutResId, data);
        View headerView = context.getLayoutInflater().inflate(R.layout.view_item_header, (ViewGroup) recyclerView
                .getParent(), false);
        addHeaderView(headerView);
        noDataView = context.getLayoutInflater().inflate(R.layout.view_item_none, (ViewGroup) recyclerView
                .getParent(), false);
        errorView = context.getLayoutInflater().inflate(R.layout.view_item_error, (ViewGroup) recyclerView
                .getParent(), false);
    }

    public void setHeaderAndEmptyView(boolean isError) {
        setNewData(null);
        setEmptyView(isError ? errorView : noDataView);
    }

    public abstract void convert(BaseViewHolder helper, T item);
}
