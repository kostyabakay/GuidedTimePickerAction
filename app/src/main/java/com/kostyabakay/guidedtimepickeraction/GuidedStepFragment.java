package com.kostyabakay.guidedtimepickeraction;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v17.leanback.app.GuidedStepSupportFragment;
import android.support.v17.leanback.widget.GuidedAction;
import android.support.v17.leanback.widget.GuidedDatePickerAction;

import java.util.Date;
import java.util.List;

public class GuidedStepFragment extends GuidedStepSupportFragment {

    protected static final int ACTION_DATE_PICKER = 10001;

    public static GuidedStepFragment newInstance() {
        Bundle args = new Bundle();
        GuidedStepFragment fragment = new GuidedStepFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreateActions(@NonNull List<GuidedAction> actions, Bundle savedInstanceState) {
        super.onCreateActions(actions, savedInstanceState);
        addDateAction(getContext(), actions, new Date().getTime());
    }

    protected void addDateAction(@NonNull Context context,
                                 @NonNull List<GuidedAction> actions,
                                 long currentDate) {
        actions.add(new GuidedDatePickerAction.Builder(context)
                .id(ACTION_DATE_PICKER)
                .date(currentDate)
                .build());
    }
}