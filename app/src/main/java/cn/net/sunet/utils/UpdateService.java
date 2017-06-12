package cn.net.sunet.utils;

import android.app.DownloadManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.BuildConfig;
import android.support.v4.content.FileProvider;

import com.blankj.utilcode.utils.AppUtils;
import com.blankj.utilcode.utils.LogUtils;
import com.blankj.utilcode.utils.ToastUtils;

import java.io.File;

import cn.net.sunet.R;
import cn.net.sunet.app.App;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   16/12/12
   Modify: 16/12/12
 * Description: update the apk version service
 */

public class UpdateService extends Service {
    private String uri;
    private BroadcastReceiver receiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        uri = intent.getStringExtra("URI");
        final String APK_NAME = getString(R.string.app_name) + ".apk";
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                unregisterReceiver(receiver);
                intent = new Intent(Intent.ACTION_VIEW);
                String filePath = Environment.getExternalStorageDirectory() + "/download/" + APK_NAME;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    LogUtils.e(App.TAG, AppUtils.getAppPackageName(context));
                    Uri contentUri = FileProvider.getUriForFile(context, AppUtils.getAppPackageName(context) + "" +
                            ".provider", new File(filePath));
                    intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
                } else {
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setDataAndType(Uri.fromFile(new File(filePath)), "application/vnd.android.package-archive");
                }
                context.startActivity(intent);
                stopSelf();
            }
        };
        registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        startDownload();
        return Service.START_STICKY;
    }

    private void startDownload() {
        DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(uri));
        final String APP_NAME = getString(R.string.app_name);
        request.setTitle(APP_NAME);
        request.setDescription(getString(R.string.app_download));
        request.setMimeType("application/vnd.android.package-archive");
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, APP_NAME + ".apk");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }
        dm.enqueue(request);
        ToastUtils.showShortToast(getString(R.string.app_bg_downloading));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ToastUtils.cancelToast();
        receiver.clearAbortBroadcast();
    }
}
