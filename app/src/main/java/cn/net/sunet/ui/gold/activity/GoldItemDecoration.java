package cn.net.sunet.ui.gold.activity;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.utils.ConvertUtils;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/20
   Modify: 2017/1/20
 * Description: gold item decoration(分割线)
 */

public class GoldItemDecoration extends RecyclerView.ItemDecoration {
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        if (position > -1) {
            if (position == 0)
                outRect.set(0, ConvertUtils.dp2px(15f), 0, 0);
            else if (position == 3)
                outRect.set(0, ConvertUtils.dp2px(0.5f), 0, ConvertUtils.dp2px(15));
            else
                outRect.set(0, ConvertUtils.dp2px(0.5f), 0, 0);
        }
    }
}
