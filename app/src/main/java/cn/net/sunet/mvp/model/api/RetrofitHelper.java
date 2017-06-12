package cn.net.sunet.mvp.model.api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.net.sunet.BuildConfig;
import cn.net.sunet.app.Constants;
import cn.net.sunet.base.GankBaseResponse;
import cn.net.sunet.base.GoldBaseResponse;
import cn.net.sunet.base.WeChatBaseResponse;
import cn.net.sunet.mvp.model.entity.DailyCommentBean;
import cn.net.sunet.mvp.model.entity.DailyDetailBean;
import cn.net.sunet.mvp.model.entity.DailyHotBean;
import cn.net.sunet.mvp.model.entity.DailyNewsBeforeBean;
import cn.net.sunet.mvp.model.entity.DailyNewsLatestBean;
import cn.net.sunet.mvp.model.entity.DailySectionDetailBean;
import cn.net.sunet.mvp.model.entity.DailySectionsBean;
import cn.net.sunet.mvp.model.entity.DailyStoryExtraBean;
import cn.net.sunet.mvp.model.entity.DailyThemeBean;
import cn.net.sunet.mvp.model.entity.DailyThemeDetailBean;
import cn.net.sunet.mvp.model.entity.GankItemBean;
import cn.net.sunet.mvp.model.entity.GankSearchItemBean;
import cn.net.sunet.mvp.model.entity.GoldItemBean;
import cn.net.sunet.mvp.model.entity.VersionBean;
import cn.net.sunet.mvp.model.entity.WeChatBean;
import cn.net.sunet.mvp.model.entity.WelcomeBean;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
   Modify: 2016/12/22
 * Description: retrofit 请求库Helper类
 */

public class RetrofitHelper {
    private static MainApi mMainApi = null;
    private static ZhihuApi mZhihuApi = null;
    private static WeChatApi mWeChatApi = null;
    private static GankApi mGankApi = null;
    private static GoldApi mGoldApi = null;
    private static OkHttpClient mOkHttpClient = null;

    private void init() {
        initOkHttp();
        mMainApi = getApiService(MainApi.BASE_URI, MainApi.class);
        mZhihuApi = getApiService(ZhihuApi.BASE_URI, ZhihuApi.class);
        mWeChatApi = getApiService(WeChatApi.BASE_URI, WeChatApi.class);
        mGankApi = getApiService(GankApi.BASE_URI, GankApi.class);
        mGoldApi = getApiService(GoldApi.BASE_URI, GoldApi.class);
    }

    public RetrofitHelper() {
        init();
    }

