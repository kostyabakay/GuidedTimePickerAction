package com.kostyabakay.guidedtimepickeraction.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v17.leanback.app.GuidedStepSupportFragment;
import android.support.v17.leanback.widget.GuidedAction;
import android.support.v17.leanback.widget.GuidedActionsStylist;
import android.support.v17.leanback.widget.GuidedDatePickerAction;

import com.kostyabakay.guidedtimepickeraction.GuidedActionsStylistExtended;
import com.kostyabakay.guidedtimepickeraction.GuidedTimePickerAction;

import java.util.Date;
import java.util.List;

public class GuidedStepFragment extends GuidedStepSupportFragment {

    protected static final int ACTION_DATE_PICKER = 10001;
    protected static final int ACTION_TIME_PICKER = 10002;

    public static GuidedStepFragment newInstance() {
        Bundle args = new Bundle();
        GuidedStepFragment fragment = new GuidedStepFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public GuidedActionsStylist onCreateActionsStylist() {
        return new GuidedActionsStylistExtended();
    }

    @Override
    public void onCreateActions(@NonNull List<GuidedAction> actions, Bundle savedInstanceState) {
        super.onCreateActions(actions, savedInstanceState);
        Context context = getContext();
        if (context != null) {
            addDateAction(context, actions, new Date().getTime());
            addTimeAction(context, actions, new Date().getTime());
        }
    }

    protected void addDateAction(@NonNull Context context,
                                 @NonNull List<GuidedAction> actions,
                                 long currentDate) {
        actions.add(new GuidedDatePickerAction.Builder(context)
                .id(ACTION_DATE_PICKER)
                .date(currentDate)
                .build());
    }

    protected void addTimeAction(@NonNull Context context,
                                 @NonNull List<GuidedAction> actions,
                                 long currentDate) {
        actions.add(new GuidedTimePickerAction.Builder(context)
                .id(ACTION_TIME_PICKER)
                .date(currentDate)
                .build());
    }
}