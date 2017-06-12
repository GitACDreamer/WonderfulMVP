package cn.net.sunet.mvp.model.entity;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/20
   Modify: 2017/1/20
 * Description: gold item bean
 */

import java.util.List;

public class GoldItemBean {

    /**
     * tagsTitleArray : ["Webpack"]
     * category : frontend
     * tags : {"__type":"Relation","className":"Tag"}
     * hotIndex : 42
     * updatedAt : 2017-01-20T06:14:19.915Z
     * viewsCount : 30
     * subscribers : {"__type":"Relation","className":"_User"}
     * collectionCount : 1
     * content : webpack中文官网
     * https://webpack-china.org/
     * objectId : 5881a8a98d6d81006cd4dc19
     * hot : false
     * original : true
     * createdAt : 2017-01-20T06:05:29.468Z
     * type : article
     * title : webpack 的新年礼物—— webpack 官方中文社区成立
     * english : true
     * rankIndex : 10.360171434883638
     * url : https://medium.com/webpack/webpack-bits-deepening-our-roots-28e30f4c6e35#.270hshd1f
     * gfw : true
     * commentsCount : 0
     * user : {"viewedEntriesCount":597,"role":"editor","totalCollectionsCount":2066,"subscribedTagsCount":19,
     * "blogCheckDate":{"__type":"Date","iso":"2015-12-11T05:08:45.429Z"},"email":"lcxfs1991@gmail.com",
     * "followersCount":18715,"applyReason":"","updatedAt":"2017-01-20T06:14:19.983Z","postedEntriesCount":21,
     * "latestCollectionUserNotification":{"__type":"Date","iso":"2017-01-19T16:42:52.697Z"},
     * "commentedEntriesCount":8,"raw":"{\"id\":1760424245,\"idstr\":\"1760424245\",\"class\":1,
     * \"screen_name\":\"李CHENGXI\",\"name\":\"李CHENGXI\",\"province\":\"44\",\"city\":\"6\",\"location\":\"广东 佛山\",
     * \"description\":\"\",\"url\":\"\",\"profile_image_url\":\"http:\\/\\/tp2.sinaimg
     * .cn\\/1760424245\\/50\\/5622567729\\/1\",\"profile_url\":\"leehkfs\",\"domain\":\"leehkfs\",\"weihao\":\"\",
     * \"gender\":\"m\",\"followers_count\":536,\"friends_count\":957,\"pagefriends_count\":0,\"statuses_count\":787,
     * \"favourites_count\":5,\"created_at\":\"Mon Jun 21 00:04:55 +0800 2010\",\"following\":false,
     * \"allow_all_act_msg\":false,\"geo_enabled\":true,\"verified\":false,\"verified_type\":220,\"remark\":\"\",
     * \"status\":{\"created_at\":\"Fri Nov 06 10:02:25 +0800 2015\",\"id\":3906121271640277,
     * \"mid\":\"3906121271640277\",\"idstr\":\"3906121271640277\",\"text\":\"转发微博\",\"source_allowclick\":0,
     * \"source_type\":1,\"source\":\"<a href=\"http:\\/\\/weibo.com\\/\" rel=\"nofollow\">微博 weibo.com<\\/a>\",
     * \"favorited\":false,\"truncated\":false,\"in_reply_to_status_id\":\"\",\"in_reply_to_user_id\":\"\",
     * \"in_reply_to_screen_name\":\"\",\"pic_urls\":[],\"geo\":null,\"reposts_count\":0,\"comments_count\":0,
     * \"attitudes_count\":0,\"mlevel\":0,\"visible\":{\"type\":0,\"list_id\":0},\"biz_feature\":0,
     * \"darwin_tags\":[],\"userType\":0},\"ptype\":0,\"allow_all_comment\":true,\"avatar_large\":\"http:\\/\\/tp2
     * .sinaimg.cn\\/1760424245\\/180\\/5622567729\\/1\",\"avatar_hd\":\"http:\\/\\/tp2.sinaimg
     * .cn\\/1760424245\\/180\\/5622567729\\/1\",\"verified_reason\":\"\",\"verified_trade\":\"\",
     * \"verified_reason_url\":\"\",\"verified_source\":\"\",\"verified_source_url\":\"\",\"follow_me\":false,
     * \"online_status\":0,\"bi_followers_count\":281,\"lang\":\"zh-cn\",\"star\":0,\"mbtype\":0,\"mbrank\":0,
     * \"block_word\":0,\"block_app\":0,\"credit_score\":80,\"user_ability\":0,\"urank\":23}","weeklyEmail":5,
     * "collectedEntriesCount":125,"objectId":"55fbd5e360b249ad605fb5ea","postedPostsCount":14,"isAuthor":true,
     * "username":"李CHENGXI","latestLoginedInAt":{"__type":"Date","iso":"2017-01-20T06:09:05.329Z"},
     * "createdAt":"2015-09-18T09:14:11.800Z","totalHotIndex":69555,"blogAddress":"https://github.com/lcxfs1991",
     * "self_description":"react, webpack","latestCheckedNotificationAt":{"__type":"Date","iso":"2016-10-27T01:51:49
     * .706Z"},"className":"_User","cover_image_phone":"","emailVerified":true,"totalCommentsCount":39,
     * "installation":{"valid":true,"timeZone":"Asia/Shanghai","updatedAt":"2016-12-17T13:27:00.142Z",
     * "channels":["vote","collection","article","voteComment","follow","url","comment","reply","commentLike",
     * "voteLike"],"objectId":"WbxAN5wFmAs08JeQ4dK20Qj1pyG7PaGf","createdAt":"2015-11-08T16:28:39.995Z",
     * "className":"_Installation","__type":"Pointer","deviceType":"android",
     * "installationId":"5751e5da-3a59-4087-b876-35a395127f72"},"blacklist":false,"weibo_id":"1760424245",
     * "mobilePhoneNumber":"18124006471","__type":"Pointer","apply":false,"followeesCount":1,"deviceType":"android",
     * "avatar_hd":"https://user-gold-cdn.xitu.io/2016/11/29/782e180261d98cfe39d38d7ed4e6cb0c","editorType":"md",
     * "jobTitle":"全棧攻城狮","company":"腾讯alloyteam","authData":{"weibo":{"access_token":"2
     * .008lYIvB03JiAL5830841e410DGoc6","avatar_url":"http://tva3.sinaimg.cn/crop.0.0.180.180
     * .180/68edf135jw1e8qgp5bmzyj2050050aa8.jpg","description":"","expiration_in":"36000","nickname":"李CHENGXI",
     * "uid":"1760424245"},"wechat":{"access_token":"IEvcDvKX0czngiyfcaxvSg_VVLvC79Nh1_XnnB5KzcPkXwu6haSvsV
     * -hnf8P30C7kNEAKXTOh6Y40KwUeHNKDQwO93aAlrSJ9bM5q501lMI","avatar_url":"http://wx.qlogo
     * .cn/mmopen/FMfD7NRqCxgGXMCvlpML0e51x0mKRKz8afonrx68yGS0Nhibfl59IhcezFKhH0MGc5kOns6XcFnuNartmIWtMXtibUqjaXkN3j
     * /0","expiration_in":"36000","nickname":"LeeHey","uid":"oDv1Ew1SOZN7apQ3sD7L7UPA68m8"},
     * "github":{"access_token":"e611fe1496c817738c3233793a3c10302e329636","expiration_in":"36000","uid":"3348398",
     * "html_url":"https://github.com/lcxfs1991","bio":"weibo: http://weibo.com/leehkfs/\r\ntwitter: https://twitter
     * .com/lcxfs1991","username":"lcxfs1991","nickname":"heyli","avatar_url":"https://avatars.githubusercontent
     * .com/u/3348398?v=3"}},"avatar_large":"https://user-gold-cdn.xitu
     * .io/2016/11/29/782e180261d98cfe39d38d7ed4e6cb0c","mobilePhoneVerified":true}
     * subscribersCount : 0
     * screenshot : {"mime_type":"image/png","updatedAt":"2017-01-20T06:05:28.192Z","key":"495925102c3f8b4ef2e7.png",
     * "name":"webpack中文.png","objectId":"5881a8a8128fe10068257531","createdAt":"2017-01-20T06:05:28.192Z",
     * "__type":"File","url":"https://dn-mhke0kuv.qbox.me/495925102c3f8b4ef2e7.png",
     * "metaData":{"owner":"55fbd5e360b249ad605fb5ea","size":14765,"mime_type":"image/png"},"bucket":"mhke0kuv"}
     * originalUrl : https://medium.com/webpack/webpack-bits-deepening-our-roots-28e30f4c6e35#.270hshd1f
     */

