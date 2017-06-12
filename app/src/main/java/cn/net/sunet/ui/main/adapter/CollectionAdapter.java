package cn.net.sunet.ui.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.net.sunet.R;
import cn.net.sunet.app.Constants;
import cn.net.sunet.mvp.model.entity.RealmCollectionBean;
import cn.net.sunet.ui.gank.activity.GirlDetailActivity;
import cn.net.sunet.ui.gank.activity.TechnologyDetailActivity;
import cn.net.sunet.ui.zhihu.activity.DailyDetailActivity;
import cn.net.sunet.utils.ImageLoader;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/10
   Modify: 2017/1/10
 * Description: collection adapter
 */

public class CollectionAdapter extends SwipeMenuAdapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<RealmCollectionBean> mData;
    private static final int TYPE_ARTICLE = 0;
    private static final int TYPE_GIRL = 1;
    private static final int FOOTER_VIEW = 2;

    public CollectionAdapter(Context context, List<RealmCollectionBean> data) {
        mContext = context;
        mData = data;
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ARTICLE)
            return LayoutInflater.from(mContext).inflate(R.layout.view_item_article, parent, false);
        else if (viewType == TYPE_GIRL)
            return LayoutInflater.from(mContext).inflate(R.layout.view_item_girl_collection, parent, false);
        else
            return LayoutInflater.from(mContext).inflate(R.layout.view_item_footer, parent, false);
    }

    @Override
    public RecyclerView.ViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        if (viewType == TYPE_ARTICLE)
            return new ArticleViewHolder(realContentView);
        else if (viewType == TYPE_GIRL)
            return new GirlViewHolder(realContentView);
        else  //viewType == FOOTER_VIEW
            return new FooterViewHolder(realContentView);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mData.size())
            return FOOTER_VIEW;
        else {
            if (mData.get(position).getType() == Constants.TYPE_GIRL)
                return TYPE_GIRL;
            else
                return TYPE_ARTICLE;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ArticleViewHolder) {
            int pos = holder.getAdapterPosition();
            ((ArticleViewHolder) holder).title.setText(mData.get(pos).getTitle());
            switch (mData.get(position).getType()) {
                case Constants.TYPE_ZHIHU:
                    if (mData.get(pos).getImage() != null)
                        ImageLoader.load(mContext, mData.get(pos).getImage(), ((ArticleViewHolder) holder).imageView);
                    else
                        ((ArticleViewHolder) holder).imageView.setImageResource(R.mipmap.ic_launcher);
                    ((ArticleViewHolder) holder).from.setText("来自 知乎");
                    break;
                case Constants.TYPE_ANDROID:
                    ((ArticleViewHolder) holder).imageView.setImageResource(R.mipmap.ic_tech_android);
                    ((ArticleViewHolder) holder).from.setText("来自 干货");
                    break;
                case Constants.TYPE_IOS:
                    ((ArticleViewHolder) holder).imageView.setImageResource(R.mipmap.ic_tech_ios);
                    ((ArticleViewHolder) holder).from.setText("来自 干货");
                    break;
                case Constants.TYPE_WEB:
                    ((ArticleViewHolder) holder).imageView.setImageResource(R.mipmap.ic_tech_web);
                    ((ArticleViewHolder) holder).from.setText("来自 干货");
                    break;
                case Constants.TYPE_WECHAT:
                    ImageLoader.load(mContext, mData.get(pos).getImage(), ((ArticleViewHolder) holder).imageView);
                    ((ArticleViewHolder) holder).from.setText("来自 微信");
                    break;
                case Constants.TYPE_GOLD:
                    ImageLoader.load(mContext, mData.get(pos).getImage(), ((ArticleViewHolder) holder).imageView);
                    ((ArticleViewHolder) holder).from.setText("来自 掘金");
                    break;
            }
        } else if (holder instanceof GirlViewHolder)
            ImageLoader.load(mContext, mData.get(position).getImage(), ((GirlViewHolder) holder).imageView);
        else {
            if (mData.size() == 0)
                holder.itemView.setVisibility(View.GONE);
            else
                holder.itemView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        if (mData == null)
            return 0;
        if (mData.size() == 0)
            return 1;
        return mData.size() + 1;
        //return mData == null ? 0 : mData.size();
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.iv_article_image)
        ImageView imageView;
        @BindView(R.id.tv_article_title)
        TextView title;
        @BindView(R.id.tv_article_from)
        TextView from;

        ArticleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mData.get(getAdapterPosition()).getType() == Constants.TYPE_ZHIHU) {
                gotoDailyDetail(Integer.valueOf(mData.get(getAdapterPosition()).getId()));
            } else {
                int position = getAdapterPosition();
                gotoTeachDetail(mData.get(position).getId(), mData.get(position).getTitle(),
                        mData.get(position).getUrl(), null, mData.get(position).getType());
            }
        }
    }

    class GirlViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.iv_girl_image)
        ImageView imageView;

        GirlViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            gotoGirlDetail(mData.get(getAdapterPosition()).getUrl(), mData.get(getAdapterPosition()).getId());
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_content)
        TextView content;

         FooterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ToastUtils.showShortToast("I'm the FooterView^_^");
        }
    }

    private void gotoDailyDetail(int id) {
        Intent intent = new Intent(mContext, DailyDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("isNotTransition", true);
        mContext.startActivity(intent);
    }

    private void gotoTeachDetail(String id, String title, String url, String imgUrl, int type) {
        TechnologyDetailActivity.launch(new TechnologyDetailActivity.Builder()
                .setContext(mContext)
                .setId(id)
                .setTitle(title)
                .setUrl(url)
                .setImgUrl(imgUrl)
                .setType(type));
    }

    private void gotoGirlDetail(String url, String id) {
        Intent intent = new Intent(mContext, GirlDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("url", url);
        mContext.startActivity(intent);
    }
}
