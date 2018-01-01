package com.example.yuquan.util;

import com.example.yuquan.Clubact;
import com.example.yuquan.Home;
import com.example.yuquan.Inform;
import com.example.yuquan.Tips;

/**
 * Created by terry on 2017/11/28.
 * 底部按钮工具类
 */

public class Constant {
    public static final class ConValue {

        /**
         * Tab选项卡的图标
         */


        /**
         * Tab选项卡的文字
         */
        public static String mTextviewArray[] = {"主页", "活动", "小知识", "个人信息"};
        /**
         * 每一个Tab界面
         */
        public static Class mTabClassArray[] = {
                Home.class,
                Clubact.class,
                Tips.class,
                Inform.class};
    }
}
