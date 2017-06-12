package cn.net.sunet.base;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
   Modify: 2016/12/22
 * Description: base presenter
 */

public interface BasePresenter<T extends BaseView> {
      void attachView(T view);

      void detachView();
}
