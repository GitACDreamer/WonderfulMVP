package cn.net.sunet.ui.main.adapter;


/*
 * Author:    Leland
 * Email:     LelandACM@gmail.com
 * Website:   www.bestsrouce.net.cn
 * Date:      2017/5/8
 * Functions: adapter
 */

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.blankj.utilcode.utils.StringUtils;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.net.sunet.R;
import cn.net.sunet.mvp.model.entity.WeChatBean;
import cn.net.sunet.utils.ImageLoader;

@SuppressWarnings("unchecked")
public class PullRVAdapter extends BaseRVAdapter {
    private Activity context;

    public PullRVAdapter(Activity activity, RecyclerView recyclerView, @Nullable List<WeChatBean> data) {
        super(activity, recyclerView, R.layout.view_item_wechat_news, data);
        this.context = activity;
    }

    @Override
    public void convert(BaseViewHolder helper, Object item) {
        WeChatBean bean = (WeChatBean) item;
        if (StringUtils.isEmpty(bean.getPicUrl())) {
            helper.setImageResource(R.id.iv_item_wechat_image, R.mipmap.ic_menu_news);
        } else {
            ImageLoader.load(context, bean.getPicUrl(), (ImageView) helper.getView(R.id.iv_item_wechat_image));
        }
        helper.setText(R.id.tv_item_wechat_title, bean.getTitle());
        helper.setText(R.id.tv_item_wechat_from, bean.getDescription());
        helper.setText(R.id.tv_item_wechat_time, bean.getCtime());
    }
}
