package com.kpocom.wulilc.noteperjectapplication.widget.sipsetting;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;

import com.kpocom.wulilc.noteperjectapplication.R;


/**
 * Created by wulilc on 2018/3/1.
 */

public class SipSettings extends PreferenceActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
