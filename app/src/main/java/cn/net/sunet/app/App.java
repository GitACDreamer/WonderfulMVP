package cn.net.sunet.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.blankj.utilcode.utils.TimeUtils;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.smtt.sdk.QbSdk;

import java.util.HashSet;
import java.util.Set;

import cn.net.sunet.BuildConfig;
import cn.net.sunet.di.component.AppComponent;
import cn.net.sunet.di.component.DaggerAppComponent;
import cn.net.sunet.di.module.AppModule;
import cn.net.sunet.utils.SharedPreferenceUtils;
import io.realm.Realm;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
   Modify: 2016/12/22
 * Description: custom app
 */

public class App extends Application {

    public final static String TAG = App.class.getPackage().getName();
    private static App mInstance;
    public static AppComponent mAppComponent;
    private Set<Activity> mAllActivities;

    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;
    public static String BUGLY_ID = "2d6881b4fd";

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        //realm init
        Realm.init(getApplicationContext());

        //utils init
        SharedPreferenceUtils.init(getApplicationContext());

        //bugly statistics
        CrashReport.initCrashReport(getApplicationContext(), BUGLY_ID, true);

        //tencent webwiew
        QbSdk.initX5Environment(getApplicationContext(), null);

        //canary
        if (BuildConfig.USE_CANARY)
            LeakCanary.install(this);

        //初始化屏幕宽度和高度
        getScreenSize();
    }

    //获取明天的日期
    public static String getTomorrow() {
        return TimeUtils.millis2String(TimeUtils.getNowTimeMills() + 86400000, "yyyyMMdd");
    }

    public void getScreenSize() {
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if (SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }

    public static synchronized App getInstance() {
        return mInstance;
    }

    public static AppComponent getAppComponent() {
        if (mAppComponent == null) {
            synchronized (App.class) {
                mAppComponent = DaggerAppComponent.builder()
                        .appModule(new AppModule(mInstance))
                        .build();
            }
        }
        return mAppComponent;
    }

    public void addActivity(Activity act) {
        if (mAllActivities == null) {
            mAllActivities = new HashSet<>();
        }
        mAllActivities.add(act);
    }

    public void removeActivity(Activity act) {
        if (mAllActivities != null) {
            mAllActivities.remove(act);
        }
    }

    public void exitApp() {
        if (mAllActivities != null) {
            synchronized (mAllActivities) {
                for (Activity act : mAllActivities) {
                    act.finish();
                }
            }
        }
        System.exit(0);
    }
}
