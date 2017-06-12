package cn.net.sunet.event;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/22
   Modify: 2016/12/22
 * Description: event bus tab按钮重复选择时调用
 */

public class TabSelectedEvent {
    public int position;

    public TabSelectedEvent(int position) {
        this.position = position;
    }
}