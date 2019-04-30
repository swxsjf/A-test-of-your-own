package com.example.myapplication.main;

import java.util.List;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/30
 * Time: 13:59
 * Describe: ${as}
 */
public class MainBean {

    /**
     * code : 200
     * message : 成功!
     * result : [{"title":"帝京篇十首 一","content":"秦川雄帝宅，函谷壮皇居。|绮殿千寻起，离宫百雉余。|连甍遥接汉，飞观迥凌虚。|云日隐层阙，风烟出绮疏。","authors":"太宗皇帝"},{"title":"帝京篇十首 二","content":"岩廊罢机务，崇文聊驻辇。|玉匣启龙图，金绳披凤篆。|韦编断仍续，缥帙舒还卷。|对此乃淹留，欹案观坟典。","authors":"太宗皇帝"},{"title":"帝京篇十首 三","content":"移步出词林，停舆欣武宴。|雕弓写明月，骏马疑流电。|惊雁落虚弦，啼猿悲急箭。|阅赏诚多美，于兹乃忘倦。","authors":"太宗皇帝"},{"title":"帝京篇十首 四","content":"鸣笳临乐馆，眺听欢芳节。|急管韵朱弦，清歌凝白雪。|彩凤肃来仪，玄鹤纷成列。|去兹郑卫声，雅音方可悦。","authors":"太宗皇帝"},{"title":"帝京篇十首 五","content":"芳辰追逸趣，禁苑信多奇。|桥形通汉上，峰势接云危。|烟霞交隐映，花鸟自参差。|何如肆辙迹？万里赏瑶池。","authors":"太宗皇帝"}]
     */

    private int code;
    private String message;
    private List<ResultBean> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * title : 帝京篇十首 一
         * content : 秦川雄帝宅，函谷壮皇居。|绮殿千寻起，离宫百雉余。|连甍遥接汉，飞观迥凌虚。|云日隐层阙，风烟出绮疏。
         * authors : 太宗皇帝
         */

        private String title;
        private String content;
        private String authors;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAuthors() {
            return authors;
        }

        public void setAuthors(String authors) {
            this.authors = authors;
        }
    }
}
