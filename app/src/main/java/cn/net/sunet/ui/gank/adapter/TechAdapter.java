package cn.net.sunet.ui.gank.adapter;

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
import cn.net.sunet.mvp.model.entity.GankItemBean;
import cn.net.sunet.ui.gank.fragment.GankMainFragment;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/17
   Modify: 2017/1/17
 * Description: tech adapter
 */

public class TechAdapter extends RecyclerView.Adapter<TechAdapter.ViewHolder> {
    private List<GankItemBean> mData;
    private Context mContext;
    private String mTech = null;

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public TechAdapter(Context context, List<GankItemBean> data, String tech) {
        mContext = context;
        mData = data;
        this.mTech = tech;
    }

    @Override
    public TechAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.view_item_tech, parent, false));
    }

    @Override
    public void onBindViewHolder(TechAdapter.ViewHolder holder, int position) {
        if (mTech.equals(GankMainFragment.tabTitles[0]))
            holder.ivIcon.setImageResource(R.mipmap.ic_tech_android);
        else if (mTech.equals(GankMainFragment.tabTitles[1]))
            holder.ivIcon.setImageResource(R.mipmap.ic_tech_ios);
        else if (mTech.equals(GankMainFragment.tabTitles[2]))
            holder.ivIcon.setImageResource(R.mipmap.ic_tech_web);
        holder.tvContent.setText(mData.get(position).getDesc());
        holder.tvAuthor.setText(mData.get(position).getWho());
        String date = mData.get(position).getPublishedAt();
        int idx = date.indexOf(".");
        if (idx >= 0 && idx < date.length())  //idx有可能=-1
            date = date.substring(0, idx).replace("T", " ");
        holder.tvTime.setText(date);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.iv_tech_icon)
        ImageView ivIcon;
        @BindView(R.id.tv_tech_title)
        TextView tvContent;
        @BindView(R.id.tv_tech_author)
        TextView tvAuthor;
        @BindView(R.id.tv_tech_time)
        TextView tvTime;

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
