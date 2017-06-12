package cn.net.sunet.base;

import android.support.v7.widget.Toolbar;
import android.view.View;

import cn.net.sunet.R;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
   Modify: 2016/12/22
 * Description: 带返回导航栏的Fragment基类
 */

public abstract class BaseBackFragment extends BaseFragment {

    //加载导航栏(back按钮)
    protected void initToolbarNav(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
    }
}
