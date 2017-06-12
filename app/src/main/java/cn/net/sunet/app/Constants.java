package cn.net.sunet.app;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/10
   Modify: 2017/1/10
 * Description: constant
 */

import android.os.Environment;

import java.io.File;

public class Constants {

    public static final String WECHAT_KEY = "7a77f136d926f083c8c5f37eefa4a0c6";
    public static final String LEANCLOUD_ID = "mhke0kuv33myn4t4ghuid4oq2hjj12li374hvcif202y5bm6";
    public static final String LEANCLOUD_SIGN = "badc5461a25a46291054b902887a68eb,1480438132702";

    public static final String PATH_DATA = Environment.getExternalStorageDirectory().getAbsolutePath() + File
            .separator + "Documents/";

    //type
    public static final int TYPE_ZHIHU = 101;

    public static final int TYPE_ANDROID = 102;

    public static final int TYPE_IOS = 103;

    public static final int TYPE_WEB = 104;

    public static final int TYPE_GIRL = 105;

    public static final int TYPE_WECHAT = 106;

    public static final int TYPE_WECHAT_NEWS = 107;

    public static final int TYPE_WECHAT_FANS = 108;

    public static final int TYPE_WECHAT_BEAUTY = 109;

    public static final int TYPE_GANK = 110;

    public static final int TYPE_FUNNY = 111 ;

    public static final int TYPE_NEWS_SOCIAL = 112 ;

    public static final int TYPE_NEWS_DOMESTIC = 113 ;

    public static final int TYPE_NEWS_INTERNATIONAL = 114 ;

    public static final int TYPE_NEWS_FUNNY = 115 ;

    public static final int TYPE_GOLD = 116;

    public static final int TYPE_SETTING = 117;

    public static final int TYPE_COLLECTION = 118;

    public static final int TYPE_ABOUT = 119;

    //================= INTENT ====================
    public static final String ITEM_TYPE = "type";

    public static final String ITEM_TYPE_CODE = "type_code";

    public static final String IT_DETAIL_TITLE = "title";

    public static final String IT_DETAIL_URL = "url";

    public static final String IT_DETAIL_IMG_URL = "img_url";

    public static final String IT_DETAIL_ID = "id";

    public static final String IT_DETAIL_TYPE = "type";

    public static final String IT_GOLD_TYPE = "type";

    public static final String IT_GOLD_TYPE_STR = "type_str";

    public static final String IT_GOLD_MANAGER = "manager";

    public static final String SP_MANAGER_POINT = "manager_point";

    public static final String SP_VERSION_POINT = "version_point";
}
