package cn.net.sunet.base;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.blankj.utilcode.utils.ToastUtils;

import cn.net.sunet.R;
import cn.net.sunet.app.App;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
   Modify: 2016/12/22
 * Description: Fragment 基类
 */

public abstract class BaseMainFragment extends BaseFragment {

    private boolean bDoubleBackToExitPressedOnce;
    private Handler mHandle = new Handler();
    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            bDoubleBackToExitPressedOnce = false;
        }
    };
    protected OnOpenDrawerListener mOpenDrawerListener;

    public interface OnOpenDrawerListener {
        void onOpenDrawer();
    }

    protected void initToolbarNav(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.mipmap.ic_menu_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOpenDrawerListener != null) {
                    mOpenDrawerListener.onOpenDrawer();
                }
            }
        });
    }


    @Override
    protected FragmentAnimator onCreateFragmentAnimator() {
        FragmentAnimator fragmentAnimator = _mActivity.getFragmentAnimator();
        fragmentAnimator.setEnter(0);
        fragmentAnimator.setExit(0);
        return fragmentAnimator;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnOpenDrawerListener) {
            mOpenDrawerListener = (OnOpenDrawerListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnOpenDrawerListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOpenDrawerListener = null;
    }

    @Override
    public boolean onBackPressedSupport() {
        if (bDoubleBackToExitPressedOnce) {
            ToastUtils.cancelToast();
            if (mHandle != null)
                mHandle.removeCallbacks(mRunnable);
            App.getInstance().exitApp();
        }
        bDoubleBackToExitPressedOnce = true;
        ToastUtils.showShortToast("再按一次退出程序");
        mHandle.postDelayed(mRunnable, 2000);
        return true ;
    }
}
