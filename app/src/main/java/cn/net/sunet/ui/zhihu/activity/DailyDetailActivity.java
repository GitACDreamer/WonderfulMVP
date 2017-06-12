package cn.net.sunet.ui.zhihu.activity;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.StringUtils;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import cn.net.sunet.R;
import cn.net.sunet.base.BaseActivity;
import cn.net.sunet.mvp.contract.DailyDetailContract;
import cn.net.sunet.mvp.model.entity.DailyDetailBean;
import cn.net.sunet.mvp.model.entity.DailyStoryExtraBean;
import cn.net.sunet.mvp.presenter.DailyDetailPresenter;
import cn.net.sunet.utils.HtmlUtils;
import cn.net.sunet.utils.ImageLoader;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/28
   Modify: 2016/12/28
 * Description: daily detail activity
 */

public class DailyDetailActivity extends BaseActivity<DailyDetailPresenter> implements DailyDetailContract.View {

    @BindView(R.id.detail_bar_image)
    ImageView detailBarImage;
    @BindView(R.id.detail_bar_copyright)
    TextView detailBarCopyright;
    @BindView(R.id.view_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.clp_toolbar)
    CollapsingToolbarLayout clpToolbar;
    @BindView(R.id.web_view)
    WebView mWebView;
    @BindView(R.id.nsv_scroller)
    NestedScrollView nsvScroller;
    @BindView(R.id.tv_detail_bottom_like)
    TextView tvDetailBottomLike;
    @BindView(R.id.tv_detail_bottom_comment)
    TextView tvDetailBottomComment;
    @BindView(R.id.ll_detail_bottom)
    FrameLayout llDetailBottom;
    @BindView(R.id.fab_collection)
    FloatingActionButton fabCollection;

    int id = 0;
    int allNum = 0;
    int shortNum = 0;
    int longNum = 0;
    String shareUrl;
    String imageUrl;
    boolean isBottomShow = true;
    boolean isTransitionEnd = false;
    boolean isNotTransition = false;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_daily_detail;
    }

    @Override
    protected void initView() {
        mToolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
        setToolBar(mToolbar, "");
        id = getIntent().getExtras().getInt("id");
        isNotTransition = getIntent().getBooleanExtra("isNotTransition", false);
        mPresenter.queryCollection(id);
        mPresenter.dailyDetail(id);
        mPresenter.dailyDetailExtra(id);
        mWebView.setWebViewClient(client);
        mWebView.setDrawingCacheEnabled(true);
        nsvScroller.setOnScrollChangeListener(onScrollChangeListener);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            (getWindow().getSharedElementEnterTransition()).addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {

                }

                @Override
                public void onTransitionEnd(Transition transition) {
                    isTransitionEnd = true;
                    if (!StringUtils.isEmpty(imageUrl)) {
                        ImageLoader.load(mContext, imageUrl, detailBarImage);
                    }
                }

                @Override
                public void onTransitionCancel(Transition transition) {

                }

                @Override
                public void onTransitionPause(Transition transition) {

                }

                @Override
                public void onTransitionResume(Transition transition) {

                }
            });
        } else {
            isTransitionEnd = true;
            if (!StringUtils.isEmpty(imageUrl)) {
                ImageLoader.load(mContext, imageUrl, detailBarImage);
            }
        }
    }

    private WebViewClient client = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    };

    private NestedScrollView.OnScrollChangeListener onScrollChangeListener = new NestedScrollView
            .OnScrollChangeListener() {
        @Override
        public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
            if (scrollY - oldScrollY > 0 && isBottomShow) {  //下移隐藏
                isBottomShow = false;
                llDetailBottom.animate().translationY(llDetailBottom.getHeight());
            } else if (scrollY - oldScrollY < 0 && !isBottomShow) { //上移动显示
                isBottomShow = true;
                llDetailBottom.animate().translationY(0);
            }
        }
    };

    @OnClick({R.id.tv_detail_bottom_like, R.id.tv_detail_bottom_comment, R.id.tv_detail_bottom_share, R.id
            .fab_collection})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_collection:
                gotoCollection();
                break;
            case R.id.tv_detail_bottom_like:
                gotoApprover();
                break;
            case R.id.tv_detail_bottom_comment:
                gotoComment();
                break;
            case R.id.tv_detail_bottom_share:
                gotoShare();
                break;
        }
    }

    private void gotoApprover() {

    }

    private void gotoComment() {
        Intent intent = new Intent(mContext, CommentActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("allNum", allNum);
        intent.putExtra("shortNum", shortNum);
        intent.putExtra("longNum", longNum);
        startActivity(intent);
    }

    private void gotoShare() {
        Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, shareUrl);
        mContext.startActivity(Intent.createChooser(intent, "分享一篇文章"));
    }

    private void gotoCollection() {
        if (fabCollection.isSelected()) {
            fabCollection.setSelected(false);
            mPresenter.deleteCollection();
        } else {
            fabCollection.setSelected(true);
            mPresenter.insertCollection();
        }
    }

    @Override
    public void showContent(DailyDetailBean bean) {
        if (bean.getBody() == null)  //judge the bean's body is null or not?
            return;
        imageUrl = bean.getImage();
        shareUrl = bean.getShare_url();
        if (isNotTransition)
            ImageLoader.load(mContext, bean.getImage(), detailBarImage);
        else {
            if (isTransitionEnd)
                ImageLoader.load(mContext, bean.getImage(), detailBarImage);
        }
        clpToolbar.setTitle(bean.getTitle());
        detailBarCopyright.setText(bean.getImage_source());
        String html = HtmlUtils.createHtmlData(bean.getBody(), bean.getCss(), bean.getJs());
        mWebView.loadData(html, HtmlUtils.MIME_TYPE, HtmlUtils.ENCODING);
    }

    @Override
    public void showExtraInfo(DailyStoryExtraBean bean) {
        tvDetailBottomLike.setText(String.format(Locale.CHINA, "%d个赞", bean.getPopularity()));
        tvDetailBottomComment.setText(String.format(Locale.CHINA, "%d条评论", bean.getComments()));
        allNum = bean.getComments();
        shortNum = bean.getShort_comments();
        longNum = bean.getLong_comments();
    }

    @Override
    public void setCollectionButtonState(boolean isCollected) {
        fabCollection.setSelected(isCollected);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressedSupport() {
        fabCollection.setVisibility(View.GONE);
        if (getSupportFragmentManager().getBackStackEntryCount() > 1)
            pop();
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else
            finish();
    }
}
