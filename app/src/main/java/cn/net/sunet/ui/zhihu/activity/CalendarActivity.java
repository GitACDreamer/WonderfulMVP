package cn.net.sunet.ui.zhihu.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.widget.CalendarView;

import com.blankj.utilcode.utils.TimeUtils;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;
import cn.net.sunet.R;
import cn.net.sunet.base.SimpleActivity;
import cn.net.sunet.event.CalendarDayEvent;
import cn.net.sunet.utils.RxBus;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/2
   Modify: 2017/1/2
 * Description: calendar activity
 */

public class CalendarActivity extends SimpleActivity {
    @BindView(R.id.view_calender)
    CalendarView mCalendarView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    CalendarDayEvent mCalendarEvent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_calendar;
    }

    @Override
    protected void initView() {
        mToolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
        setToolBar(mToolbar, "选择日期");
        mCalendarEvent = new CalendarDayEvent();
        mCalendarView.setFirstDayOfWeek(Calendar.MONDAY);
        mCalendarView.setMinDate(TimeUtils.string2Millis("2015-01-01", "yyyy-MM-dd"));
        mCalendarView.setMaxDate(TimeUtils.getNowTimeMills());
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                mCalendarEvent.setDate(year, month, dayOfMonth);
            }
        });
    }

    @OnClick(R.id.tv_calender_enter)
    void chooseDate() {
        RxBus.getDefault().post(mCalendarEvent);
        finish();
    }
}
