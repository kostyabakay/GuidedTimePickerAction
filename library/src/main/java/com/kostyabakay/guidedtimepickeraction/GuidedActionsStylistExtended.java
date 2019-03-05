package com.kostyabakay.guidedtimepickeraction;

import android.support.annotation.NonNull;
import android.support.v17.leanback.widget.GuidedAction;
import android.support.v17.leanback.widget.GuidedActionsStylist;
import android.support.v17.leanback.widget.picker.TimePicker;

import java.util.Calendar;

public class GuidedActionsStylistExtended extends GuidedActionsStylist {

    private static final int VIEW_TYPE_TIME_PICKER = 2;

    //region GuidedActionsStylist
    @Override
    public int getItemViewType(GuidedAction action) {
        if (action instanceof GuidedTimePickerAction) {
            return VIEW_TYPE_TIME_PICKER;
        } else {
            return super.getItemViewType(action);
        }
    }

    @Override
    public int onProvideItemLayoutId(int viewType) {
        if (viewType == VIEW_TYPE_TIME_PICKER) {
            return R.layout.guided_action_time_picker;
        } else {
            return super.onProvideItemLayoutId(viewType);
        }
    }
    //endregion

    public void onBindActivatorView(@NonNull GuidedActionsStylist.ViewHolder vh, GuidedAction action) {
        if (action instanceof GuidedTimePickerAction) {
            GuidedTimePickerAction timeAction = (GuidedTimePickerAction) action;
            TimePicker timeView = vh.itemView.findViewById(R.id.guidedactions_activator_item);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(timeAction.getTime());
            timeView.setHour(calendar.get(Calendar.HOUR_OF_DAY));
            timeView.setMinute(calendar.get(Calendar.MINUTE));
        } else {
            super.onBindActivatorView(vh, action);
        }
    }

    public boolean onUpdateActivatorView(@NonNull GuidedActionsStylist.ViewHolder vh, GuidedAction action) {
        if (action instanceof GuidedTimePickerAction) {
            GuidedTimePickerAction timeAction = (GuidedTimePickerAction) action;
            TimePicker timeView = vh.itemView.findViewById(R.id.guidedactions_activator_item);

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(timeAction.getTime());
            calendar.set(Calendar.HOUR_OF_DAY, timeView.getHour());
            calendar.set(Calendar.MINUTE, timeView.getMinute());
            if (timeAction.getTime() != calendar.getTimeInMillis()) {
                timeAction.setTime(calendar.getTimeInMillis());
                return true;
            } else {
                return false;
            }
        } else {
            return super.onUpdateActivatorView(vh, action);
        }
    }
}