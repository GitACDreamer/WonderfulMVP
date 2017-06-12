package cn.net.sunet.utils;

import android.Manifest;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.blankj.utilcode.utils.LogUtils;
import com.tbruyelle.rxpermissions.RxPermissions;

import cn.net.sunet.app.App;
import rx.functions.Action1;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/9
   Modify: 2017/1/9
 * Description: android 6.0 runtime permission tools class
 */

public class RxPermissionUtils {

    public interface RequestPermission {
        void onRequestSuccess();
    }

    /*
     * 请求日历相关权限
     */
    public static void calendar(final RequestPermission requestPermission, RxPermissions rxPermissions) {
        //判断是否已经有读写日历权限
        boolean isGranted = rxPermissions.isGranted(Manifest.permission.READ_CALENDAR);
        if (isGranted)
            requestPermission.onRequestSuccess();
        else {
            request(requestPermission, rxPermissions, Manifest.permission.READ_CALENDAR);
        }
    }

    /*
     * 外部存储相关权限
     */
    public static void externalStorage(final RequestPermission requestPermission, RxPermissions rxPermissions) {
        //判断是否已经有权限
        boolean isGranted = rxPermissions.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (isGranted)
            requestPermission.onRequestSuccess();
        else {
            request(requestPermission, rxPermissions, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    }

    /*
     * 相机相关权限
     */
    public static void camera(final RequestPermission requestPermission, RxPermissions rxPermissions) {
        //判断是否已经有权限
        boolean isGranted = rxPermissions.isGranted(Manifest.permission.CAMERA);
        if (isGranted)
            requestPermission.onRequestSuccess();
        else {
            request(requestPermission, rxPermissions, Manifest.permission.CAMERA);
        }
    }

    /*
     * 联系人相关权限
     */
    public static void contacts(final RequestPermission requestPermission, RxPermissions rxPermissions) {
        //判断是否已经有权限
        boolean isGranted = rxPermissions.isGranted(Manifest.permission.READ_CONTACTS);
        if (isGranted)
            requestPermission.onRequestSuccess();
        else {
            request(requestPermission, rxPermissions, Manifest.permission.READ_CONTACTS);
        }
    }

    /*
     * 手机相关权限
     */
    public static void phone(final RequestPermission requestPermission, RxPermissions rxPermissions) {
        //判断是否已经有权限
        boolean isGranted = rxPermissions.isGranted(Manifest.permission.READ_PHONE_STATE);
        if (isGranted)
            requestPermission.onRequestSuccess();
        else {
            request(requestPermission, rxPermissions, Manifest.permission.READ_PHONE_STATE);
        }
    }

    /*
     *位置相关权限
     */
    public static void location(final RequestPermission requestPermission, RxPermissions rxPermissions) {
        //判断是否已经有权限
        boolean isGranted = rxPermissions.isGranted(Manifest.permission.ACCESS_FINE_LOCATION);
        if (isGranted)
            requestPermission.onRequestSuccess();
        else {
            request(requestPermission, rxPermissions, Manifest.permission.ACCESS_FINE_LOCATION);
        }
    }

    /*
     * 录音相关权限
     */
    public static void record(final RequestPermission requestPermission, RxPermissions rxPermissions) {
        //判断是否已经有权限
        boolean isGranted = rxPermissions.isGranted(Manifest.permission.RECORD_AUDIO);
        if (isGranted)
            requestPermission.onRequestSuccess();
        else {
            request(requestPermission, rxPermissions, Manifest.permission.RECORD_AUDIO);
        }
    }

    /*
     * 传感器相关权限
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    public static void sensors(final RequestPermission requestPermission, RxPermissions rxPermissions) {
        //判断是否已经有权限
        boolean isGranted = rxPermissions.isGranted(Manifest.permission.BODY_SENSORS);
        if (isGranted)
            requestPermission.onRequestSuccess();
        else {
            request(requestPermission, rxPermissions, Manifest.permission.BODY_SENSORS);
        }
    }

    /*
     * 信息相关权限
     */
    public static void sms(final RequestPermission requestPermission, RxPermissions rxPermissions) {
        //判断是否已经有权限
        boolean isGranted = rxPermissions.isGranted(Manifest.permission.RECEIVE_SMS);
        if (isGranted)
            requestPermission.onRequestSuccess();
        else {
            request(requestPermission, rxPermissions, Manifest.permission.RECEIVE_SMS);
        }
    }

    /*
     * 请求权限
     */
    private static void request(final RequestPermission requestPermission, RxPermissions rxPermissions, final String
            permission) {
        rxPermissions.request(permission).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean isOK) {
                if (isOK) {
                    LogUtils.d(App.TAG, "请求权限成功");
                    requestPermission.onRequestSuccess();
                } else {
                    LogUtils.d(App.TAG, "请求权限失败");
                }
            }
        });
    }
}
