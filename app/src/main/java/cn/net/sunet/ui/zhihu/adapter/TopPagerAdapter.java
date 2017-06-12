package cn.net.sunet.ui.zhihu.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.net.sunet.R;
import cn.net.sunet.mvp.model.entity.DailyNewsLatestBean;
import cn.net.sunet.ui.zhihu.activity.DailyDetailActivity;
import cn.net.sunet.utils.ImageLoader;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/28
   Modify: 2016/12/28
 * Description: top viewpager adapter
 */

class TopPagerAdapter extends PagerAdapter {
    private List<DailyNewsLatestBean.TopStoriesEntity> mData;
    private Context mContext;

    TopPagerAdapter(Context context, List<DailyNewsLatestBean.TopStoriesEntity> data) {
        mContext = context;
        mData = data;
    }

    @Override
    public int getCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_item_top_pager, container, false);
        ImageView ivImg = (ImageView) view.findViewById(R.id.iv_top_image);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_top_title);
        ImageLoader.load(mContext, mData.get(position).getImage(), ivImg);
        tvTitle.setText(mData.get(position).getTitle());
        final int id = mData.get(position).getId();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DailyDetailActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("isNotTransition", true);
                mContext.startActivity(intent);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
