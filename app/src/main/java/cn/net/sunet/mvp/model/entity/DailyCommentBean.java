package cn.net.sunet.mvp.model.entity;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/26
   Modify: 2016/12/26
 * Description: 日报的长评论
 */

import java.util.List;

public class DailyCommentBean {

    private List<CommentsEntity> comments;

    public List<CommentsEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentsEntity> comments) {
        this.comments = comments;
    }

    public static class CommentsEntity {
        /**
         * author : 何大赫赫
         * content : 一般人谁用得着去千百次啊
         * avatar : http://pic3.zhimg.com/e9a0bb112_im.jpg
         * time : 1472992836
         * id : 26444796
         * likes : 0
         * reply_to : {"content":"吃鲱鱼罐头到底是怎样一种体验？\n\n 铁衣，心累神教\n\n这是我朋友给我讲的故事。\n\n她在英国时跟一群人在同学家院子里开
         * party，中途食物告急，就让一位（忘了是哪国的）同学去厨房帮她拿 tuna
         * 罐头来。\n\n异国同学不知道是没听懂还是没看懂，从厨房里翻出了几个看起来有点高级的罐头，由于她不确定拿没拿对，就直接打开了。\n\n我朋友说，这玩意儿罐头上写明了开启时要进行防护措施，要在上风口开。\n\n
         * 懵懂无知的同学打开罐头的一瞬间就手脚失灵，把内容物全洒在了自己身上。\n\n然后她惨叫着狂奔出房间冲进人群然后毅然跳进了花园里的水池。\n\n被她撞到的人也沾上了罐头里的东西，也惨叫着跟着跳进水池。\n\n
         * 以上的一切都发生在半分钟内。\n\n\u2026\u2026我朋友说，在恶臭、惨叫、骚乱爆发的那一刻，她第一反应是以为丧尸潮爆发了。","status":0,"id":24734564,
         * "author":"autobahn"}
         */

        private String author;
        private String content;
        private String avatar;
        private long time;
        private int id;
        private int likes;
        private ReplyToEntity reply_to;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLikes() {
            return likes;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        public ReplyToEntity getReply_to() {
            return reply_to;
        }

        public void setReply_to(ReplyToEntity reply_to) {
            this.reply_to = reply_to;
        }

        public static class ReplyToEntity {
            /**
             * content : 吃鲱鱼罐头到底是怎样一种体验？

             铁衣，心累神教

             这是我朋友给我讲的故事。

             她在英国时跟一群人在同学家院子里开 party，中途食物告急，就让一位（忘了是哪国的）同学去厨房帮她拿 tuna 罐头来。

             异国同学不知道是没听懂还是没看懂，从厨房里翻出了几个看起来有点高级的罐头，由于她不确定拿没拿对，就直接打开了。

             我朋友说，这玩意儿罐头上写明了开启时要进行防护措施，要在上风口开。

             懵懂无知的同学打开罐头的一瞬间就手脚失灵，把内容物全洒在了自己身上。

             然后她惨叫着狂奔出房间冲进人群然后毅然跳进了花园里的水池。

             被她撞到的人也沾上了罐头里的东西，也惨叫着跟着跳进水池。

             以上的一切都发生在半分钟内。

             ……我朋友说，在恶臭、惨叫、骚乱爆发的那一刻，她第一反应是以为丧尸潮爆发了。
             * status : 0
             * id : 24734564
             * author : autobahn
             */

            private String content;
            private int status;
            private int id;
            private String author;

            private int expandState = 0;

            public int getExpandState() {
                return expandState;
            }

            public void setExpandState(int expandState) {
                this.expandState = expandState;
            }
            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }
        }
    }
}
