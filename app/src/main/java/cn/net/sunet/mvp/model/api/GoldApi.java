package cn.net.sunet.mvp.model.api;

import java.util.List;

import cn.net.sunet.base.GoldBaseResponse;
import cn.net.sunet.mvp.model.entity.GoldItemBean;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/19
   Modify: 2017/1/19
 * Description: gold api
 */

interface GoldApi {
    String BASE_URI = "https://api.leancloud.cn/";

    /*
     * 文章列表
     */
    @GET("1.1/classes/Entry")
    Observable<GoldBaseResponse<List<GoldItemBean>>> goldData(@Header("X-LC-Id") String id,
                                                              @Header("X-LC-Sign") String sign,
                                                              @Query("where") String where,
                                                              @Query("order") String order,
                                                              @Query("include") String include,
                                                              @Query("limit") int limit,
                                                              @Query("skip") int skip);

    /*
     * 热门推荐
     */
    @GET("1.1/classes/Entry")
    Observable<GoldBaseResponse<List<GoldItemBean>>> goldHotData(@Header("X-LC-Id") String id,
                                                                 @Header("X-LC-Sign") String sign,
                                                                 @Query("where") String where,
                                                                 @Query("order") String order,
                                                                 @Query("include") String include,
                                                                 @Query("limit") int limit);
}
