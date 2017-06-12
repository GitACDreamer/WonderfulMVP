package cn.net.sunet.ui.gank.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.net.sunet.R;
import cn.net.sunet.app.App;
import cn.net.sunet.mvp.model.entity.GankItemBean;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/17
   Modify: 2017/1/17
 * Description: tech adapter
 */

public class GirlAdapter extends RecyclerView.Adapter<GirlAdapter.ViewHolder> {
    private List<GankItemBean> mData;
    private Context mContext;

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public GirlAdapter(Context context, List<GankItemBean> data) {
        mContext = context;
        mData = data;
    }

    @Override
    public int getItemViewType(int position) {
        return Math.round((float) App.SCREEN_WIDTH / (float) mData.get(position).getHeight() * 10f);
    }

    @Override
    public GirlAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.view_item_girl, parent, false));
    }

    @Override
    public void onBindViewHolder(final GirlAdapter.ViewHolder holder, int position) {
        //存在记录的高度时先Layout再异步加载图片
        if (mData.get(holder.getAdapterPosition()).getHeight() > 0) {
            ViewGroup.LayoutParams lp = holder.ivGirl.getLayoutParams();
            lp.height = mData.get(holder.getAdapterPosition()).getHeight();
        }

        Glide.with(mContext).load(mData.get(position).getUrl()).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new SimpleTarget<Bitmap>(App.SCREEN_WIDTH / 2, App.SCREEN_HEIGHT / 2) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        if (holder.getAdapterPosition() != RecyclerView.NO_POSITION) {
                            if (mData.get(holder.getAdapterPosition()).getHeight() <= 0) {
                                int width = resource.getWidth();
                                int height = resource.getHeight();
                                int realHeight = (App.SCREEN_WIDTH / 2) * height / width;
                                mData.get(holder.getAdapterPosition()).setHeight(realHeight);
                                ViewGroup.LayoutParams lp = holder.ivGirl.getLayoutParams();
                                lp.height = realHeight;
                            }
                            holder.ivGirl.setImageBitmap(resource);
                        }
                    }
                });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.iv_girl)
        ImageView ivGirl;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
