package cn.net.sunet.ui.gold.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.net.sunet.R;
import cn.net.sunet.app.Constants;
import cn.net.sunet.mvp.model.entity.GoldItemBean;
import cn.net.sunet.ui.gank.activity.TechnologyDetailActivity;
import cn.net.sunet.utils.ImageLoader;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/20
   Modify: 2017/1/20
 * Description: gold pager adapter
 */


public class GoldPagerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM_TITLE = 0; //标题
    private static final int ITEM_HOT = 1;   //热门
    private static final int ITEM_CONTENT = 2; //内容
    private List<GoldItemBean> mData;
    private Context mContext;
    private String mType;
    private boolean mIsHot = true;
    private OnHotCloseListener onHotCloseListener;

    public interface OnHotCloseListener {
        void onClose();
    }

    public GoldPagerAdapter(Context context, List<GoldItemBean> data, String type) {
        this.mContext = context;
        this.mData = data;
        this.mType = type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TITLE)
            return new TitleViewHolder(LayoutInflater.from(mContext).inflate(R.layout.view_item_gold_title, parent,
                    false));
        else if (viewType == ITEM_HOT)
            return new HotViewHolder(LayoutInflater.from(mContext).inflate(R.layout.view_item_gold_hot, parent,
                    false));
        return new ContentViewHolder(LayoutInflater.from(mContext).inflate(R.layout.view_item_gold_content, parent,
                false));
    }

    @Override
    public int getItemViewType(int position) {
        if (!mIsHot) {
            return ITEM_CONTENT;
        } else {
            if (position == 0)
                return ITEM_TITLE;
            else if (position > 0 && position <= 3)
                return ITEM_HOT;
            else
                return ITEM_CONTENT;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GoldItemBean item = mData.get(0);
        if (position > 0)
            item = mData.get(position - 1);
        if (holder instanceof ContentViewHolder) {
            if (item.getScreenshot() != null && item.getScreenshot().getUrl() != null)
                ImageLoader.load(mContext, item.getScreenshot().getUrl(), ((ContentViewHolder) holder).ivImg);
            else
                ((ContentViewHolder) holder).ivImg.setImageResource(R.mipmap.ic_launcher);
            ((ContentViewHolder) holder).tvTitle.setText(item.getTitle());
            String date = item.getCreatedAt();
            int idx = date.indexOf(".");
            if (idx > 0)
                date = date.substring(0, idx).replace("T", " ");
            ((ContentViewHolder) holder).tvInfo.setText(getItemInfo(item.getCollectionCount(),
                    item.getCommentsCount(),
                    item.getUser().getUsername(),
                    date));
            holder.itemView.setOnClickListener(new OnItemClickListener(--position));
        } else if (holder instanceof HotViewHolder) {
            if (item.getScreenshot() != null && item.getScreenshot().getUrl() != null)
                ImageLoader.load(mContext, item.getScreenshot().getUrl(), ((HotViewHolder) holder).ivImg);
            else
                ((HotViewHolder) holder).ivImg.setImageResource(R.mipmap.ic_launcher);
            ((HotViewHolder) holder).tvTitle.setText(item.getTitle());
            ((HotViewHolder) holder).tvLike.setText(String.valueOf(item.getCollectionCount()));
            ((HotViewHolder) holder).tvAuthor.setText(item.getUser().getUsername());
            String date = item.getCreatedAt();
            int idx = date.indexOf(".");
            if (idx > 0)
                date = date.substring(0, idx).replace("T", " ");
            ((HotViewHolder) holder).tvTime.setText(date);
            holder.itemView.setOnClickListener(new OnItemClickListener(--position));
        } else {
            ((TitleViewHolder) holder).tvTitle.setText(String.format("%s 热门", mType));
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public boolean isHot() {
        return mIsHot;
    }

    public void setHot(boolean isHot) {
        this.mIsHot = isHot;
    }

    private String getItemInfo(int like, int comment, String author, String time) {
        return String.valueOf(like) + "人收藏 · " + comment + "条评论 · " + author + " · " + time;
    }

    private class OnItemClickListener implements View.OnClickListener {
        private int position;

        OnItemClickListener(int position) {
            this.position = position;
            if (position < 0)
                this.position = 0;
        }

        @Override
        public void onClick(View v) {
            String imgUrl = null;
            if (mData.get(position).getScreenshot() != null &&
                    mData.get(position).getScreenshot().getUrl() != null)
                imgUrl = mData.get(position).getScreenshot().getUrl();
            TechnologyDetailActivity.launch(new TechnologyDetailActivity.Builder()
                    .setContext(mContext)
                    .setId(mData.get(position).getObjectId())
                    .setTitle(mData.get(position).getTitle())
                    .setUrl(mData.get(position).getUrl())
                    .setImgUrl(imgUrl)
                    .setType(Constants.TYPE_GOLD));
        }
    }

    class ContentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_gold_item_title)
        TextView tvTitle;
        @BindView(R.id.tv_gold_item_info)
        TextView tvInfo;
        @BindView(R.id.iv_gold_item_img)
        ImageView ivImg;

        ContentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class TitleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_gold_hot_title)
        TextView tvTitle;
        @BindView(R.id.btn_gold_hot_close)
        AppCompatButton btnClose;

        TitleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            btnClose.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mIsHot = false;
            for (int i = 0; i < 4; ++i)
                mData.remove(0);
            notifyItemRangeChanged(0, 4);
            if (onHotCloseListener != null)
                onHotCloseListener.onClose();
        }
    }

    class HotViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_gold_item_title)
        TextView tvTitle;
        @BindView(R.id.tv_gold_item_like)
        TextView tvLike;
        @BindView(R.id.tv_gold_item_author)
        TextView tvAuthor;
        @BindView(R.id.tv_gold_item_time)
        TextView tvTime;
        @BindView(R.id.iv_gold_item_img)
        ImageView ivImg;

        HotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnHotCloseListener(OnHotCloseListener onHotCloseListener) {
        this.onHotCloseListener = onHotCloseListener;
    }
}
