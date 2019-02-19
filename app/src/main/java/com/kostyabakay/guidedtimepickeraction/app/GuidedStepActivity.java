package com.kostyabakay.guidedtimepickeraction.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v17.leanback.app.GuidedStepSupportFragment;
import android.support.v4.app.FragmentActivity;

public class GuidedStepActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GuidedStepSupportFragment.addAsRoot(this, GuidedStepFragment.newInstance(), android.R.id.content);
    }
}