package com.kostyabakay.guidedtimepickeraction;

import android.os.Bundle;
import android.support.v17.leanback.app.GuidedStepSupportFragment;

public class GuidedStepFragment extends GuidedStepSupportFragment {

    public static GuidedStepFragment newInstance() {
        Bundle args = new Bundle();
        GuidedStepFragment fragment = new GuidedStepFragment();
        fragment.setArguments(args);
        return fragment;
    }
}