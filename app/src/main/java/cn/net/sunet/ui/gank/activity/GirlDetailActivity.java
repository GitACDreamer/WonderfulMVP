package cn.net.sunet.ui.gank.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.blankj.utilcode.utils.TimeUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import cn.net.sunet.R;
import cn.net.sunet.app.App;
import cn.net.sunet.app.Constants;
import cn.net.sunet.base.SimpleActivity;
import cn.net.sunet.mvp.model.db.RealmHelper;
import cn.net.sunet.mvp.model.entity.RealmCollectionBean;
import uk.co.senab.photoview.PhotoViewAttacher;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/18
   Modify: 2017/1/18
 * Description: girl detail activity
 */

public class GirlDetailActivity extends SimpleActivity {
    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.iv_girl_detail)
    ImageView ivGirlDetail;

    RealmHelper mRealmHelper;
    MenuItem menuItem;
    Bitmap bitmap;
    PhotoViewAttacher mAttacher;

    String url, id, title;
    boolean isCollected;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_girl_detail;
    }

    @Override
    protected void initView() {

        mRealmHelper = App.getAppComponent().realmHelper();
        Intent intent = getIntent();
        id = intent.getExtras().getString(Constants.IT_DETAIL_ID);
        url = intent.getExtras().getString(Constants.IT_DETAIL_URL);
        title = intent.getExtras().getString(Constants.IT_DETAIL_TITLE);
        mToolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
        setToolBar(mToolbar, title);
        if (url != null) {
            Glide.with(mContext).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    bitmap = resource;
                    ivGirlDetail.setImageBitmap(resource);
                    mAttacher = new PhotoViewAttacher(ivGirlDetail);
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.girl_menu, menu);
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
                gotoSave(mContext, url, bitmap, false);
                break;
            case R.id.action_share:
                gotoShare(gotoSave(mContext, url, bitmap, true));
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void gotoCollection() {
        RealmCollectionBean bean = new RealmCollectionBean();
        bean.setId(id);
        bean.setUrl(url);
        bean.setType(Constants.TYPE_GIRL);
        bean.setTime(TimeUtils.getNowTimeMills());
        mRealmHelper.insertCollection(bean);
    }

    private static Uri gotoSave(Context context, String url, Bitmap bitmap, boolean isShare) {
        String filename = url.substring(url.lastIndexOf("/"), url.lastIndexOf(".")) + ".png";
        File dir = new File(Constants.PATH_DATA);
        if (!dir.exists()) {
            dir.getParentFile().mkdirs();
            dir.mkdir();
        }
        File imageFile = new File(dir, filename);
        Uri uri = Uri.fromFile(imageFile);
        if (isShare && imageFile.exists())
            return uri;
        try {
            FileOutputStream fos = new FileOutputStream(imageFile);
            boolean isCompress = bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
            if (isCompress)
                ToastUtils.showShortToast("保存妹子成功n(*≧▽≦*)n");
            else
                ToastUtils.showShortToast("保存妹纸失败ヽ(≧Д≦)ノ");
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            ToastUtils.showShortToast("保存妹纸失败ヽ(≧Д≦)ノ");
        }
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(), imageFile.getAbsolutePath(), filename
                    , null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
        return uri;
    }

    private void gotoShare(Uri uri) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/png");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        mContext.startActivity(Intent.createChooser(intent, "分享一个妹子"));
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
}
