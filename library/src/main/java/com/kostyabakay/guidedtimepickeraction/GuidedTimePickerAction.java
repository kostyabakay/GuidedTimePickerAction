package com.kostyabakay.guidedtimepickeraction;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v17.leanback.widget.GuidedAction;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Subclass of GuidedAction that can choose a time. The Action is editable by default; to make it
 * read only, call hasEditableActivatorView(false) on the Builder.
 */
public class GuidedTimePickerAction extends GuidedAction {

    private long mTime;

    //region GuidedAction
    @Override
    public void onSaveInstanceState(Bundle bundle, String key) {
        bundle.putLong(key, getTime());
    }

    @Override
    public void onRestoreInstanceState(Bundle bundle, String key) {
        setTime(bundle.getLong(key, getTime()));
    }
    //endregion

    /**
     * Get current value of TimePicker in milliseconds since January 1, 1970 00:00:00 in
     * {@link TimeZone#getDefault()} time zone.
     *
     * @return Current value of TimePicker Action.
     */
    public long getTime() {
        return mTime;
    }

    /**
     * Sets current value of TimePicker in milliseconds since January 1, 1970 00:00:00 in
     * {@link TimeZone#getDefault()} time zone.
     *
     * @param time New value to update current value of TimePicker Action.
     */
    public void setTime(long time) {
        mTime = time;
    }

    /**
     * Base Builder class to build GuidedTimePickerAction. Subclass this BuilderBase when app needs
     * to subclass GuidedTimePickerAction, implement your build() which should call
     * {@link #applyTimePickerValues(GuidedTimePickerAction)}. When using GuidedTimePickerAction
     * directly, use {@link Builder}.
     */
    public abstract static class BuilderBase<B extends BuilderBase>
            extends GuidedAction.BuilderBase<B> {

        private long mTime;
        private long mMinTime = Long.MIN_VALUE;
        private long mMaxTime = Long.MAX_VALUE;

        public BuilderBase(Context context) {
            super(context);
            Calendar c = Calendar.getInstance();
            mTime = c.getTimeInMillis();
            hasEditableActivatorView(true);
        }

        /**
         * Sets a time for time picker in milliseconds since January 1, 1970 00:00:00 in
         * {@link TimeZone#getDefault()} time zone.
         *
         * @return This Builder Object.
         */
        public B time(long time) {
            mTime = time;
            return (B) this;
        }

        /**
         * Apply values to GuidedTimePickerAction.
         *
         * @param action GuidedTimePickerAction to apply values.
         */
        protected final void applyTimePickerValues(@NonNull GuidedTimePickerAction action) {
            super.applyValues(action);
            action.mTime = mTime;
            if (mMinTime > mMaxTime) {
                throw new IllegalArgumentException("MinTime cannot be larger than MaxTime");
            }
        }
    }

    /**
     * Builder class to build a GuidedTimePickerAction.
     */
    public final static class Builder extends BuilderBase<Builder> {

        public Builder(Context context) {
            super(context);
        }

        /**
         * Builds the GuidedTimePickerAction corresponding to this Builder.
         *
         * @return The GuidedTimePickerAction as configured through this Builder.
         */
        public GuidedTimePickerAction build() {
            GuidedTimePickerAction action = new GuidedTimePickerAction();
            applyTimePickerValues(action);
            return action;
        }
    }
}