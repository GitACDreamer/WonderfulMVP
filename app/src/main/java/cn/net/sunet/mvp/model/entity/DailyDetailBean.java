package cn.net.sunet.mvp.model.entity;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/26
   Modify: 2016/12/26
 * Description: 日报详情
 */

import java.util.List;

public class DailyDetailBean {

    /**
     * body : <div class="main-wrap content-wrap">
     * <div class="headline">
     * <p>
     * <div class="img-place-holder"></div>
     * <p>
     * </div>
     * <p>
     * <div class="content-inner">
     * <p>
     * <div class="question">
     * <h2 class="question-title"></h2>
     * <p>
     * <div class="answer">
     * <p>
     * <div class="content">
     * <p>又是一年即将结束，「2016 年度盘点」专题带你一起回顾这一年的热门大事、行业动向和个人成长。欢迎在评论区聊聊你所经历的 2016~</p>
     * </div>
     * </div>
     * <p>
     * </div>
     * <p>
     * <div class="question">
     * <h2 class="question-title">2016 年哪些互联网技术开始变得流行，哪些过时了？</h2>
     * <p>
     * <div class="answer">
     * <p>
     * <div class="meta">
     * <img class="avatar" src="http://pic2.zhimg.com/34feca667836b731f6e88c42c2727cbd_is.jpg">
     * <span class="author">贺嘉，</span><span class="bio">腾讯云布道师、TEDxZhuhai策展人</span>
     * </div>
     * <p>
     * <div class="content">
     * <p><strong>我个人看来评价技术流行与否的标准，一般参考标准的行业指数，另一方面就是看实际开发实践中程序员们是否实际使用该项技术。</strong></p>
     * <p><strong>所以我从编程语言、移动开发、中间件、大数据这几个热门技术领域，尝试着给大家分析一下我和我身边的开发者们使用的技术趋势的一些变迁。
     * </strong>（由于个人知识所限，无法涵盖图形学、运维、安全、测试等等所有的技术领域，欢迎技术大牛们 @Milo Yip 补充）<strong><br /><br />1.编程语言排行</strong></p>
     * <p>参考业界知名的 TIOBE 的 16 年 12 月编程语言排名，<strong>可以看出来 C 语言的下降趋势明显，Ruby、C#的流行度有所下降，Swift 和 Go 语言有一定上升趋势。</strong></p>
     * <p>TOIBE 的观点，C 语言流行下降的重要原因是在当前最火的移动端开发领域，C 语言缺少应用场景；同时 C 也缺少足够分量的公司支持，就像微软之于 C#，Oracle（收购了 Sun）之于 Java。</p>
     * <p><img class="content-image" src="http://pic4.zhimg.com/70/v2-a13369083edf4ab0ccb766c7e416dbf7_b.jpg" alt=""
     * /></p>
     * <p>TIOBE 主要是根据 25 个全球主要搜索引擎的返回结果数量和权重进行的 rating，评价指数详细介绍：<a
     * href="http://link.zhihu.com/?target=http%3A//www.tiobe.com/tiobe-index/programming-languages-definition/">TIOBE
     * - The Software Quality Company</a></p>
     * <ul>
     * <li>Google.com: 7.69%</li>
     * <li>Youtube.com: 7.38%</li>
     * <li>Baidu.com: 7.08%</li>
     * <li>Yahoo.com: 6.77%</li>
     * <li>Wikipedia.org: 6.46%</li>
     * <li>Google.co.in: 6.15%</li>
     * <li>Qq.com: 5.85% ....</li>
     * </ul>
     * <p><strong>2.前端&amp;移动客户端开发技术</strong></p>
     * <blockquote><strong>- 微信小程序 </strong>，相较于 H5
     * 和服务号而言，小程序提供的本地缓存和其他更多接口，使得移动端的开发可以变得更加轻量，用户体验也有很大提升，基本上今年最火的移动开发技术就要数小程序，而且微信定义了自己的一套 MINA
     * 框架，wxss\wxml\wxjs。</blockquote>
     * <p><strong>（百度指数）</strong></p>
     * <p>我今年写的微信小程序教程也在知乎和各个平台获得了不少推荐：</p>
     * <p><a href="https://www.zhihu.com/question/50907897/answer/124096740">如何入门微信小程序开发，有哪些学习资料？ - 贺嘉的回答 - 知乎</a></p>
     * <p><img class="content-image" src="http://pic2.zhimg.com/70/v2-a3f8dcb40dbba43e61cd9cc04c595b21_b.jpg" alt=""
     * /></p>
     * <blockquote><strong>React-Native</strong>，Facebook 的大厂作品，16 年起飞，腾讯、百度等一批大厂都用 RN
     * 重构了自己的移动客户端。更多学习资料:<a href="http://link.zhihu.com/?target=https%3A//facebook.github.io/react-native/">React
     * Native | A framework for building native apps using React</a></blockquote>
     * <p><img class="content-image" src="http://pic2.zhimg.com/70/v2-1228b551a7a4c5550951ab1ed5f88595_b.jpg" alt=""
     * /></p>
     * <blockquote><strong>Vue.js </strong>@尤雨溪大牛的作品，更加轻量而且上手容易，提供更加灵活的数据绑定方式，上手也不难 ，前端里面 2016
     * 年可能是最火的框架。<strong>（google 指数）</strong></blockquote>
     * <p><img class="content-image" src="http://pic4.zhimg.com/70/v2-e08fb8c7e00abc0254c7b5f7fa8f471b_b.jpg" alt=""
     * /></p>
     * <blockquote><strong>Redux</strong>，开始在 16 年变得流行起来，它提供的应用程序的状态容器，这个容器保存了所有运行的状态。这是开发过程中的 time travel
     * 成为了可能。</blockquote>
     * <p><img class="content-image" src="http://pic2.zhimg.com/70/v2-c7e5d311c86786bacc3d7bfca9112e8d_b.jpg" alt=""
     * /></p>
     * <blockquote><strong>AngularJS</strong>，从 15 年中旬开始，越来越不流行。不过评论区有同学指出 angular2 流行度现在不错。<strong>（google
     * 指数）</strong><br />虽说上手容易，但是不止一位 CTO 和我抱怨过前端团队是如何用 AngularJS 把整体的框架越做越复杂，它没有服务器端的页面渲染，而且 google 也不在生产环境用
     * AngularJS。</blockquote>
     * <p><strong><img class="content-image" src="http://pic4.zhimg.com/70/v2-08b5b317b7bc471c5ac99d28daf38c4f_b.jpg"
     * alt="" /><br />3.中间件有关的技术</strong><strong>（google 指数）</strong></p>
     * <blockquote><strong>docker 容器化技术，</strong>在操作系统层面而不是硬件层面进行虚拟化，更加轻量且支持历史版本管理，16 年开始流行度继续上升，达到大红大紫的地步。</blockquote>
     * <p><img class="content-image" src="http://pic4.zhimg.com/70/v2-9367d37005ccc4575b34c72666b67d7f_b.jpg" alt=""
     * /></p>
     * <blockquote><strong>Kubernetes</strong>主要用于容器编排，Google 大规模容器管理系统 borg
     * 的开源版本实现，支持多层安全防护、准入机制、多租户应用支撑、透明的服务注册、服务发现、内建负载均衡、强大的故障发现和自我修复机制。</blockquote>
     * <p><img class="content-image" src="http://pic1.zhimg.com/70/v2-5879347fecb7b1ac6809428e2607c9f0_b.jpg" alt=""
     * /></p>
     * <blockquote><strong>influxdb,
     * </strong>专门用于监控的数据库，开始变得很火，不少创业公司的监控系统都用它做，性能表现不错，但是版本较多，而且版本之间存在一定兼容性问题。</blockquote>
     * <p><img class="content-image" src="http://pic4.zhimg.com/70/v2-5d26943f0bcbba4657eba355611774b7_b.jpg" alt=""
     * /></p>
     * <blockquote><strong>VMware</strong>,老牌厂商的虚拟化技术，热度有所下降。</blockquote>
     * <p><img class="content-image" src="http://pic4.zhimg.com/70/v2-7b7adfee828cdfcb0706d8c029369d7f_b.jpg" alt=""
     * /></p>
     * <p><strong>4.大数据 /AI 相关技术</strong><strong>（google 指数）</strong></p>
     * <blockquote><strong>Hadoop</strong>，流行趋势稍有下降,我知道的是不少 CTO 在从 Hadoop 转向 Spark。</blockquote>
     * <p><img class="content-image" src="http://pic4.zhimg.com/70/v2-8352aa195e01347f3a05cda4d60d817f_b.jpg" alt=""
     * /></p>
     * <blockquote><strong>Spark，</strong>比较新的大数据计算框架，支持批处理、交互式、流处理等多种方式，性能和方案统一性都优于 Hadoop，流行度有所上升。</blockquote>
     * <p><img class="content-image" src="http://pic3.zhimg.com/70/v2-6385f375edf83773777f07ae9971a462_b.jpg" alt=""
     * /></p>
     * <blockquote><strong>Tensorflow，</strong>google 开源的机器学习框架，2016 年大热<br
     * />学习更多参考：<a href="http://link.zhihu.com/?target=http%3A//www.tensorfly.cn/tfdoc/get_started/introduction.html">TensorFlow 官方文档中文版</a></blockquote>
     * <p><img class="content-image" src="http://pic1.zhimg.com/70/v2-eca3efb2a7f0384e5b7589fa6b31ca4c_b.jpg" alt=""
     * /></p>
     * <p><strong>技术流行与否背后的因素是多元的，与编程思想的变化有关，也与硬件技术的变化有关，可能也与参与技术发展的大公司支持有关，也与之前的技术存在的缺憾有关</strong>。</p>
     * <p><strong>而技术的衰落有的时候更多是产品和商业模式带来的冲击，苹果不兼容 Flash 的那一刻开始，多少之前靠 1 分钟 Action Script
     * 可以赚好几万的程序员就面临着不转行就失业的挑战。</strong></p>
     * <p>不少技术的产生往往就是为了解决之前特定技术的问题而诞生，比如说虚拟化技术是为了解决硬件使用效率低下，TCO
     * 总持有成本高企而诞生的，但是更新的容器技术，在现有虚拟化技术基础上，提供更加细粒度的资源共享，使得硬件的使用效率可以进一步提升。</p>
     * <p>一言蔽之，其兴也勃焉，其亡也忽焉...</p>
     * <p><strong>祝各位程序员大大，不用每年追着新技术跑，好人一生平安。</strong></p>
     * <p><img class="content-image" src="http://pic4.zhimg.com/70/v2-0d4cfd3dd0e4f6e39c61544db20be56b_b.jpg" alt=""
     * /></p>
     * </div>
     * </div>
     * <p>
     * <div class="view-more"><a href="http://www.zhihu.com/question/53351477">查看知乎讨论<span
     * class="js-question-holder"></span></a></div>
     * <p>
     * </div>
     * <p>
     * <div class="question">
     * <h2 class="question-title"></h2>
     * <p>
     * <div class="answer">
     * <p>
     * <div class="content">
     * <p>更多讨论，查看&nbsp;知乎圆桌 &middot;&nbsp;<a href="https://www.zhihu
     * .com/roundtable/bestof2016?utm_campaign=official_account&amp;utm_source=zhihudaily&amp;utm_medium=link&amp;
     * utm_content=roundtable">2016 年度盘点</a></p>
     * </div>
     * </div>
     * <p>
     * </div>
     * <p>
     * </div>
     * </div>
     * image_source : Christiaan Colen / CC BY-SA
     * title : 2016 年度盘点 · 现在最流行的编程语言是哪一门？
     * image : http://pic2.zhimg.com/22984e88e1399ac3b5a9c856aa549c11.jpg
     * share_url : http://daily.zhihu.com/story/9092912
     * js : []
     * ga_prefix : 122507
     * section : {"thumbnail":"http://pic1.zhimg.com/260f887dbdd54a34ff5700ba9fd28644.jpg","id":55,"name":"2016 年度盘点"}
     * images : ["http://pic3.zhimg.com/f7e712933deac50b17f0a7631668f1d2.jpg"]
     * type : 0
     * id : 9092912
     * css : ["http://news-at.zhihu.com/css/news_qa.auto.css?v=4b3e3"]
     */

    private String body;
    private String image_source;
    private String title;
    private String image;
    private String share_url;
    private String ga_prefix;
    private SectionEntity section;
    private int type;
    private String id;
    private List<String> js;
    private List<String> images;
    private List<String> css;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public SectionEntity getSection() {
        return section;
    }

    public void setSection(SectionEntity section) {
        this.section = section;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getJs() {
        return js;
    }

    public void setJs(List<String> js) {
        this.js = js;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getCss() {
        return css;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }

    public static class SectionEntity {
        /**
         * thumbnail : http://pic1.zhimg.com/260f887dbdd54a34ff5700ba9fd28644.jpg
         * id : 55
         * name : 2016 年度盘点
         */

        private String thumbnail;
        private int id;
        private String name;

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
