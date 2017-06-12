package cn.net.sunet.mvp.model.api;

import java.util.List;

import cn.net.sunet.base.GankBaseResponse;
import cn.net.sunet.mvp.model.entity.GankItemBean;
import cn.net.sunet.mvp.model.entity.GankSearchItemBean;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/17
   Modify: 2017/1/17
 * Description: gank api
 */

interface GankApi {
    String BASE_URI = "http://gank.io/api/";

    /*
     * 技术文章列表
     */
    @GET("data/{tech}/{num}/{page}")
    Observable<GankBaseResponse<List<GankItemBean>>> techList(@Path("tech") String tech,
                                                              @Path("num") int num,
                                                              @Path("page") int page);

    /*
     * 妹子列表
     */
    @GET("data/福利/{num}/{page}")
    Observable<GankBaseResponse<List<GankItemBean>>> girlList(@Path("num") int num,
                                                              @Path("page") int page);

    /*
     * 随机妹子图
     */
    @GET("random/data/福利/{num}")
    Observable<GankBaseResponse<List<GankItemBean>>> randomGirl(@Path("num") int num);

    /*
     * 搜索
     */
    @GET("search/query/{query}/category/{type}/count/{count}/page/{page}")
    Observable<GankBaseResponse<List<GankSearchItemBean>>> searchList(@Path("query") String query,
                                                                      @Path("type") String type,
                                                                      @Path("count") int num,
                                                                      @Path("page") int page);
}
