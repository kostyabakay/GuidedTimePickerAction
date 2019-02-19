package com.kostyabakay.guidedtimepickeraction;

import android.content.Context;
import android.os.Bundle;
import android.support.v17.leanback.widget.GuidedAction;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Subclass of GuidedAction that can choose a time.  The Action is editable by default; to make it
 * read only, call hasEditableActivatorView(false) on the Builder.
 */
public class GuidedTimePickerAction extends GuidedAction {
    /**
     * Base Builder class to build GuidedTimePickerAction.  Subclass this BuilderBase when app needs
     * to subclass GuidedTimePickerAction, implement your build() which should call
     * {@link #applyTimePickerValues(GuidedTimePickerAction)}.  When using GuidedTimePickerAction
     * directly, use {@link Builder}.
     */
    public abstract static class BuilderBase<B extends BuilderBase>
            extends GuidedAction.BuilderBase<B> {
        private String mTimePickerFormat;
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
         * Sets format of date Picker or null for default.  The format is a case insensitive String
         * containing the day ('d'), month ('m'), and year ('y').  When the format is not specified,
         * a default format of current locale will be used.
         *
         * @param format Format of showing Date, e.g. "YMD".
         * @return This Builder object.
         */
        public B datePickerFormat(String format) {
            mTimePickerFormat = format;
            return (B) this;
        }

        /**
         * Sets a Time for time picker in milliseconds since January 1, 1970 00:00:00 in
         * {@link TimeZone#getDefault()} time zone.
         *
         * @return This Builder Object.
         */
        public B time(long time) {
            mTime = time;
            return (B) this;
        }

        /**
         * Sets minimal Date for date picker in milliseconds since January 1, 1970 00:00:00 in
         * {@link TimeZone#getDefault()} time zone.
         *
         * @return This Builder Object.
         */
        public B minDate(long minDate) {
            mMinTime = minDate;
            return (B) this;
        }

        /**
         * Sets maximum Date for date picker in milliseconds since January 1, 1970 00:00:00 in
         * {@link TimeZone#getDefault()} time zone.
         *
         * @return This Builder Object.
         */
        public B maxDate(long maxDate) {
            mMaxTime = maxDate;
            return (B) this;
        }

        /**
         * Apply values to GuidedTimePickerAction.
         *
         * @param action GuidedTimePickerAction to apply values.
         */
        protected final void applyTimePickerValues(GuidedTimePickerAction action) {
            super.applyValues(action);
            action.mTimePickerFormat = mTimePickerFormat;
            action.mTime = mTime;
            if (mMinTime > mMaxTime) {
                throw new IllegalArgumentException("MinTime cannot be larger than MaxTime");
            }
            action.mMinTime = mMinTime;
            action.mMaxTime = mMaxTime;
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

    private String mTimePickerFormat;
    private long mTime;
    private long mMinTime = Long.MIN_VALUE;
    private long mMaxTime = Long.MAX_VALUE;

    /**
     * Returns format of date Picker or null if not specified.  The format is a case insensitive
     * String containing the * day ('d'), month ('m'), and year ('y'). When the format is not
     * specified, a default format of current locale will
     * be used.
     *
     * @return Format of showing Date, e.g. "YMD".  Returns null if using current locale's default.
     */
    public String getTimePickerFormat() {
        return mTimePickerFormat;
    }

    /**
     * Get current value of DatePicker in milliseconds since January 1, 1970 00:00:00 in
     * {@link TimeZone#getDefault()} time zone.
     *
     * @return Current value of DatePicker Action.
     */
    public long getDate() {
        return mTime;
    }

    /**
     * Sets current value of DatePicker in milliseconds since January 1, 1970 00:00:00 in
     * {@link TimeZone#getDefault()} time zone.
     *
     * @param date New value to update current value of DatePicker Action.
     */
    public void setDate(long date) {
        mTime = date;
    }

    /**
     * Get minimal value of DatePicker in milliseconds since January 1, 1970 00:00:00 in
     * {@link TimeZone#getDefault()} time zone.  -1 if not set.
     *
     * @return Minimal value of DatePicker Action or Long.MIN_VALUE if not set.
     */
    public long getMinDate() {
        return mMinTime;
    }

    /**
     * Get maximum value of DatePicker in milliseconds since January 1, 1970 00:00:00 in
     * {@link TimeZone#getDefault()} time zone.
     *
     * @return Maximum value of DatePicker Action or Long.MAX_VALUE if not set.
     */
    public long getMaxDate() {
        return mMaxTime;
    }

    @Override
    public void onSaveInstanceState(Bundle bundle, String key) {
        bundle.putLong(key, getDate());
    }

    @Override
    public void onRestoreInstanceState(Bundle bundle, String key) {
        setDate(bundle.getLong(key, getDate()));
    }
}