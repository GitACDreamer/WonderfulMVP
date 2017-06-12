package cn.net.sunet.ui.gank.activity;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.utils.TimeUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;
import cn.net.sunet.R;
import cn.net.sunet.app.App;
import cn.net.sunet.app.Constants;
import cn.net.sunet.base.SimpleActivity;
import cn.net.sunet.mvp.model.db.RealmHelper;
import cn.net.sunet.mvp.model.entity.RealmCollectionBean;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/18
   Modify: 2017/1/18
 * Description: technology detail activity
 */

public class TechnologyDetailActivity extends SimpleActivity {
    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.web_view)
    WebView mWebView;
    @BindView(R.id.tv_progress)
    TextView tvProgress;

    RealmHelper mRealmHelper;
    MenuItem menuItem;

    String title, url, imgUrl, id;
    int type;
    boolean isCollected;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_technology_detail;
    }

    @Override
    protected void initView() {
        mToolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
        setToolBar(mToolbar, title);
        mRealmHelper = App.getAppComponent().realmHelper();
        Intent intent = getIntent();
        id = intent.getExtras().getString(Constants.IT_DETAIL_ID);
        title = intent.getExtras().getString(Constants.IT_DETAIL_TITLE);
        url = intent.getExtras().getString(Constants.IT_DETAIL_URL);
        imgUrl = intent.getExtras().getString(Constants.IT_DETAIL_IMG_URL);
        type = intent.getExtras().getInt(Constants.IT_DETAIL_TYPE);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                webView.loadUrl(s);
                return true;
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView webView, int progress) {
                super.onProgressChanged(webView, progress);
                if (tvProgress == null)
                    return;
                if (progress == 100)
                    tvProgress.setVisibility(View.GONE);
                else {
                    tvProgress.setVisibility(View.VISIBLE);
                    ViewGroup.LayoutParams lp = tvProgress.getLayoutParams();
                    lp.width = App.SCREEN_WIDTH * progress / 100;
                }
            }

            @Override
            public void onReceivedTitle(WebView webView, String s) {
                super.onReceivedTitle(webView, s);
                setTitle(title);
            }
        });
        mWebView.loadUrl(url);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tech_meun, menu);
        menuItem = menu.findItem(R.id.action_collection);
        setCollectionState(mRealmHelper.queryCollection(id));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.action_collection:
                if (isCollected) {
                    item.setIcon(R.mipmap.ic_toolbar_collection_n);
                    mRealmHelper.deleteCollection(id);
                    isCollected = false;
                } else {
                    item.setIcon(R.mipmap.ic_toolbar_collection_p);
                    gotoCollection();
                    isCollected = true;
                }
                break;
            case R.id.action_copy:
                gotoCopy(url);
                break;
            case R.id.action_share:
                gotoShare();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void gotoCollection() {
        RealmCollectionBean bean = new RealmCollectionBean();
        bean.setId(id);
        bean.setTitle(title);
        bean.setUrl(url);
        bean.setImage(imgUrl);
        bean.setType(type);
        bean.setTime(TimeUtils.getNowTimeMills());
        mRealmHelper.insertCollection(bean);
    }

    private void gotoCopy(String text) {
        ClipData clipData = ClipData.newPlainText("url", text);
        ClipboardManager manager = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
        manager.setPrimaryClip(clipData);
        ToastUtils.showShortToast("已复制到剪贴板");
    }

    private void gotoShare() {
        Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, url);
        mContext.startActivity(Intent.createChooser(intent, "分享一篇文章"));
    }

    private void setCollectionState(boolean state) {
        if (state) {
            menuItem.setIcon(R.mipmap.ic_toolbar_collection_p);
            isCollected = true;
        } else {
            menuItem.setIcon(R.mipmap.ic_toolbar_collection_n);
            isCollected = false;
        }
    }

    @Override
    protected void onDestroy() {
        ToastUtils.cancelToast();
        super.onDestroy();
    }

    public static class Builder {
        private String id;
        private String title;
        private String url;
        private String imgUrl;
        private int type;
        private View view;
        private Context mContext;
        private Activity mActivity;

        public Builder() {

        }

        public Builder setContext(Context context) {
            mContext = context;
            return this;
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
            return this;
        }

        public Builder setType(int type) {
            this.type = type;
            return this;
        }

        public Builder setAnimConfig(Activity activity, View view) {
            this.mActivity = activity;
            this.view = view;
            return this;
        }
    }

    public static void launch(Builder builder) {
        if (builder.view != null) {
            Intent intent = new Intent(builder.mContext, TechnologyDetailActivity.class);
            intent.putExtra(Constants.IT_DETAIL_ID, builder.id);
            intent.putExtra(Constants.IT_DETAIL_TITLE, builder.title);
            intent.putExtra(Constants.IT_DETAIL_URL, builder.url);
            intent.putExtra(Constants.IT_DETAIL_IMG_URL, builder.imgUrl);
            intent.putExtra(Constants.IT_DETAIL_TYPE, builder.type);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                builder.mContext.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation
                        (builder.mActivity, builder.view, "shareView").toBundle());
            else
                builder.mContext.startActivity(intent);

        } else {
            Intent intent = new Intent(builder.mContext, TechnologyDetailActivity.class);
            intent.putExtra(Constants.IT_DETAIL_ID, builder.id);
            intent.putExtra(Constants.IT_DETAIL_TITLE, builder.title);
            intent.putExtra(Constants.IT_DETAIL_URL, builder.url);
            intent.putExtra(Constants.IT_DETAIL_IMG_URL, builder.imgUrl);
            intent.putExtra(Constants.IT_DETAIL_TYPE, builder.type);
            builder.mContext.startActivity(intent);
        }
    }
}
