package cn.net.sunet.ui.gold.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.net.sunet.R;
import cn.net.sunet.mvp.model.entity.RealmGoldManagerItemBean;
import cn.net.sunet.ui.gold.fragment.GoldMainFragment;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/20
   Modify: 2017/1/20
 * Description: gold item manager adapter
 */

public class GoldManagerAdapter extends SwipeMenuAdapter<GoldManagerAdapter.ViewHolder> {

    private Context mContext;
    private List<RealmGoldManagerItemBean> mData;

    public GoldManagerAdapter(Context context, List<RealmGoldManagerItemBean> data) {
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(mContext).inflate(R.layout.view_item_gold_manager, parent, false);
    }

    @Override
    public GoldManagerAdapter.ViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new ViewHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(final GoldManagerAdapter.ViewHolder holder, int position) {
        holder.tvType.setText(GoldMainFragment.tabTitles[mData.get(position).getIndex()]);
        holder.scSwitch.setChecked(mData.get(position).isSelect());
        holder.scSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mData.get(holder.getAdapterPosition()).setSelect(isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return GoldMainFragment.tabTitles.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_gold_manager_type)
        TextView tvType;
        @BindView(R.id.sc_gold_manager_switch)
        SwitchCompat scSwitch;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
