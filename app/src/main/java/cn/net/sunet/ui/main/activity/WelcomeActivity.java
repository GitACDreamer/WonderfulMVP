package cn.net.sunet.ui.main.activity;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/26
   Modify: 2016/12/26
 * Description: 欢迎界面
 */

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import cn.net.sunet.R;
import cn.net.sunet.base.BaseActivity;
import cn.net.sunet.mvp.contract.WelcomeContract;
import cn.net.sunet.mvp.model.entity.WelcomeBean;
import cn.net.sunet.mvp.presenter.WelcomePresenter;
import cn.net.sunet.utils.ImageLoader;

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements WelcomeContract.View {

    @BindView(R.id.iv_welcome_bg)
    ImageView ivWelcomeBg;
    @BindView(R.id.tv_welcome_author)
    TextView tvWelcomeAuthor;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        mPresenter.welcome();
    }

    @Override
    public void showContent(WelcomeBean bean) {
        ImageLoader.load(this, bean.getImg(), ivWelcomeBg);
        ivWelcomeBg.animate().scaleX(1.12f).scaleY(1.12f).setDuration(2000).setStartDelay(100).start();
        tvWelcomeAuthor.setText(bean.getText());
    }

    @Override
    public void jumpToLogin() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    protected void onDestroy() {
        Glide.clear(ivWelcomeBg);
        mPresenter.detachView();
        super.onDestroy();
    }
}
