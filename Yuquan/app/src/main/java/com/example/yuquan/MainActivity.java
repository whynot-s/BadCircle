package com.example.yuquan;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.John.service.user;
import com.example.yuquan.util.Constant;


/**
 * 主Activity
 * 通过点击RadioGroup下的RadioButton来切换不同界面
 * Created by D&LL on 2016/7/20.
 */
@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {
    static user User=new user();
    //定义TabHost对象
    private TabHost tabHost;

    //定义RadioGroup对象
    private RadioGroup radioGroup;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_layout);

        initView();

        initData();
    }

    /**
     * 初始化组件
     */
    private void initView() {
        //实例化TabHost，得到TabHost对象
        tabHost = getTabHost();

        //得到Activity的个数
        int count = Constant.ConValue.mTabClassArray.length;

        for (int i = 0; i < count; i++) {
            //为每一个Tab按钮设置图标、文字和内容
            TabSpec tabSpec = tabHost.newTabSpec(Constant.ConValue.mTextviewArray[i])
                    .setIndicator(Constant.ConValue.mTextviewArray[i]).setContent(getTabItemIntent(i));
            //将Tab按钮添加进Tab选项卡中
            tabHost.addTab(tabSpec);
        }

        //实例化RadioGroup
        radioGroup = (RadioGroup) findViewById(R.id.main_radiogroup);
    }

    /**
     * 初始化组件
     */
    private void initData() {
        // 给radioGroup设置监听事件
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.RadioButton0:
                        tabHost.setCurrentTabByTag(Constant.ConValue.mTextviewArray[0]);
                        break;
                    case R.id.RadioButton1:
                        tabHost.setCurrentTabByTag(Constant.ConValue.mTextviewArray[1]);
                        break;
                    case R.id.RadioButton2:
                        tabHost.setCurrentTabByTag(Constant.ConValue.mTextviewArray[2]);
                        break;
                    case R.id.RadioButton3:
                        tabHost.setCurrentTabByTag(Constant.ConValue.mTextviewArray[3]);
                        break;
                }
            }
        });
        ((RadioButton) radioGroup.getChildAt(0)).toggle();
    }

    /**
     * 给Tab选项卡设置内容（每个内容都是一个Activity）
     */
    private Intent getTabItemIntent(int index) {
        Intent intent = new Intent(this, Constant.ConValue.mTabClassArray[index]);
        return intent;
    }
}
