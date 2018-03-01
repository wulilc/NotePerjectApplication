package com.kpocom.wulilc.noteperjectapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.heima.tabview.library.TabView;
import com.heima.tabview.library.TabViewChild;
import com.kpocom.wulilc.noteperjectapplication.R;
import com.kpocom.wulilc.noteperjectapplication.fragment.WMainHomeFragment;
import com.kpocom.wulilc.noteperjectapplication.nets.WPostRequstWeatherInterface;

import java.util.ArrayList;
import java.util.List;

public class WApplicationMainActivity extends AppCompatActivity {

    private TabView tabView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wapplication_main);

        tabView= (TabView) findViewById(R.id.tabView);

        WPostRequstWeatherInterface wPostRequstWeatherInterface = new WPostRequstWeatherInterface();
        wPostRequstWeatherInterface.wpostRequstWeather();

        initView();

    }

    public void initView(){
        List<TabViewChild> tabViewChildList=new ArrayList<>();
        TabViewChild tabViewChild01=new TabViewChild(R.drawable.tab01_sel,R.drawable.tab01_unsel,"首页", new WMainHomeFragment());
        TabViewChild tabViewChild02=new TabViewChild(R.drawable.tab02_sel,R.drawable.tab02_unsel,"分类",  new WMainHomeFragment());
        TabViewChild tabViewChild03=new TabViewChild(R.drawable.tab03_sel,R.drawable.tab03_unsel,"资讯",  new WMainHomeFragment());
        TabViewChild tabViewChild04=new TabViewChild(R.drawable.tab04_sel,R.drawable.tab04_unsel,"购物车",new WMainHomeFragment());
        TabViewChild tabViewChild05=new TabViewChild(R.drawable.tab05_sel,R.drawable.tab05_unsel,"我的",  new WMainHomeFragment());
        tabViewChildList.add(tabViewChild01);
        tabViewChildList.add(tabViewChild02);
        tabViewChildList.add(tabViewChild03);
        tabViewChildList.add(tabViewChild04);
        tabViewChildList.add(tabViewChild05);

        tabView.setTabViewChild(tabViewChildList,getSupportFragmentManager());
        tabView.setOnTabChildClickListener(new TabView.OnTabChildClickListener() {
            @Override
            public void onTabChildClick(int  position, ImageView currentImageIcon, TextView currentTextView) {
                Toast.makeText(getApplicationContext(),"position:"+position,Toast.LENGTH_SHORT).show();
            }
        });
    }


}
