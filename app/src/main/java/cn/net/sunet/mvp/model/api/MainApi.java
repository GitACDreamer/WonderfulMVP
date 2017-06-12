package cn.net.sunet.mvp.model.api;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
   Modify: 2016/12/22
 * Description: main api
 */

import cn.net.sunet.base.WeChatBaseResponse;
import cn.net.sunet.mvp.model.entity.LoginBean;
import cn.net.sunet.mvp.model.entity.VersionBean;
import cn.net.sunet.mvp.model.entity.WelcomeBean;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

interface MainApi {
    String BASE_URI = "http://www.bestsource.cn:80/bestsource/";

    /*
     * 获取欢迎界面图片
     */
    @GET("/welcome")
    Observable<WeChatBaseResponse<WelcomeBean>> welcome();

    /*
     * 获取最新版本信息
     */
    @GET("/version")
    Observable<WeChatBaseResponse<VersionBean>> version();

    /*
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return observable
     */
    @FormUrlEncoded
    @POST("/login")
    Observable<WeChatBaseResponse<LoginBean>> login(@Field("user_name") String username,
                                                    @Field("password") String password);

    /*
     * 用户注册
     * @param username 用户名
     * @param phone_number
     * @param password 密码
     * @return observable
     */
    @FormUrlEncoded
    @POST("/register")
    Observable<WeChatBaseResponse<LoginBean>> register(@Field("user_name") String username,
                                                       @Field("phone_number") String pone_number,
                                                       @Field("password") String password);

    /*
     * 切换用户
     */
    @GET("/logout")
    Observable<WeChatBaseResponse<String>> logout();
}
