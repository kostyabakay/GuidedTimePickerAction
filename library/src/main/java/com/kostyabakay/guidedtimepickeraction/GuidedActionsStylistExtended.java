package com.kostyabakay.guidedtimepickeraction;

import android.support.v17.leanback.widget.GuidedAction;
import android.support.v17.leanback.widget.GuidedActionsStylist;
import android.support.v17.leanback.widget.picker.TimePicker;

import java.util.Calendar;

public class GuidedActionsStylistExtended extends GuidedActionsStylist {

    public static final int VIEW_TYPE_TIME_PICKER = 2;

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
            return R.layout.action_time_picker;
        } else {
            return super.onProvideItemLayoutId(viewType);
        }
    }

    public void onBindActivatorView(GuidedActionsStylist.ViewHolder vh, GuidedAction action) {
        if (action instanceof GuidedTimePickerAction) {
            GuidedTimePickerAction timeAction = (GuidedTimePickerAction) action;
            TimePicker timeView = vh.itemView.findViewById(R.id.guidedactions_activator_item);
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(timeAction.getDate());
            timeView.setHour(c.get(Calendar.HOUR_OF_DAY));
            timeView.setMinute(c.get(Calendar.MINUTE));
        } else {
            super.onBindActivatorView(vh, action);
        }
    }

    public boolean onUpdateActivatorView(GuidedActionsStylist.ViewHolder vh, GuidedAction action) {
        if (action instanceof GuidedTimePickerAction) {
            GuidedTimePickerAction timeAction = (GuidedTimePickerAction) action;
            TimePicker timeView = vh.itemView.findViewById(R.id.guidedactions_activator_item);

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(timeAction.getDate());
            c.set(Calendar.HOUR_OF_DAY, timeView.getHour());
            c.set(Calendar.MINUTE, timeView.getMinute());
            if (timeAction.getDate() != c.getTimeInMillis()) {
                timeAction.setDate(c.getTimeInMillis());
                return true;
            } else {
                return false;
            }
        } else {
            return super.onUpdateActivatorView(vh, action);
        }
    }
}