package cn.net.sunet.utils;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/28
   Modify: 2016/12/28
 * Description: shared preferenceUtils
 */

import android.content.Context;

import com.blankj.utilcode.utils.SPUtils;
import com.blankj.utilcode.utils.Utils;

import cn.net.sunet.app.Constants;

public class SharedPreferenceUtils {

    private static SPUtils sp;
    private static final String SP_NAME = "sp_name";
    private static final boolean DEFAULT_MANAGER_POINT = false;
    private static final boolean DEFAULT_VERSION_POINT = false;

    public static void init(Context context) {
        Utils.init(context);
        sp = new SPUtils(SP_NAME);
    }

    public static boolean isManagerPoint() {
        return sp.getBoolean(Constants.SP_MANAGER_POINT, DEFAULT_MANAGER_POINT);
    }

    public static void setManagerPoint(boolean isFirst) {
        sp.putBoolean(Constants.SP_MANAGER_POINT, isFirst);
    }

    public static boolean getVersionPoint() {
        return sp.getBoolean(Constants.SP_VERSION_POINT, DEFAULT_VERSION_POINT);
    }

    public static void setVersionPoint(boolean isFirst) {
        sp.putBoolean(Constants.SP_VERSION_POINT, isFirst);
    }

}
