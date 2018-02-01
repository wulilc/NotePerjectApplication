package com.kpocom.wulilc.noteperjectapplication.utils.WSwipebackHelper;

/**
 * Created by wulilc on 2018/2/1.
 */

import android.app.Activity;

import java.util.Stack;

/**
 * 滑动的全局管理类
 */
public class WSwipebackHelper {

    private static final Stack<WSwipebackPage> mPageStack = new Stack<>();

    private static WSwipebackPage findHelperByActivity(Activity activity){
        for (WSwipebackPage swipeBackPage : mPageStack) {
            if (swipeBackPage.mActivity == activity) return swipeBackPage;
        }
        return null;
    }

    public static WSwipebackPage getCurrentPage(Activity activity){
        WSwipebackPage page;
        if ((page = findHelperByActivity(activity)) == null){
            throw new RuntimeException("You Should call SwipeBackHelper.onCreate(activity) first");
        }
        return page;
    }

    public static void onCreate(Activity activity) {
        WSwipebackPage page;
        if ((page = findHelperByActivity(activity)) == null){
            page = mPageStack.push(new WSwipebackPage(activity));
        }
        page.onCreate();
    }

    public static void onPostCreate(Activity activity){
        WSwipebackPage page;
        if ((page = findHelperByActivity(activity)) == null){
            throw new RuntimeException("You Should call SwipeBackHelper.onCreate(activity) first");
        }
        page.onPostCreate();
    }

    public static void onDestroy(Activity activity){
        WSwipebackPage page;
        if ((page = findHelperByActivity(activity)) == null){
            throw new RuntimeException("You Should call SwipeBackHelper.onCreate(activity) first");
        }
        mPageStack.remove(page);
        page.mActivity=null;
    }

    public static void finish(Activity activity){
        WSwipebackPage page;
        if ((page = findHelperByActivity(activity)) == null){
            throw new RuntimeException("You Should call SwipeBackHelper.onCreate(activity) first");
        }
        page.scrollToFinishActivity();
    }

    static WSwipebackPage getPrePage(WSwipebackPage activity){
        int index = mPageStack.indexOf(activity);
        if (index>0)return mPageStack.get(index-1);
        else return null;
    }
}
