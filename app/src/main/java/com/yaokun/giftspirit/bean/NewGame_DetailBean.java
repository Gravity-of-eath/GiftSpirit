package com.yaokun.giftspirit.bean;

import java.util.List;

/**
 * Created by 姚 坤 on 2016/10/7.
 */
public class NewGame_DetailBean {
    /**
     * id : 879
     * fid : 20160729
     * appid : 1474973471
     * appname : 维特之旅
     * typename : 休闲益智
     * appsize : 未知
     * adimg : /allimgs/img_iapp/201609/_1474973731395.jpeg
     * appkfs : Ju Peng
     * iconurl : /allimgs/img_iapp/201609/_1474973393220.png
     * addtime : 2016-09-27
     * descs : 电影般的动画游戏
     * critique : 《维特之旅》就是这样一款神奇的游戏，准确的说是一款神奇的动画游戏。故事发生在一个小镇子里，封闭愚昧的市民们受“火山之心”蛊惑，被骗进猩猩市长的工厂做挖矿苦工。而青蛙维特所居住的小镇被水围困，能源危机迫在眉睫。小镇唯一的出口也被怪物占据，只有支付金币召唤乌龟才能使村民离开。维特与家人发生争执，为了心中的爱和理想，最终踏上了追寻火山之心的旅途。
     * iszq : 0
     * typeid : 0
     * istop : 1
     */

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private int id;
        private int fid;
        private String appid;
        private String appname;
        private String typename;
        private String appsize;
        private String adimg;
        private String appkfs;
        private String iconurl;
        private String addtime;
        private String descs;
        private String critique;
        private int iszq;
        private int typeid;
        private int istop;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getFid() {
            return fid;
        }

        public void setFid(int fid) {
            this.fid = fid;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getAppname() {
            return appname;
        }

        public void setAppname(String appname) {
            this.appname = appname;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public String getAppsize() {
            return appsize;
        }

        public void setAppsize(String appsize) {
            this.appsize = appsize;
        }

        public String getAdimg() {
            return adimg;
        }

        public void setAdimg(String adimg) {
            this.adimg = adimg;
        }

        public String getAppkfs() {
            return appkfs;
        }

        public void setAppkfs(String appkfs) {
            this.appkfs = appkfs;
        }

        public String getIconurl() {
            return iconurl;
        }

        public void setIconurl(String iconurl) {
            this.iconurl = iconurl;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getDescs() {
            return descs;
        }

        public void setDescs(String descs) {
            this.descs = descs;
        }

        public String getCritique() {
            return critique;
        }

        public void setCritique(String critique) {
            this.critique = critique;
        }

        public int getIszq() {
            return iszq;
        }

        public void setIszq(int iszq) {
            this.iszq = iszq;
        }

        public int getTypeid() {
            return typeid;
        }

        public void setTypeid(int typeid) {
            this.typeid = typeid;
        }

        public int getIstop() {
            return istop;
        }

        public void setIstop(int istop) {
            this.istop = istop;
        }
    }
}
