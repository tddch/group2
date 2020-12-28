package com.example.liujian.bean;

import java.util.List;

public class homeGoodsBean {
    public List<Data> datas;
    public static class  Data{
        public int icon;
        public String title;
        public String content;
        public List<PicList> images;
        public static class  PicList{
            public String pic;
            public Integer src;
        }
    }
}