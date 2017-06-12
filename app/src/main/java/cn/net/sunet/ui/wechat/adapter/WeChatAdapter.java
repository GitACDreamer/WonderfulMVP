package cn.net.sunet.ui.wechat.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.StringUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.net.sunet.R;
import cn.net.sunet.app.App;
import cn.net.sunet.app.Constants;
import cn.net.sunet.mvp.model.entity.WeChatBean;
import cn.net.sunet.ui.gank.activity.GirlDetailActivity;
import cn.net.sunet.ui.gank.activity.TechnologyDetailActivity;
import cn.net.sunet.utils.ImageLoader;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/11
   Modify: 2017/1/11
 * Description: wechat adapter
 */

public class WeChatAdapter extends SwipeMenuAdapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<WeChatBean> mData;
    private final static int ITEM_NEWS = -1;

    public WeChatAdapter(Context context, List<WeChatBean> data) {
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        if (viewType == ITEM_NEWS)
            return LayoutInflater.from(mContext).inflate(R.layout.view_item_wechat_news, parent, false);
        else
            return LayoutInflater.from(mContext).inflate(R.layout.view_item_girl, parent, false);
    }

    @Override
    public int getItemViewType(int position) {
        if (mData.get(position).getType() == Constants.TYPE_WECHAT_NEWS ||
                mData.get(position).getType() == Constants.TYPE_WECHAT_FANS
                || mData.get(position).getType() == Constants.TYPE_NEWS_SOCIAL
                || mData.get(position).getType() == Constants.TYPE_NEWS_DOMESTIC
                || mData.get(position).getType() == Constants.TYPE_NEWS_INTERNATIONAL
                || mData.get(position).getType() == Constants.TYPE_NEWS_FUNNY)
            return ITEM_NEWS;
        else
            return Math.round((float) App.SCREEN_WIDTH / (float) mData.get(position).getHeight() * 10f);
    }

    @Override
    public RecyclerView.ViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        if (viewType == ITEM_NEWS)
            return new NewsViewHolder(realContentView);
        else
            return new BeautyViewHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NewsViewHolder) {
            if (!StringUtils.isEmpty(mData.get(position).getPicUrl())) {
                if (mData.get(position).getPicUrl().contains("photocdn.sohu.com")) //搜狐所有的图片失效
                    ((NewsViewHolder) holder).ivImage.setImageResource(R.mipmap.ic_menu_news);
                else
                    ImageLoader.load(mContext, mData.get(position).getPicUrl(), ((NewsViewHolder) holder).ivImage);
            } else
                ((NewsViewHolder) holder).ivImage.setImageResource(R.mipmap.ic_menu_news);
            ((NewsViewHolder) holder).tvTitle.setText(mData.get(position).getTitle());
            ((NewsViewHolder) holder).tvTime.setText(mData.get(position).getCtime());
            ((NewsViewHolder) holder).tvFrom.setText(mData.get(position).getDescription());
        } else {
            //存在记录的高度时先Layout再异步加载图片
            if (mData.get(position).getHeight() > 0) {
                ViewGroup.LayoutParams lp = ((BeautyViewHolder) holder).ivGirl.getLayoutParams();
                lp.height = mData.get(position).getHeight();
            }
            Glide.with(mContext).load(mData.get(position).getPicUrl()).asBitmap().diskCacheStrategy(DiskCacheStrategy
                    .ALL)
                    .into(new SimpleTarget<Bitmap>(App.SCREEN_WIDTH / 2, App.SCREEN_HEIGHT / 2) {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            int pos = holder.getAdapterPosition();
                            if (pos != RecyclerView.NO_POSITION) {
                                if (mData.get(pos).getHeight() <= 0) {
                                    int width = resource.getWidth();
                                    int height = resource.getHeight();
                                    int realHeight = (App.SCREEN_WIDTH / 2) * height / width;
                                    mData.get(pos).setHeight(realHeight);
                                    ViewGroup.LayoutParams lp = ((BeautyViewHolder) holder).ivGirl.getLayoutParams();
                                    lp.height = realHeight;
                                }
                                ((BeautyViewHolder) holder).ivGirl.setImageBitmap(resource);
                            }
                        }
                    });
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_item_wechat_title)
        TextView tvTitle;
        @BindView(R.id.tv_item_wechat_time)
        TextView tvTime;
        @BindView(R.id.tv_item_wechat_from)
        TextView tvFrom;
        @BindView(R.id.iv_item_wechat_image)
        ImageView ivImage;

        NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            gotoTeachDetail(mData.get(pos).getPicUrl(),    //id
                    mData.get(pos).getTitle(),             //title
                    mData.get(pos).getUrl(),               //uri
                    mData.get(pos).getPicUrl(),            //imgUri
                    Constants.TYPE_WECHAT);                //type
        }
    }

    class BeautyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.iv_girl)
        ImageView ivGirl;

        BeautyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            gotoGirlDetail(mData.get(pos).getPicUrl(), mData.get(pos).getUrl(), mData.get(pos).getDescription());
        }
    }


    private void gotoGirlDetail(String url, String id, String who) {
        Intent intent = new Intent(mContext, GirlDetailActivity.class);
        intent.putExtra(Constants.IT_DETAIL_URL, url);
        intent.putExtra(Constants.IT_DETAIL_ID, id);
        intent.putExtra(Constants.IT_DETAIL_TITLE, who);
        mContext.startActivity(intent);
    }

    private void gotoTeachDetail(String id, String title, String url, String imgUrl, int type) {
        TechnologyDetailActivity.launch(new TechnologyDetailActivity.Builder()
                .setContext(mContext)
                .setId(String.valueOf(id))
                .setTitle(title)
                .setUrl(url)
                .setImgUrl(imgUrl)
                .setType(type));
    }
}
