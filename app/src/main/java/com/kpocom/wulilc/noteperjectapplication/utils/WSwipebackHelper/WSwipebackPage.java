package com.kpocom.wulilc.noteperjectapplication.utils.WSwipebackHelper;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;

/**
 * Created by wulilc on 2018/2/1.
 * 每个滑动页面的管理
 */

public class WSwipebackPage {

    //仅为判断是否需要将mSwipeBackLayout注入进去
    private boolean mEnable = true;
    private boolean mRelativeEnable = false;

    Activity mActivity;
    WSwipeBackLayout mSwipeBackLayout;
    WRelateSlider slider;
    WSwipebackPage(Activity activity){
        this.mActivity = activity;
    }

    //页面的回调用于配置滑动效果
    void onCreate(){
        mActivity.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mActivity.getWindow().getDecorView().setBackgroundColor(Color.TRANSPARENT);
        mSwipeBackLayout = new WSwipeBackLayout(mActivity);
        mSwipeBackLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        slider = new WRelateSlider(this);
    }

    void onPostCreate(){
        handleLayout();
    }


    @TargetApi(11)
    public WSwipebackPage setSwipeRelateEnable(boolean enable){
        mRelativeEnable = enable;
        slider.setEnable(enable);
        return this;
    }

    public WSwipebackPage setSwipeRelateOffset(int offset){
        slider.setOffset(offset);
        return this;
    }

    //是否可滑动关闭
    public WSwipebackPage setSwipeBackEnable(boolean enable) {
        mEnable = enable;
        mSwipeBackLayout.setEnableGesture(enable);
        handleLayout();
        return this;
    }

    private void handleLayout(){
        if (mEnable||mRelativeEnable){
            mSwipeBackLayout.attachToActivity(mActivity);
        }else {
            mSwipeBackLayout.removeFromActivity(mActivity);
        }
    }

    //可滑动的范围。百分比。200表示为左边200px的屏幕
    public WSwipebackPage setSwipeEdge(int swipeEdge){
        mSwipeBackLayout.setEdgeSize(swipeEdge);
        return this;
    }

    //可滑动的范围。百分比。0.2表示为左边20%的屏幕
    public WSwipebackPage setSwipeEdgePercent(float swipeEdgePercent){
        mSwipeBackLayout.setEdgeSizePercent(swipeEdgePercent);
        return this;
    }

    //对横向滑动手势的敏感程度。0为迟钝 1为敏感
    public WSwipebackPage setSwipeSensitivity(float sensitivity){
        mSwipeBackLayout.setSensitivity(mActivity, sensitivity);
        return this;
    }

    //底层阴影颜色
    public WSwipebackPage setScrimColor(int color){
        mSwipeBackLayout.setScrimColor(color);
        return this;
    }

    //触发关闭Activity百分比
    public WSwipebackPage setClosePercent(float percent){
        mSwipeBackLayout.setScrollThreshold(percent);
        return this;
    }

    public WSwipebackPage setDisallowInterceptTouchEvent(boolean disallowIntercept){
        mSwipeBackLayout.setDisallowInterceptTouchEvent(disallowIntercept);
        return this;
    }

    public WSwipebackPage addListener(WSwipebackListener listener){
        mSwipeBackLayout.addSwipeListener(listener);
        return this;
    }

    public WSwipebackPage removeListener(WSwipebackListener listener){
        mSwipeBackLayout.removeSwipeListener(listener);
        return this;
    }

    public WSwipeBackLayout getSwipeBackLayout() {
        return mSwipeBackLayout;
    }

    public void scrollToFinishActivity() {
        mSwipeBackLayout.scrollToFinishActivity();
    }
}
