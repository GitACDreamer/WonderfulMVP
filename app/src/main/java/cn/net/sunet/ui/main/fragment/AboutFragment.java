package cn.net.sunet.ui.main.fragment;

import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import butterknife.BindView;
import cn.net.sunet.R;
import cn.net.sunet.base.SimpleFragment;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
   Modify: 2016/12/22
 * Description: about fragment
 */

public class AboutFragment extends SimpleFragment {
    @BindView(R.id.tv_goto_sunnet)
    TextView tv_to_sunet;
    @BindView(R.id.tv_goto_copyright)
    TextView tv_to_copyright;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_about;
    }

    @Override
    protected void initView() {
        tv_to_sunet.setMovementMethod(LinkMovementMethod.getInstance());
        tv_to_copyright.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
