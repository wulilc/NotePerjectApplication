package com.kpocom.wulilc.noteperjectapplication.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.DecelerateInterpolator;
import com.kpocom.wulilc.noteperjectapplication.R;
import com.kpocom.wulilc.noteperjectapplication.databinding.ActivityWwelcomBinding;

/**
 * Created by wulilc on 2018/1/30.
 */

public class WStartApplicationActivity extends BaseActivity<ActivityWwelcomBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_wwelcom;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator animator = ObjectAnimator.ofFloat(getBinding().activityWelcome, "alpha", 1f, 0f);
                animator.setDuration(1500);
                animator.setInterpolator(new DecelerateInterpolator());
                animator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        jump();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }
                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                animator.start();
            }
        }, 3000);
    }

    private void jump() {
        Intent intent = new Intent(this, WApplicationMainActivity.class);
        startActivity(intent);
        this.finish();
    }

}
