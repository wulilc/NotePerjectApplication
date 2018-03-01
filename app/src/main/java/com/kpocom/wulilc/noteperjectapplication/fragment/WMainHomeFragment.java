package com.kpocom.wulilc.noteperjectapplication.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.materialdialogs.MaterialDialog;
import com.kpocom.wulilc.noteperjectapplication.R;

import me.leefeng.citypicker.CityPicker;
import me.leefeng.citypicker.CityPickerListener;

/**
 * Created by wulilc on 2018/1/31.
 */

public class WMainHomeFragment extends Fragment implements CityPickerListener, View.OnClickListener {

    private CityPicker cityPicker;
    private TextView tv_city;
    private Button btn_showdiaglog;

    private TextToSpeech textToSpeech;

    View rootView;

    Activity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_wmainhome, container, false);

        initView();

        initData();

        return rootView;
    }

    private void initData() {

        if (activity == null) {
            activity = getActivity();
        }

        cityPicker = new CityPicker(activity, this);

        textToSpeech = new TextToSpeech(activity, new TextToSpeech.OnInitListener() {
            @Override


            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    //初始化成功
                } else {
                    Toast.makeText(activity, "初始化失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

        textToSpeech.setPitch(5.0f);

        new Thread(){
            @Override
            public void run() {
                while (true){
                    speak();
                    try {
                        sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    private void initView() {

        tv_city = rootView.findViewById(R.id.tv_city);
        btn_showdiaglog = rootView.findViewById(R.id.btn_showdiaglog);
        btn_showdiaglog.setOnClickListener(this);
        tv_city.setOnClickListener(this);
    }

    @Override
    public void getCity(final String name) {
        tv_city.setText(name);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_city:
                cityPicker.show();
                break;
            case R.id.btn_showdiaglog:
                showDialog();
            default:
                break;
        }
    }

    public void showDialog() {
        new MaterialDialog.Builder(activity)
                .title("这是标题")
                .content("这是描述的内容")
                .positiveText("确定")
                .negativeText("取消")
                .show();
    }

//    有人问我什么是暗恋，我下意识的答道，暗恋就好比你喜欢一个玩具，很喜欢想买，可是你没钱，然后就开始挣钱，挣到钱
//    之后再去买的时候发现涨价了，你又不得不去继续挣钱，等钱够了再去买的时候发现玩具已经被别人买走了。希望不要在
//    垃圾堆看到，不然我还是会忍不住把它捡回家。


    /**
     * 忘了是S几了，ad我玩VN 辅助是努努，我们对线期不是很强势，但是每次努努出门都是买5瓶药水，然后线上挡在我的前面替我吸收伤害
     * 有次努努没血了走到防御塔下不动，我以为他在打字喷我玩的菜，没过多久，屏幕就出现一行字，VN 我可以用Q吃你一个小兵回点血吗？我没药水了。
     *
     * 王诗  丁中原
     *
     *
     * 1 2 4 8 16 32 64 128 256 512 1024 2048 4096 8192 16384 32768 65536 131072 262144 524288 1048576 2097152 4194304 8388608 16777216 33554432
     *
     * 67108864 
     *
     */
    public void speak() {
        textToSpeech.speak("滴滴滴", TextToSpeech.QUEUE_ADD, null);
    }

    @Override
    public void onDestroyView() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}
