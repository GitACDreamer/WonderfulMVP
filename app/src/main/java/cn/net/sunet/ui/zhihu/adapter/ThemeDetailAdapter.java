package cn.net.sunet.ui.zhihu.adapter;

import android.content.Context;
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
import cn.net.sunet.mvp.model.entity.DailyThemeDetailBean;
import cn.net.sunet.utils.ImageLoader;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/2
   Modify: 2017/1/2
 * Description: theme detail adapter
 */

public class ThemeDetailAdapter extends RecyclerView.Adapter<ThemeDetailAdapter.ViewHolder> {

    private List<DailyThemeDetailBean.StoriesEntity> mData;
    private Context mContext;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public ThemeDetailAdapter(Context context, List<DailyThemeDetailBean.StoriesEntity> data) {
        mData = data;
        mContext = context;
    }

    @Override
    public ThemeDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.view_item_daily, parent, false));
    }

    @Override
    public void onBindViewHolder(final ThemeDetailAdapter.ViewHolder holder, final int position) {
        if (mData.get(position).getImages() != null && mData.get(position).getImages().size() > 0)
            ImageLoader.load(mContext, mData.get(position).getImages().get(0), holder.imageView);
        holder.textView.setText(mData.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    ImageView iv = (ImageView) v.findViewById(R.id.iv_theme_item_image);
                    onItemClickListener.onItemClick(iv, holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_theme_item_title)
        TextView textView;
        @BindView(R.id.iv_theme_item_image)
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setReadState(int position, boolean readState) {
        mData.get(position).setReadState(readState);
    }
}
