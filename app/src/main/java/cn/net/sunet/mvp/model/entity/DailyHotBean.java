package cn.net.sunet.mvp.model.entity;

import java.util.List;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/26
   Modify: 2016/12/26
 * Description: 热门日报
 */

public class DailyHotBean {


    private List<RecentEntity> recent;

    public List<RecentEntity> getRecent() {
        return recent;
    }

    public void setRecent(List<RecentEntity> recent) {
        this.recent = recent;
    }

    public static class RecentEntity {
        /**
         * news_id : 9075529
         * url : http://news-at.zhihu.com/api/2/news/9075529
         * thumbnail : http://pic1.zhimg.com/887ef9af3e7c95edbd657bc000c476e4.jpg
         * title : 手指肿痛长倒刺发炎？别怕，教你做些简单处理
         */

        private int news_id;
        private String url;
        private String thumbnail;
        private String title;
        private boolean readState ;

        public int getNews_id() {
            return news_id;
        }

        public void setNews_id(int news_id) {
            this.news_id = news_id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isReadState() {
            return readState;
        }

        public void setReadState(boolean readState) {
            this.readState = readState;
        }
    }
}