    private String category;
    private TagsEntity tags;
    private int hotIndex;
    private String updatedAt;
    private int viewsCount;
    private SubscribersEntity subscribers;
    private int collectionCount;
    private String content;
    private String objectId;
    private boolean hot;
    private boolean original;
    private String createdAt;
    private String type;
    private String title;
    private boolean english;
    private double rankIndex;
    private String url;
    private boolean gfw;
    private int commentsCount;
    private UserEntity user;
    private int subscribersCount;
    private ScreenshotEntity screenshot;
    private String originalUrl;
    private List<String> tagsTitleArray;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public TagsEntity getTags() {
        return tags;
    }

    public void setTags(TagsEntity tags) {
        this.tags = tags;
    }

    public int getHotIndex() {
        return hotIndex;
    }

    public void setHotIndex(int hotIndex) {
        this.hotIndex = hotIndex;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(int viewsCount) {
        this.viewsCount = viewsCount;
    }

    public SubscribersEntity getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(SubscribersEntity subscribers) {
        this.subscribers = subscribers;
    }

    public int getCollectionCount() {
        return collectionCount;
    }

    public void setCollectionCount(int collectionCount) {
        this.collectionCount = collectionCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

    public boolean isOriginal() {
        return original;
    }

    public void setOriginal(boolean original) {
        this.original = original;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isEnglish() {
        return english;
    }

    public void setEnglish(boolean english) {
        this.english = english;
    }

    public double getRankIndex() {
        return rankIndex;
    }

    public void setRankIndex(double rankIndex) {
        this.rankIndex = rankIndex;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isGfw() {
        return gfw;
    }

    public void setGfw(boolean gfw) {
        this.gfw = gfw;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public int getSubscribersCount() {
        return subscribersCount;
    }

    public void setSubscribersCount(int subscribersCount) {
        this.subscribersCount = subscribersCount;
    }

    public ScreenshotEntity getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(ScreenshotEntity screenshot) {
        this.screenshot = screenshot;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public List<String> getTagsTitleArray() {
        return tagsTitleArray;
    }

    public void setTagsTitleArray(List<String> tagsTitleArray) {
        this.tagsTitleArray = tagsTitleArray;
    }

    public static class TagsEntity {
        /**
         * __type : Relation
         * className : Tag
         */

        private String __type;
        private String className;

        public String get__type() {
            return __type;
        }

        public void set__type(String __type) {
            this.__type = __type;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }
    }

    public static class SubscribersEntity {
        /**
         * __type : Relation
         * className : _User
         */

        private String __type;
        private String className;

        public String get__type() {
            return __type;
        }

        public void set__type(String __type) {
            this.__type = __type;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }
    }

    public static class UserEntity {
        /**
         * viewedEntriesCount : 597
         * role : editor
         * totalCollectionsCount : 2066
         * subscribedTagsCount : 19
         * blogCheckDate : {"__type":"Date","iso":"2015-12-11T05:08:45.429Z"}
         * email : lcxfs1991@gmail.com
         * followersCount : 18715
         * applyReason :
         * updatedAt : 2017-01-20T06:14:19.983Z
         * postedEntriesCount : 21
         * latestCollectionUserNotification : {"__type":"Date","iso":"2017-01-19T16:42:52.697Z"}
         * commentedEntriesCount : 8
         * raw : {"id":1760424245,"idstr":"1760424245","class":1,"screen_name":"李CHENGXI","name":"李CHENGXI",
         * "province":"44","city":"6","location":"广东 佛山","description":"","url":"","profile_image_url":"http:\/\/tp2
         * .sinaimg.cn\/1760424245\/50\/5622567729\/1","profile_url":"leehkfs","domain":"leehkfs","weihao":"",
         * "gender":"m","followers_count":536,"friends_count":957,"pagefriends_count":0,"statuses_count":787,
         * "favourites_count":5,"created_at":"Mon Jun 21 00:04:55 +0800 2010","following":false,
         * "allow_all_act_msg":false,"geo_enabled":true,"verified":false,"verified_type":220,"remark":"",
         * "status":{"created_at":"Fri Nov 06 10:02:25 +0800 2015","id":3906121271640277,"mid":"3906121271640277",
         * "idstr":"3906121271640277","text":"转发微博","source_allowclick":0,"source_type":1,
         * "source":"<a href="http:\/\/weibo.com\/" rel="nofollow">微博 weibo.com<\/a>","favorited":false,
         * "truncated":false,"in_reply_to_status_id":"","in_reply_to_user_id":"","in_reply_to_screen_name":"",
         * "pic_urls":[],"geo":null,"reposts_count":0,"comments_count":0,"attitudes_count":0,"mlevel":0,
         * "visible":{"type":0,"list_id":0},"biz_feature":0,"darwin_tags":[],"userType":0},"ptype":0,
         * "allow_all_comment":true,"avatar_large":"http:\/\/tp2.sinaimg.cn\/1760424245\/180\/5622567729\/1",
         * "avatar_hd":"http:\/\/tp2.sinaimg.cn\/1760424245\/180\/5622567729\/1","verified_reason":"",
         * "verified_trade":"","verified_reason_url":"","verified_source":"","verified_source_url":"",
         * "follow_me":false,"online_status":0,"bi_followers_count":281,"lang":"zh-cn","star":0,"mbtype":0,
         * "mbrank":0,"block_word":0,"block_app":0,"credit_score":80,"user_ability":0,"urank":23}
         * weeklyEmail : 5
         * collectedEntriesCount : 125
         * objectId : 55fbd5e360b249ad605fb5ea
         * postedPostsCount : 14
         * isAuthor : true
         * username : 李CHENGXI
         * latestLoginedInAt : {"__type":"Date","iso":"2017-01-20T06:09:05.329Z"}
         * createdAt : 2015-09-18T09:14:11.800Z
         * totalHotIndex : 69555
         * blogAddress : https://github.com/lcxfs1991
         * self_description : react, webpack
         * latestCheckedNotificationAt : {"__type":"Date","iso":"2016-10-27T01:51:49.706Z"}
         * className : _User
         * cover_image_phone :
         * emailVerified : true
         * totalCommentsCount : 39
         * installation : {"valid":true,"timeZone":"Asia/Shanghai","updatedAt":"2016-12-17T13:27:00.142Z",
         * "channels":["vote","collection","article","voteComment","follow","url","comment","reply","commentLike",
         * "voteLike"],"objectId":"WbxAN5wFmAs08JeQ4dK20Qj1pyG7PaGf","createdAt":"2015-11-08T16:28:39.995Z",
         * "className":"_Installation","__type":"Pointer","deviceType":"android",
         * "installationId":"5751e5da-3a59-4087-b876-35a395127f72"}
         * blacklist : false
         * weibo_id : 1760424245
         * mobilePhoneNumber : 18124006471
         * __type : Pointer
         * apply : false
         * followeesCount : 1
         * deviceType : android
         * avatar_hd : https://user-gold-cdn.xitu.io/2016/11/29/782e180261d98cfe39d38d7ed4e6cb0c
         * editorType : md
         * jobTitle : 全棧攻城狮
         * company : 腾讯alloyteam
         * authData : {"weibo":{"access_token":"2.008lYIvB03JiAL5830841e410DGoc6","avatar_url":"http://tva3.sinaimg
         * .cn/crop.0.0.180.180.180/68edf135jw1e8qgp5bmzyj2050050aa8.jpg","description":"","expiration_in":"36000",
         * "nickname":"李CHENGXI","uid":"1760424245"},
         * "wechat":{"access_token":"IEvcDvKX0czngiyfcaxvSg_VVLvC79Nh1_XnnB5KzcPkXwu6haSvsV
         * -hnf8P30C7kNEAKXTOh6Y40KwUeHNKDQwO93aAlrSJ9bM5q501lMI","avatar_url":"http://wx.qlogo
         * .cn/mmopen/FMfD7NRqCxgGXMCvlpML0e51x0mKRKz8afonrx68yGS0Nhibfl59IhcezFKhH0MGc5kOns6XcFnuNartmIWtMXtibUqjaXkN3j/0","expiration_in":"36000","nickname":"LeeHey","uid":"oDv1Ew1SOZN7apQ3sD7L7UPA68m8"},"github":{"access_token":"e611fe1496c817738c3233793a3c10302e329636","expiration_in":"36000","uid":"3348398","html_url":"https://github.com/lcxfs1991","bio":"weibo: http://weibo.com/leehkfs/\r\ntwitter: https://twitter.com/lcxfs1991","username":"lcxfs1991","nickname":"heyli","avatar_url":"https://avatars.githubusercontent.com/u/3348398?v=3"}}
         * avatar_large : https://user-gold-cdn.xitu.io/2016/11/29/782e180261d98cfe39d38d7ed4e6cb0c
         * mobilePhoneVerified : true
         */

        private int viewedEntriesCount;
        private String role;
        private int totalCollectionsCount;
        private int subscribedTagsCount;
        private BlogCheckDateEntity blogCheckDate;
        private String email;
        private int followersCount;
        private String applyReason;
        private String updatedAt;
        private int postedEntriesCount;
        private LatestCollectionUserNotificationEntity latestCollectionUserNotification;
        private int commentedEntriesCount;
        private String raw;
        private int weeklyEmail;
        private int collectedEntriesCount;
        private String objectId;
        private int postedPostsCount;
        private boolean isAuthor;
        private String username;
        private LatestLoginedInAtEntity latestLoginedInAt;
        private String createdAt;
        private int totalHotIndex;
        private String blogAddress;
        private String self_description;
        private LatestCheckedNotificationAtEntity latestCheckedNotificationAt;
        private String className;
        private String cover_image_phone;
        private boolean emailVerified;
        private int totalCommentsCount;
        private InstallationEntity installation;
        private boolean blacklist;
        private String weibo_id;
        private String mobilePhoneNumber;
        private String __type;
        private boolean apply;
        private int followeesCount;
        private String deviceType;
        private String avatar_hd;
        private String editorType;
        private String jobTitle;
        private String company;
        private AuthDataEntity authData;
        private String avatar_large;
        private boolean mobilePhoneVerified;

        public int getViewedEntriesCount() {
            return viewedEntriesCount;
        }

        public void setViewedEntriesCount(int viewedEntriesCount) {
            this.viewedEntriesCount = viewedEntriesCount;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public int getTotalCollectionsCount() {
            return totalCollectionsCount;
        }

        public void setTotalCollectionsCount(int totalCollectionsCount) {
            this.totalCollectionsCount = totalCollectionsCount;
        }

        public int getSubscribedTagsCount() {
            return subscribedTagsCount;
        }

        public void setSubscribedTagsCount(int subscribedTagsCount) {
            this.subscribedTagsCount = subscribedTagsCount;
        }

        public BlogCheckDateEntity getBlogCheckDate() {
            return blogCheckDate;
        }

        public void setBlogCheckDate(BlogCheckDateEntity blogCheckDate) {
            this.blogCheckDate = blogCheckDate;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getFollowersCount() {
            return followersCount;
        }

        public void setFollowersCount(int followersCount) {
            this.followersCount = followersCount;
        }

        public String getApplyReason() {
            return applyReason;
        }

        public void setApplyReason(String applyReason) {
            this.applyReason = applyReason;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public int getPostedEntriesCount() {
            return postedEntriesCount;
        }

        public void setPostedEntriesCount(int postedEntriesCount) {
            this.postedEntriesCount = postedEntriesCount;
        }

        public LatestCollectionUserNotificationEntity getLatestCollectionUserNotification() {
            return latestCollectionUserNotification;
        }

        public void setLatestCollectionUserNotification(LatestCollectionUserNotificationEntity
                                                                latestCollectionUserNotification) {
            this.latestCollectionUserNotification = latestCollectionUserNotification;
        }

        public int getCommentedEntriesCount() {
            return commentedEntriesCount;
        }

        public void setCommentedEntriesCount(int commentedEntriesCount) {
            this.commentedEntriesCount = commentedEntriesCount;
        }

        public String getRaw() {
            return raw;
        }

        public void setRaw(String raw) {
            this.raw = raw;
        }

        public int getWeeklyEmail() {
            return weeklyEmail;
        }

        public void setWeeklyEmail(int weeklyEmail) {
            this.weeklyEmail = weeklyEmail;
        }

        public int getCollectedEntriesCount() {
            return collectedEntriesCount;
        }

        public void setCollectedEntriesCount(int collectedEntriesCount) {
            this.collectedEntriesCount = collectedEntriesCount;
        }

        public String getObjectId() {
            return objectId;
        }

        public void setObjectId(String objectId) {
            this.objectId = objectId;
        }

        public int getPostedPostsCount() {
            return postedPostsCount;
        }

        public void setPostedPostsCount(int postedPostsCount) {
            this.postedPostsCount = postedPostsCount;
        }

        public boolean isIsAuthor() {
            return isAuthor;
        }

        public void setIsAuthor(boolean isAuthor) {
            this.isAuthor = isAuthor;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public LatestLoginedInAtEntity getLatestLoginedInAt() {
            return latestLoginedInAt;
        }

        public void setLatestLoginedInAt(LatestLoginedInAtEntity latestLoginedInAt) {
            this.latestLoginedInAt = latestLoginedInAt;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public int getTotalHotIndex() {
            return totalHotIndex;
        }

        public void setTotalHotIndex(int totalHotIndex) {
            this.totalHotIndex = totalHotIndex;
        }

        public String getBlogAddress() {
            return blogAddress;
        }

        public void setBlogAddress(String blogAddress) {
            this.blogAddress = blogAddress;
        }

        public String getSelf_description() {
            return self_description;
        }

        public void setSelf_description(String self_description) {
            this.self_description = self_description;
        }

        public LatestCheckedNotificationAtEntity getLatestCheckedNotificationAt() {
            return latestCheckedNotificationAt;
        }

        public void setLatestCheckedNotificationAt(LatestCheckedNotificationAtEntity latestCheckedNotificationAt) {
            this.latestCheckedNotificationAt = latestCheckedNotificationAt;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getCover_image_phone() {
            return cover_image_phone;
        }

        public void setCover_image_phone(String cover_image_phone) {
            this.cover_image_phone = cover_image_phone;
        }

        public boolean isEmailVerified() {
            return emailVerified;
        }

        public void setEmailVerified(boolean emailVerified) {
            this.emailVerified = emailVerified;
        }

        public int getTotalCommentsCount() {
            return totalCommentsCount;
        }

        public void setTotalCommentsCount(int totalCommentsCount) {
            this.totalCommentsCount = totalCommentsCount;
        }

        public InstallationEntity getInstallation() {
            return installation;
        }

        public void setInstallation(InstallationEntity installation) {
            this.installation = installation;
        }

        public boolean isBlacklist() {
            return blacklist;
        }

        public void setBlacklist(boolean blacklist) {
            this.blacklist = blacklist;
        }

        public String getWeibo_id() {
            return weibo_id;
        }

        public void setWeibo_id(String weibo_id) {
            this.weibo_id = weibo_id;
        }

        public String getMobilePhoneNumber() {
            return mobilePhoneNumber;
        }

        public void setMobilePhoneNumber(String mobilePhoneNumber) {
            this.mobilePhoneNumber = mobilePhoneNumber;
        }

        public String get__type() {
            return __type;
        }

        public void set__type(String __type) {
            this.__type = __type;
        }

        public boolean isApply() {
            return apply;
        }

        public void setApply(boolean apply) {
            this.apply = apply;
        }

        public int getFolloweesCount() {
            return followeesCount;
        }

        public void setFolloweesCount(int followeesCount) {
            this.followeesCount = followeesCount;
        }

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public String getAvatar_hd() {
            return avatar_hd;
        }

        public void setAvatar_hd(String avatar_hd) {
            this.avatar_hd = avatar_hd;
        }

        public String getEditorType() {
            return editorType;
        }

        public void setEditorType(String editorType) {
            this.editorType = editorType;
        }

        public String getJobTitle() {
            return jobTitle;
        }

        public void setJobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public AuthDataEntity getAuthData() {
            return authData;
        }

        public void setAuthData(AuthDataEntity authData) {
            this.authData = authData;
        }

        public String getAvatar_large() {
            return avatar_large;
        }

        public void setAvatar_large(String avatar_large) {
            this.avatar_large = avatar_large;
        }

        public boolean isMobilePhoneVerified() {
            return mobilePhoneVerified;
        }

        public void setMobilePhoneVerified(boolean mobilePhoneVerified) {
            this.mobilePhoneVerified = mobilePhoneVerified;
        }

        public static class BlogCheckDateEntity {
            /**
             * __type : Date
             * iso : 2015-12-11T05:08:45.429Z
             */

            private String __type;
            private String iso;

            public String get__type() {
                return __type;
            }

            public void set__type(String __type) {
                this.__type = __type;
            }

            public String getIso() {
                return iso;
            }

            public void setIso(String iso) {
                this.iso = iso;
            }
        }

        public static class LatestCollectionUserNotificationEntity {
            /**
             * __type : Date
             * iso : 2017-01-19T16:42:52.697Z
             */

            private String __type;
            private String iso;

            public String get__type() {
                return __type;
            }

            public void set__type(String __type) {
                this.__type = __type;
            }

            public String getIso() {
                return iso;
            }

            public void setIso(String iso) {
                this.iso = iso;
            }
        }

        public static class LatestLoginedInAtEntity {
            /**
             * __type : Date
             * iso : 2017-01-20T06:09:05.329Z
             */

            private String __type;
            private String iso;

            public String get__type() {
                return __type;
            }

            public void set__type(String __type) {
                this.__type = __type;
            }

            public String getIso() {
                return iso;
            }

            public void setIso(String iso) {
                this.iso = iso;
            }
        }

        public static class LatestCheckedNotificationAtEntity {
            /**
             * __type : Date
             * iso : 2016-10-27T01:51:49.706Z
             */

            private String __type;
            private String iso;

            public String get__type() {
                return __type;
            }

            public void set__type(String __type) {
                this.__type = __type;
            }

            public String getIso() {
                return iso;
            }

            public void setIso(String iso) {
                this.iso = iso;
            }
        }

        public static class InstallationEntity {
            /**
             * valid : true
             * timeZone : Asia/Shanghai
             * updatedAt : 2016-12-17T13:27:00.142Z
             * channels : ["vote","collection","article","voteComment","follow","url","comment","reply",
             * "commentLike","voteLike"]
             * objectId : WbxAN5wFmAs08JeQ4dK20Qj1pyG7PaGf
             * createdAt : 2015-11-08T16:28:39.995Z
             * className : _Installation
             * __type : Pointer
             * deviceType : android
             * installationId : 5751e5da-3a59-4087-b876-35a395127f72
             */

            private boolean valid;
            private String timeZone;
            private String updatedAt;
            private String objectId;
            private String createdAt;
            private String className;
            private String __type;
            private String deviceType;
            private String installationId;
            private List<String> channels;

            public boolean isValid() {
                return valid;
            }

            public void setValid(boolean valid) {
                this.valid = valid;
            }

            public String getTimeZone() {
                return timeZone;
            }

            public void setTimeZone(String timeZone) {
                this.timeZone = timeZone;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

            public String getObjectId() {
                return objectId;
            }

            public void setObjectId(String objectId) {
                this.objectId = objectId;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getClassName() {
                return className;
            }

            public void setClassName(String className) {
                this.className = className;
            }

            public String get__type() {
                return __type;
            }

            public void set__type(String __type) {
                this.__type = __type;
            }

            public String getDeviceType() {
                return deviceType;
            }

            public void setDeviceType(String deviceType) {
                this.deviceType = deviceType;
            }

            public String getInstallationId() {
                return installationId;
            }

            public void setInstallationId(String installationId) {
                this.installationId = installationId;
            }

            public List<String> getChannels() {
                return channels;
            }

            public void setChannels(List<String> channels) {
                this.channels = channels;
            }
        }

        public static class AuthDataEntity {
            /**
             * weibo : {"access_token":"2.008lYIvB03JiAL5830841e410DGoc6","avatar_url":"http://tva3.sinaimg.cn/crop.0
             * .0.180.180.180/68edf135jw1e8qgp5bmzyj2050050aa8.jpg","description":"","expiration_in":"36000",
             * "nickname":"李CHENGXI","uid":"1760424245"}
             * wechat : {"access_token":"IEvcDvKX0czngiyfcaxvSg_VVLvC79Nh1_XnnB5KzcPkXwu6haSvsV
             * -hnf8P30C7kNEAKXTOh6Y40KwUeHNKDQwO93aAlrSJ9bM5q501lMI","avatar_url":"http://wx.qlogo
             * .cn/mmopen/FMfD7NRqCxgGXMCvlpML0e51x0mKRKz8afonrx68yGS0Nhibfl59IhcezFKhH0MGc5kOns6XcFnuNartmIWtMXtibUqjaXkN3j/0","expiration_in":"36000","nickname":"LeeHey","uid":"oDv1Ew1SOZN7apQ3sD7L7UPA68m8"}
             * github : {"access_token":"e611fe1496c817738c3233793a3c10302e329636","expiration_in":"36000",
             * "uid":"3348398","html_url":"https://github.com/lcxfs1991","bio":"weibo: http://weibo
             * .com/leehkfs/\r\ntwitter: https://twitter.com/lcxfs1991","username":"lcxfs1991","nickname":"heyli",
             * "avatar_url":"https://avatars.githubusercontent.com/u/3348398?v=3"}
             */

            private WeiboEntity weibo;
            private WechatEntity wechat;
            private GithubEntity github;

            public WeiboEntity getWeibo() {
                return weibo;
            }

            public void setWeibo(WeiboEntity weibo) {
                this.weibo = weibo;
            }

            public WechatEntity getWechat() {
                return wechat;
            }

            public void setWechat(WechatEntity wechat) {
                this.wechat = wechat;
            }

            public GithubEntity getGithub() {
                return github;
            }

            public void setGithub(GithubEntity github) {
                this.github = github;
            }

            public static class WeiboEntity {
                /**
                 * access_token : 2.008lYIvB03JiAL5830841e410DGoc6
                 * avatar_url : http://tva3.sinaimg.cn/crop.0.0.180.180.180/68edf135jw1e8qgp5bmzyj2050050aa8.jpg
                 * description :
                 * expiration_in : 36000
                 * nickname : 李CHENGXI
                 * uid : 1760424245
                 */

                private String access_token;
                private String avatar_url;
                private String description;
                private String expiration_in;
                private String nickname;
                private String uid;

                public String getAccess_token() {
                    return access_token;
                }

                public void setAccess_token(String access_token) {
                    this.access_token = access_token;
                }

                public String getAvatar_url() {
                    return avatar_url;
                }

                public void setAvatar_url(String avatar_url) {
                    this.avatar_url = avatar_url;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getExpiration_in() {
                    return expiration_in;
                }

                public void setExpiration_in(String expiration_in) {
                    this.expiration_in = expiration_in;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public String getUid() {
                    return uid;
                }

                public void setUid(String uid) {
                    this.uid = uid;
                }
            }

            public static class WechatEntity {
                /**
                 * access_token : IEvcDvKX0czngiyfcaxvSg_VVLvC79Nh1_XnnB5KzcPkXwu6haSvsV
                 * -hnf8P30C7kNEAKXTOh6Y40KwUeHNKDQwO93aAlrSJ9bM5q501lMI
                 * avatar_url : http://wx.qlogo
                 * .cn/mmopen/FMfD7NRqCxgGXMCvlpML0e51x0mKRKz8afonrx68yGS0Nhibfl59IhcezFKhH0MGc5kOns6XcFnuNartmIWtMXtibUqjaXkN3j/0
                 * expiration_in : 36000
                 * nickname : LeeHey
                 * uid : oDv1Ew1SOZN7apQ3sD7L7UPA68m8
                 */

                private String access_token;
                private String avatar_url;
                private String expiration_in;
                private String nickname;
                private String uid;

                public String getAccess_token() {
                    return access_token;
                }

                public void setAccess_token(String access_token) {
                    this.access_token = access_token;
                }

                public String getAvatar_url() {
                    return avatar_url;
                }

                public void setAvatar_url(String avatar_url) {
                    this.avatar_url = avatar_url;
                }

                public String getExpiration_in() {
                    return expiration_in;
                }

                public void setExpiration_in(String expiration_in) {
                    this.expiration_in = expiration_in;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public String getUid() {
                    return uid;
                }

                public void setUid(String uid) {
                    this.uid = uid;
                }
            }

            public static class GithubEntity {
                /**
                 * access_token : e611fe1496c817738c3233793a3c10302e329636
                 * expiration_in : 36000
                 * uid : 3348398
                 * html_url : https://github.com/lcxfs1991
                 * bio : weibo: http://weibo.com/leehkfs/
                 * twitter: https://twitter.com/lcxfs1991
                 * username : lcxfs1991
                 * nickname : heyli
                 * avatar_url : https://avatars.githubusercontent.com/u/3348398?v=3
                 */

                private String access_token;
                private String expiration_in;
                private String uid;
                private String html_url;
                private String bio;
                private String username;
                private String nickname;
                private String avatar_url;

                public String getAccess_token() {
                    return access_token;
                }

                public void setAccess_token(String access_token) {
                    this.access_token = access_token;
                }

                public String getExpiration_in() {
                    return expiration_in;
                }

                public void setExpiration_in(String expiration_in) {
                    this.expiration_in = expiration_in;
                }

                public String getUid() {
                    return uid;
                }

                public void setUid(String uid) {
                    this.uid = uid;
                }

                public String getHtml_url() {
                    return html_url;
                }

                public void setHtml_url(String html_url) {
                    this.html_url = html_url;
                }

                public String getBio() {
                    return bio;
                }

                public void setBio(String bio) {
                    this.bio = bio;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public String getAvatar_url() {
                    return avatar_url;
                }

                public void setAvatar_url(String avatar_url) {
                    this.avatar_url = avatar_url;
                }
            }
        }
    }

    public static class ScreenshotEntity {
        /**
         * mime_type : image/png
         * updatedAt : 2017-01-20T06:05:28.192Z
         * key : 495925102c3f8b4ef2e7.png
         * name : webpack中文.png
         * objectId : 5881a8a8128fe10068257531
         * createdAt : 2017-01-20T06:05:28.192Z
         * __type : File
         * url : https://dn-mhke0kuv.qbox.me/495925102c3f8b4ef2e7.png
         * metaData : {"owner":"55fbd5e360b249ad605fb5ea","size":14765,"mime_type":"image/png"}
         * bucket : mhke0kuv
         */

        private String mime_type;
        private String updatedAt;
        private String key;
        private String name;
        private String objectId;
        private String createdAt;
        private String __type;
        private String url;
        private MetaDataEntity metaData;
        private String bucket;

        public String getMime_type() {
            return mime_type;
        }

        public void setMime_type(String mime_type) {
            this.mime_type = mime_type;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getObjectId() {
            return objectId;
        }

        public void setObjectId(String objectId) {
            this.objectId = objectId;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String get__type() {
            return __type;
        }

        public void set__type(String __type) {
            this.__type = __type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public MetaDataEntity getMetaData() {
            return metaData;
        }

        public void setMetaData(MetaDataEntity metaData) {
            this.metaData = metaData;
        }

        public String getBucket() {
            return bucket;
        }

        public void setBucket(String bucket) {
            this.bucket = bucket;
        }

        public static class MetaDataEntity {
            /**
             * owner : 55fbd5e360b249ad605fb5ea
             * size : 14765
             * mime_type : image/png
             */

            private String owner;
            private int size;
            private String mime_type;

            public String getOwner() {
                return owner;
            }

            public void setOwner(String owner) {
                this.owner = owner;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public String getMime_type() {
                return mime_type;
            }

            public void setMime_type(String mime_type) {
                this.mime_type = mime_type;
            }
        }
    }
}
