package cn.net.sunet.ui.zhihu.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.ConvertUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.net.sunet.R;
import cn.net.sunet.app.App;
import cn.net.sunet.mvp.model.entity.DailySectionsBean;
import cn.net.sunet.utils.ImageLoader;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/2
   Modify: 2017/1/2
 * Description: section adapter
 */

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.ViewHolder> {

    private List<DailySectionsBean.DataEntity> mData;
    private Context mContext;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public SectionAdapter(Context context, List<DailySectionsBean.DataEntity> data) {
        mData = data;
        mContext = context;
    }

    @Override
    public SectionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.view_item_section, parent, false));
    }

    @Override
    public void onBindViewHolder(final SectionAdapter.ViewHolder holder, final int position) {
        ViewGroup.LayoutParams lp = holder.imageView.getLayoutParams();
        lp.width = (App.SCREEN_WIDTH - ConvertUtils.dp2px(12)) / 2;
        lp.height = ConvertUtils.dp2px(120);

        ImageLoader.load(mContext, mData.get(position).getThumbnail(), holder.imageView);
        holder.tvKind.setText(mData.get(position).getName());
        holder.tvDes.setText(mData.get(position).getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null)
                    onItemClickListener.onItemClick(v, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.section_bg)
        ImageView imageView;
        @BindView(R.id.section_kind)
        TextView tvKind;
        @BindView(R.id.section_des)
        TextView tvDes;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
