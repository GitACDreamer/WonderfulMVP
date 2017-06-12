package cn.net.sunet.mvp.model.api;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/11
   Modify: 2017/1/11
 * Description: wechat apis
 */

import java.util.List;

import cn.net.sunet.base.WeChatBaseResponse;
import cn.net.sunet.mvp.model.entity.WeChatBean;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

interface WeChatApi {
    String BASE_URI = "https://api.tianapi.com/";

    /*
     * 微信精选列表
     */
    @GET("wxnew")
    Observable<WeChatBaseResponse<List<WeChatBean>>> wechatHot(@Query("key") String key,
                                                               @Query("num") int num,
                                                               @Query("page") int page);

    /*
     * 搜索微信精选列表
     */
    @GET("wxnew")
    Observable<WeChatBaseResponse<List<WeChatBean>>> wechatHotSearch(@Query("key") String key,
                                                                     @Query("num") int num,
                                                                     @Query("page") int page,
                                                                     @Query("word") String word);

    /*
     * 美女图片
     */
    @GET("meinv")
    Observable<WeChatBaseResponse<List<WeChatBean>>> wechatBeauty(@Query("key") String key,
                                                                  @Query("num") int num,
                                                                  @Query("page") int page);

    /*
     * 搜索美女图片
     */
    @GET("meinv")
    Observable<WeChatBaseResponse<List<WeChatBean>>> wechatBeautySearch(@Query("key") String key,
                                                                        @Query("num") int num,
                                                                        @Query("page") int page,
                                                                        @Query("word") String word);

    /*
     * 奇闻轶事
     */
    @GET("qiwen")
    Observable<WeChatBaseResponse<List<WeChatBean>>> wechatFantastic(@Query("key") String key,
                                                                     @Query("num") int num,
                                                                     @Query("page") int page);

    /*
     * 搜索奇闻轶事
     */
    @GET("qiwen")
    Observable<WeChatBaseResponse<List<WeChatBean>>> wechatFantasticSearch(@Query("key") String key,
                                                                           @Query("num") int num,
                                                                           @Query("page") int page,
                                                                           @Query("word") String word);

    /*
     * 社会新闻
     */
    @GET("social")
    Observable<WeChatBaseResponse<List<WeChatBean>>> wechatSocial(@Query("key") String key,
                                                                  @Query("num") int num,
                                                                  @Query("page") int page);

    /*
    * 社会新闻搜索
    */
    @GET("social")
    Observable<WeChatBaseResponse<List<WeChatBean>>> wechatSocialSearch(@Query("key") String key,
                                                                        @Query("num") int num,
                                                                        @Query("page") int page,
                                                                        @Query("word") String word);

    /*
     * 国内新闻
     */
    @GET("guonei")
    Observable<WeChatBaseResponse<List<WeChatBean>>> wechatDomestic(@Query("key") String key,
                                                                    @Query("num") int num,
                                                                    @Query("page") int page);

    /*
    * 国内新闻搜索
    */
    @GET("guonei")
    Observable<WeChatBaseResponse<List<WeChatBean>>> wechatDomesticSearch(@Query("key") String key,
                                                                          @Query("num") int num,
                                                                          @Query("page") int page,
                                                                          @Query("word") String word);

    /*
    * 国际新闻
    */
    @GET("world")
    Observable<WeChatBaseResponse<List<WeChatBean>>> wechatInternational(@Query("key") String key,
                                                                         @Query("num") int num,
                                                                         @Query("page") int page);

    /*
    * 国际新闻搜索
    */
    @GET("world")
    Observable<WeChatBaseResponse<List<WeChatBean>>> wechatInternationalSearch(@Query("key") String key,
                                                                               @Query("num") int num,
                                                                               @Query("page") int page,
                                                                               @Query("word") String word);

    /*
    * 娱乐新闻
    */
    @GET("huabian")
    Observable<WeChatBaseResponse<List<WeChatBean>>> wechatFunny(@Query("key") String key,
                                                                 @Query("num") int num,
                                                                 @Query("page") int page);

    /*
    * 搜索花边新闻
    */
    @GET("huabian")
    Observable<WeChatBaseResponse<List<WeChatBean>>> wechatFunnySearch(@Query("key") String key,
                                                                       @Query("num") int num,
                                                                       @Query("page") int page,
                                                                       @Query("word") String word);
}
