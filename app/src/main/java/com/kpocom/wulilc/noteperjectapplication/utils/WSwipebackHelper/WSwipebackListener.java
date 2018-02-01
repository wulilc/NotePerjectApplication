package com.kpocom.wulilc.noteperjectapplication.utils.WSwipebackHelper;

/**
 * Created by wulilc on 2018/2/1.
 */

public interface WSwipebackListener {
    void onScroll(float percent, int px);
    void onEdgeTouch();
    /**
     * Invoke when scroll percent over the threshold for the first time
     */
    void onScrollToClose();
}
