package cn.net.sunet.mvp.model.entity;

import java.util.List;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/26
   Modify: 2016/12/26
 * Description: 最新日报bean
 */

public class DailyNewsLatestBean {

    /**
     * date : 20161226
     * stories : [{"images":["http://pic2.zhimg.com/57d0496b12849158b615a9f6b34db689.jpg"],"type":0,"id":9063132,
     * "ga_prefix":"122617","title":"老师们给的成绩越来越高，学校不乐意了"},{"images":["http://pic1.zhimg
     * .com/d739581acf187fa506bbd3a07c242974.jpg"],"type":0,"id":9097926,"ga_prefix":"122615","title":"2017
     * 年，投资投什么，创业创什么？"},{"title":"动画片里小动物的毛发是一根一根画出来的吗？","ga_prefix":"122614","images":["http://pic4.zhimg
     * .com/99dff82ce99652ee9eb1f557c82bfcfb.jpg"],"multipic":true,"type":0,"id":9097680},{"images":["http://pic2
     * .zhimg.com/7299607cde79a54740ccb7d6bbf8b2d1.jpg"],"type":0,"id":9097418,"ga_prefix":"122613",
     * "title":"「我看见这一代最杰出的头脑毁于骗点击」"},{"images":["http://pic4.zhimg.com/27b29e116b57c452c03ae2c422c1481b.jpg"],
     * "type":0,"id":9097139,"ga_prefix":"122612","title":"大误 · 都别和我争，我才是被社会抛弃的人"},{"title":"这样用衣柜，才是真的放满了",
     * "ga_prefix":"122611","images":["http://pic2.zhimg.com/0cd8b74bd10f2276d3ba57a45c06f535.jpg"],"multipic":true,
     * "type":0,"id":9096126},{"images":["http://pic3.zhimg.com/ccf1598fbc4ea2d3c5a480ba80c4edae.jpg"],"type":0,
     * "id":9090624,"ga_prefix":"122610","title":"为什么品牌这么爱拍圣诞小电影？"},{"images":["http://pic2.zhimg
     * .com/086d83be1e13c4308d33babce0db0b95.jpg"],"type":0,"id":9090242,"ga_prefix":"122609",
     * "title":"这几位中国青年作家的作品可以读一读"},{"title":"认真起来的汉堡，也可以是堪称绝妙的大餐","ga_prefix":"122608","images":["http://pic1.zhimg
     * .com/e878a0d7b820e321cad8632ca3d74edc.jpg"],"multipic":true,"type":0,"id":9095981},{"images":["http://pic1
     * .zhimg.com/7cd155bc4d1a93f7ddc37a63db8314b4.jpg"],"type":0,"id":9095647,"ga_prefix":"122607",
     * "title":"领导和下属的博弈，拼的是立场不是真相"},{"images":["http://pic3.zhimg.com/9e6f860bc17ea89cfb1d66941ea0373a.jpg"],
     * "type":0,"id":9095809,"ga_prefix":"122607","title":"圣诞节一到，正儿八经的学术期刊也「不正经」了起来"},{"title":"2016 年度盘点 ·
     * 我心目中的最佳专辑","ga_prefix":"122607","images":["http://pic1.zhimg.com/260f887dbdd54a34ff5700ba9fd28644.jpg"],
     * "multipic":true,"type":0,"id":9096097},{"images":["http://pic3.zhimg.com/95a54e9c5b4aa54d0c5e352c25c23222
     * .jpg"],"type":0,"id":9095003,"ga_prefix":"122606","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"http://pic3.zhimg.com/a59289fcd48c67a24294db62ffece6fa.jpg","type":0,"id":9097926,
     * "ga_prefix":"122615","title":"2017 年，投资投什么，创业创什么？"},{"image":"http://pic1.zhimg
     * .com/87c3349627685831f5601c1996802798.jpg","type":0,"id":9097680,"ga_prefix":"122614",
     * "title":"动画片里小动物的毛发是一根一根画出来的吗？"},{"image":"http://pic4.zhimg.com/d7401f4b94923dd9051539084b984fb3.jpg",
     * "type":0,"id":9097418,"ga_prefix":"122613","title":"「我看见这一代最杰出的头脑毁于骗点击」"},{"image":"http://pic2.zhimg
     * .com/eded11f11a91e323bca5af3d212258ad.jpg","type":0,"id":9095647,"ga_prefix":"122607",
     * "title":"领导和下属的博弈，拼的是立场不是真相"},{"image":"http://pic4.zhimg.com/dc48d1d3f9d013214a085149d610a11b.jpg","type":0,
     * "id":9096097,"ga_prefix":"122607","title":"2016 年度盘点 · 我心目中的最佳专辑"}]
     */

    private String date;
    private List<StoriesEntity> stories;
    private List<TopStoriesEntity> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesEntity> getStories() {
        return stories;
    }

    public void setStories(List<StoriesEntity> stories) {
        this.stories = stories;
    }

    public List<TopStoriesEntity> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesEntity> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesEntity {
        /**
         * images : ["http://pic2.zhimg.com/57d0496b12849158b615a9f6b34db689.jpg"]
         * type : 0
         * id : 9063132
         * ga_prefix : 122617
         * title : 老师们给的成绩越来越高，学校不乐意了
         * multipic : true
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private boolean multipic;
        private List<String> images;
        private boolean readState ;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public boolean isReadState() {
            return readState;
        }

        public void setReadState(boolean readState) {
            this.readState = readState;
        }
    }

    public static class TopStoriesEntity {
        /**
         * image : http://pic3.zhimg.com/a59289fcd48c67a24294db62ffece6fa.jpg
         * type : 0
         * id : 9097926
         * ga_prefix : 122615
         * title : 2017 年，投资投什么，创业创什么？
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
