package cn.net.sunet.ui.zhihu.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.TimeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.net.sunet.R;
import cn.net.sunet.mvp.model.entity.DailyCommentBean;
import cn.net.sunet.utils.ImageLoader;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/2
   Modify: 2017/1/2
 * Description: comment adapter
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private List<DailyCommentBean.CommentsEntity> mData;
    private Context mContext;
    private static final int STATE_NULL = 0;  //未知
    private static final int STATE_NONE = 1;  //无需展开
    private static final int STATE_EXPAND = 2;//已展开
    private static final int STATE_SHRINK = 3;//已收缩
    private static final int MAX_LINE = 2;    //最多显示2行

    public CommentAdapter(Context context, List<DailyCommentBean.CommentsEntity> data) {
        mData = data;
        mContext = context;
    }

    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.view_item_comment, parent, false));
    }

    @Override
    public void onBindViewHolder(final CommentAdapter.ViewHolder holder, final int position) {
        DailyCommentBean.CommentsEntity entity = mData.get(position);
        ImageLoader.load(mContext, entity.getAvatar(), holder.civFace);
        holder.tvName.setText(entity.getAuthor());
        holder.tvContent.setText(entity.getContent());
        //entity.gettime()返回的值是以s为单位，而system.getCurrentTimeMills()返回的是ms所以需要*1000
        holder.tvTime.setText(TimeUtils.getFriendlyTimeSpanByNow(1000*entity.getTime()));
        if (entity.getReply_to() != null && entity.getReply_to().getId() != 0) {
            holder.tvReply.setVisibility(View.VISIBLE);
            SpannableString ss = new SpannableString("@" + entity.getReply_to().getAuthor() + ": " + entity
                    .getReply_to()
                    .getContent());
            ss.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.comment_at)), 0, entity
                    .getReply_to().getAuthor().length() + 2, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            holder.tvReply.setText(ss);
            if (entity.getReply_to().getExpandState() == STATE_NULL) { //未知
                holder.tvReply.post(new Runnable() {
                    @Override
                    public void run() {
                        if (holder.tvReply.getLineCount() > MAX_LINE) {
                            holder.tvReply.setMaxLines(MAX_LINE);
                            holder.tvExpand.setVisibility(View.VISIBLE);
                            holder.tvExpand.setText("展开");
                            mData.get(holder.getAdapterPosition()).getReply_to().setExpandState(STATE_SHRINK);
                            holder.tvExpand.setOnClickListener(new OnStateClickListener(holder.tvReply, holder
                                    .getAdapterPosition()));
                        }
                    }
                });
            } else if (entity.getReply_to().getExpandState() == STATE_NONE) { //无需展开
                holder.tvExpand.setVisibility(View.GONE);
            } else if (entity.getReply_to().getExpandState() == STATE_EXPAND) { //已展开
                holder.tvReply.setMaxLines(Integer.MAX_VALUE);
                holder.tvExpand.setText("收起");
                holder.tvExpand.setVisibility(View.VISIBLE);
                holder.tvExpand.setOnClickListener(new OnStateClickListener(holder.tvReply, holder.getAdapterPosition
                        ()));
            } else {  //已收缩
                holder.tvReply.setMaxLines(MAX_LINE);
                holder.tvExpand.setText("展开");
                holder.tvExpand.setVisibility(View.VISIBLE);
                holder.tvExpand.setOnClickListener(new OnStateClickListener(holder.tvReply, holder.getAdapterPosition
                        ()));
            }
        } else {
            holder.tvReply.setVisibility(View.GONE);
            holder.tvExpand.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.civ_comment_face)
        ImageView civFace;
        @BindView(R.id.tv_comment_name)
        TextView tvName;
        @BindView(R.id.tv_comment_content)
        TextView tvContent;
        @BindView(R.id.tv_comment_time)
        TextView tvTime;
        @BindView(R.id.tv_comment_expand)
        TextView tvExpand;
        @BindView(R.id.tv_comment_like)
        TextView tvLike;
        @BindView(R.id.tv_comment_reply)
        TextView tvReply;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private class OnStateClickListener implements View.OnClickListener {
        TextView replyView;
        int position;

        OnStateClickListener(TextView view, int position) {
            this.position = position;
            this.replyView = view;
        }

        @Override
        public void onClick(View v) {
            TextView tv = (TextView) v;
            if (mData.get(position).getReply_to().getExpandState() == STATE_SHRINK) {
                tv.setText("收起");
                replyView.setMaxLines(Integer.MAX_VALUE);
                replyView.setEllipsize(null);
                mData.get(position).getReply_to().setExpandState(STATE_EXPAND);
            } else {
                tv.setText("展开");
                replyView.setMaxLines(MAX_LINE);
                replyView.setEllipsize(TextUtils.TruncateAt.END);
                mData.get(position).getReply_to().setExpandState(STATE_SHRINK);
            }
        }
    }
}
