package com.kpocom.wulilc.noteperjectapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kpocom.wulilc.noteperjectapplication.R;

/**
 * Created by wulilc on 2018/1/31.
 */

public class WMainClassifyFragment extends Fragment {

    View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_wmainhome, container, false);
        return rootView;
    }
}
