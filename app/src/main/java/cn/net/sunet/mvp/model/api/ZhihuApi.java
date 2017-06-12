package cn.net.sunet.mvp.model.api;

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
import cn.net.sunet.mvp.model.entity.WelcomeBean;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/26
   Modify: 2016/12/26
 * Description: zhihu apis
 */

interface ZhihuApi {
    String BASE_URI = "http://news-at.zhihu.com/api/4/";

    /*
     * 启动界面
     * @param res 图片大小（分辨率)
     */
    @GET("start-image/{res}")
    Observable<WelcomeBean> welcome(@Path("res") String res);

    /*
     * 最新日报
     */
    @GET("news/latest")
    Observable<DailyNewsLatestBean> dailyNewsLatest();

    /*
     * 往期日报
     */
    @GET("news/before/{date}")
    Observable<DailyNewsBeforeBean> dailyNewsBefore(@Path("date") String date);

    /*
     * 主题日报
     */
    @GET("themes")
    Observable<DailyThemeBean> dailyThemes();

    /*
     * 主题日报详情
     * @param id 某一个主题的id
     */
    @GET("theme/{id}")
    Observable<DailyThemeDetailBean> dailyThemeDetail(@Path("id") int id);

    /*
     * 专栏日报
     */
    @GET("sections")
    Observable<DailySectionsBean> dailySections();

    /*
     * 专栏日报详情
     * @param 某一个专栏的id
     */
    @GET("section/{id}")
    Observable<DailySectionDetailBean> dailySectionDetail(@Path("id") int id);

    /*
     * 热门日报
     */
    @GET("news/hot")
    Observable<DailyHotBean> dailyHot();

    /*
     * 日报详情
     * @param id 某一条日报的详情
     */
    @GET("news/{id}")
    Observable<DailyDetailBean> dailyDetail(@Path("id") int id);

    /*
     * 日报的额外信息
     * @param id 某一条日报的id
     */
    @GET("story-extra/{id}")
    Observable<DailyStoryExtraBean> dailyDetailExtra(@Path("id") int id);

    /*
     * 日报的长评论
     * @param id
     */
    @GET("story/{id}/long-comments")
    Observable<DailyCommentBean> dailyLongComment(@Path("id") int id);

    /*
     * 日报的短评论
     * @param id
     */
    @GET("story/{id}/short-comments")
    Observable<DailyCommentBean> dailyShortComment(@Path("id") int id);
}