    private static void initOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //在debug状态，添加日志拦截
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(interceptor);
        }
        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        mOkHttpClient = builder.build();
    }

    private <T> T getApiService(String baseUrl, Class<T> clz) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(clz);
    }

    //获取欢迎界面图片
    public Observable<WelcomeBean> welcome(String res) {
        return mZhihuApi.welcome(res);
    }

    //检测是否有新版本
    public Observable<WeChatBaseResponse<VersionBean>> version() {
        return mMainApi.version();
    }


    /*
   * 最新日报
   */
    public Observable<DailyNewsLatestBean> dailyNewsLatest() {
        return mZhihuApi.dailyNewsLatest();
    }


    /*
     * 往期日报
     */
    public Observable<DailyNewsBeforeBean> dailyNewsBefore(String date) {
        return mZhihuApi.dailyNewsBefore(date);
    }

    /*
     * 主题日报
     */
    public Observable<DailyThemeBean> dailyThemes() {
        return mZhihuApi.dailyThemes();
    }

    /*
     * 主题日报详情
     * @param id 某一个主题的id
     */
    public Observable<DailyThemeDetailBean> dailyThemeDetail(int id) {
        return mZhihuApi.dailyThemeDetail(id);
    }

    /*
     * 专栏日报
     */
    public Observable<DailySectionsBean> dailySections() {
        return mZhihuApi.dailySections();
    }

    /*
     * 专栏日报详情
     * @param 某一个专栏的id
     */
    public Observable<DailySectionDetailBean> dailySectionDetail(int id) {
        return mZhihuApi.dailySectionDetail(id);
    }

    /*
     * 热门日报
     */
    public Observable<DailyHotBean> dailyHot() {
        return mZhihuApi.dailyHot();
    }

    /*
     * 日报详情
     * @param id 某一条日报的详情
     */
    public Observable<DailyDetailBean> dailyDetail(int id) {
        return mZhihuApi.dailyDetail(id);
    }

    /*
     * 日报的额外信息
     * @param id 某一条日报的id
     */
    public Observable<DailyStoryExtraBean> dailyDetailExtra(int id) {
        return mZhihuApi.dailyDetailExtra(id);
    }

    /*
     * 日报的长评论
     * @param id
     */
    public Observable<DailyCommentBean> dailyLongComment(int id) {
        return mZhihuApi.dailyLongComment(id);
    }

    /*
     * 日报的短评论
     * @param id
     */
    public Observable<DailyCommentBean> dailyShortComment(int id) {
        return mZhihuApi.dailyShortComment(id);
    }

    /*
     * 微信精选列表
     */
    public Observable<WeChatBaseResponse<List<WeChatBean>>> wechatHot(int num, int page) {
        return mWeChatApi.wechatHot(Constants.WECHAT_KEY, num, page);
    }

    /*
     * 在微信精选中搜索
     */
    public Observable<WeChatBaseResponse<List<WeChatBean>>> wechatHotSearch(int num, int page, String word) {
        return mWeChatApi.wechatHotSearch(Constants.WECHAT_KEY, num, page, word);
    }

    /*
     * 美女图片
     */
    public Observable<WeChatBaseResponse<List<WeChatBean>>> wechatBeauty(int num, int page) {
        return mWeChatApi.wechatBeauty(Constants.WECHAT_KEY, num, page);
    }

    /*
     * 搜索美女图片
     */
    public Observable<WeChatBaseResponse<List<WeChatBean>>> wechatBeautySearch(int num, int page, String word) {
        return mWeChatApi.wechatBeautySearch(Constants.WECHAT_KEY, num, page, word);
    }

    /*
     * 奇闻轶事
     */
    public Observable<WeChatBaseResponse<List<WeChatBean>>> wechatFantastic(int num, int page) {
        return mWeChatApi.wechatFantastic(Constants.WECHAT_KEY, num, page);
    }

    /*
     * 搜索奇闻轶事
     */
    public Observable<WeChatBaseResponse<List<WeChatBean>>> wechatFantasticSearch(int num, int page, String word) {
        return mWeChatApi.wechatFantasticSearch(Constants.WECHAT_KEY, num, page, word);
    }

    /*
     * 社会新闻
     */
    public Observable<WeChatBaseResponse<List<WeChatBean>>> wechatSocial(int num, int page) {
        return mWeChatApi.wechatSocial(Constants.WECHAT_KEY, num, page);
    }

    /*
    * 社会新闻搜索
    */
    public Observable<WeChatBaseResponse<List<WeChatBean>>> wechatSocialSearch(int num, int page, String word) {
        return mWeChatApi.wechatSocialSearch(Constants.WECHAT_KEY, num, page, word);
    }

    /*
     * 国内新闻
     */
    public Observable<WeChatBaseResponse<List<WeChatBean>>> wechatDomestic(int num, int page) {
        return mWeChatApi.wechatDomestic(Constants.WECHAT_KEY, num, page);
    }

    /*
     * 国内新闻搜索
     */
    public Observable<WeChatBaseResponse<List<WeChatBean>>> wechatDomesticSearch(int num, int page, String word) {
        return mWeChatApi.wechatDomesticSearch(Constants.WECHAT_KEY, num, page, word);
    }

    /*
     * 国际新闻
     */
    public Observable<WeChatBaseResponse<List<WeChatBean>>> wechatInternational(int num, int page) {
        return mWeChatApi.wechatInternational(Constants.WECHAT_KEY, num, page);
    }

    /*
     * 国际新闻搜索
     */
    public Observable<WeChatBaseResponse<List<WeChatBean>>> wechatInternationalSearch(int num, int page, String word) {
        return mWeChatApi.wechatInternationalSearch(Constants.WECHAT_KEY, num, page, word);
    }

    /*
     * 娱乐新闻
     */
    public Observable<WeChatBaseResponse<List<WeChatBean>>> wechatFunny(int num, int page) {
        return mWeChatApi.wechatFunny(Constants.WECHAT_KEY, num, page);
    }

    /*
     * 娱乐新闻搜索
     */
    public Observable<WeChatBaseResponse<List<WeChatBean>>> wechatFunnySearch(int num, int page, String word) {
        return mWeChatApi.wechatFunnySearch(Constants.WECHAT_KEY, num, page, word);
    }

    /*
     * 获取技术文章列表
     */
    public Observable<GankBaseResponse<List<GankItemBean>>> techList(String tech, int num, int page) {
        return mGankApi.techList(tech, num, page);
    }

    /*
     * 获取妹子列表
     */
    public Observable<GankBaseResponse<List<GankItemBean>>> girlList(int num, int page) {
        return mGankApi.girlList(num, page);
    }

    /*
     * 获取随机妹子列表
     */
    public Observable<GankBaseResponse<List<GankItemBean>>> randomGirl(int num) {
        return mGankApi.randomGirl(num);
    }

    /*
     * 获取搜索结果
     */
    public Observable<GankBaseResponse<List<GankSearchItemBean>>> gankSearchList(String query, String type, int num,
                                                                                 int page) {
        return mGankApi.searchList(query, type, num, page);
    }

    /*
     * gold文章列表
     */
    public Observable<GoldBaseResponse<List<GoldItemBean>>> goldData(String type, int num, int page) {
        return mGoldApi.goldData(Constants.LEANCLOUD_ID,
                Constants.LEANCLOUD_SIGN,
                "{\"category\":\"" + type + "\"}",
                "-createdAt",
                "user,user.installation",
                num,
                page * num);
    }

    /*
     * gold 热门推荐
     */
    public Observable<GoldBaseResponse<List<GoldItemBean>>> goldHotData(String type, String datetime, int limit) {
        return mGoldApi.goldHotData(Constants.LEANCLOUD_ID,
                Constants.LEANCLOUD_SIGN,
                "{\"category\":\"" + type + "\"," +
                        "\"createdAt\":{\"$gt\":{\"__type\":\"Date\",\"iso\":\"" + datetime + "T00:00:00.000Z\"}}," +
                        "\"objectId\":{\"$nin\":[\"58362f160ce463005890753e\",\"583659fcc59e0d005775cc8c\"," +
                        "\"5836b7358ac2470065d3df62\"]}}",
                "-hotIndex",
                "user,user.installation",
                limit);
    }
}
