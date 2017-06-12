package cn.net.sunet.ui.zhihu.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.net.sunet.R;
import cn.net.sunet.mvp.model.entity.DailyNewsBeforeBean;
import cn.net.sunet.mvp.model.entity.DailyNewsLatestBean;
import cn.net.sunet.utils.ImageLoader;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/28
   Modify: 2016/12/28
 * Description: 知乎日报Adapter
 */

public class DailyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM_TOP = 0;     //滚动栏
    private static final int ITEM_DATE = 1;    //日期
    private static final int ITEM_CONTENT = 2; //内容
    private List<DailyNewsLatestBean.StoriesEntity> mData;
    private List<DailyNewsLatestBean.TopStoriesEntity> mTopData;
    private TopPagerAdapter mTopAdapter;
    private ViewPager mTopViewPager;
    private Context mContext;
    private boolean mIsBefore = false;
    private String mCurrentTitle;

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public DailyAdapter(Context context, List<DailyNewsLatestBean.StoriesEntity> data) {
        mContext = context;
        mData = data;
        mCurrentTitle = context.getString(R.string.zhihu_daily_title);
    }

    @Override
    public int getItemViewType(int position) {
        if (!mIsBefore) {
            switch (position) {
                case ITEM_TOP:
                    return ITEM_TOP;
                case ITEM_DATE:
                    return ITEM_DATE;
                default:
                    return ITEM_CONTENT;
            }
        } else {
            if (position == ITEM_TOP)
                return ITEM_DATE;
            else
                return ITEM_CONTENT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_TOP:
                mTopAdapter = new TopPagerAdapter(mContext, mTopData);
                return new TopViewHolder(LayoutInflater.from(mContext).inflate(R.layout.view_item_top, parent, false));
            case ITEM_DATE:
                return new DateViewHolder(LayoutInflater.from(mContext).inflate(R.layout.view_item_date, parent,
                        false));
            default:
                return new ContentViewHolder(LayoutInflater.from(mContext).inflate(R.layout.view_item_content,
                        parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ContentViewHolder) {
            final int contentPosition = mIsBefore ? position - 1 : position - 2;
            ((ContentViewHolder) holder).title.setText(mData.get(contentPosition).getTitle());
            if (mData.get(contentPosition).isReadState())
                ((ContentViewHolder) holder).title.setTextColor(ContextCompat.getColor(mContext, R.color.news_read));
            else
                ((ContentViewHolder) holder).title.setTextColor(ContextCompat.getColor(mContext, R.color.news_unread));
            ImageLoader.load(mContext, mData.get(contentPosition).getImages().get(0), ((ContentViewHolder) holder)
                    .image);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        ImageView iv = (ImageView) v.findViewById(R.id.iv_content_item_image);
                        onItemClickListener.onItemClick(iv, contentPosition);
                    }
                }
            });
        } else if (holder instanceof DateViewHolder) {
            ((DateViewHolder) holder).date.setText(mCurrentTitle);
        } else {
            ((TopViewHolder) holder).viewPager.setAdapter(mTopAdapter);
            mTopViewPager = ((TopViewHolder) holder).viewPager;
        }
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    static class ContentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_content_item_title)
        TextView title;
        @BindView(R.id.iv_content_item_image)
        ImageView image;

        ContentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class DateViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_daily_date)
        TextView date;

        DateViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    static class TopViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.vp_top)
        ViewPager viewPager;
//        @BindView(R.id.ll_point_container)
//        LinearLayout linearLayout;

        TopViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void addDailyNewLatest(DailyNewsLatestBean bean) {
        mCurrentTitle = mContext.getString(R.string.zhihu_daily_title);
        mData = bean.getStories();
        mTopData = bean.getTop_stories();
        mIsBefore = false;
        notifyDataSetChanged();
    }

    public void addDailyBeforeDate(String date, DailyNewsBeforeBean beforeBean) {
        mCurrentTitle = date;
        mData = beforeBean.getStories();
        mIsBefore = true;
        notifyDataSetChanged();
    }

    public boolean isBefore() {
        return mIsBefore;
    }

    public void setReadState(int position, boolean readState) {
        mData.get(position).setReadState(readState);
    }

    public void changeToTopPager(int currentCount) {
        if (!mIsBefore && mTopViewPager != null)
            mTopViewPager.setCurrentItem(currentCount);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
