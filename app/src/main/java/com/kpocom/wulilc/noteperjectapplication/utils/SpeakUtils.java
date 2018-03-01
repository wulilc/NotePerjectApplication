package com.kpocom.wulilc.noteperjectapplication.utils;

import android.app.Activity;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

/**
 * Created by wulilc on 2018/2/27.
 */

public class SpeakUtils {

    private TextToSpeech textToSpeech;

    public SpeakUtils(final Activity context){
        textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    //初始化成功
                } else {
                    Toast.makeText(context, "初始化失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
        textToSpeech.setPitch(5.0f);
    }

    public void speakText(String text){
        textToSpeech.speak(text, TextToSpeech.QUEUE_ADD, null);
    }

    public void stop(){
        textToSpeech.stop();
    }

    public void shutdown(){
        textToSpeech.shutdown();
    }

}
