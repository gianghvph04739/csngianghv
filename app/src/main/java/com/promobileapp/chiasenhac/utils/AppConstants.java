package com.promobileapp.chiasenhac.utils;

import android.content.Context;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

public class AppConstants {

    public static final String BASE_URL = "https://chiasenhac.vn";
    public static final String CHART_VN = "/nhac-hot/vietnam.html";
    public static final String CHART_US_UK = "/nhac-hot/us-uk.html";
    public static final String CHART_JP = "/nhac-hot/japan.html";
    public static final String CHART_KR = "/nhac-hot/korea.html";
    public static final String TRENDING = "TRENDING";
    public static final String COUT_NAME = "COUT_NAME";
    public static final String POSITION_TRENDING = "POSITION_TRENDING";

    @IntDef({LOOP.LOOP_OFF, LOOP.LOOP_ONE, LOOP.LOOP_ALL})
    @Retention(value = RetentionPolicy.SOURCE)
    public @interface LOOP {
        int LOOP_OFF = 0;
        int LOOP_ONE = 1;
        int LOOP_ALL = 999;
    }

    public static final String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";
    public static final String DEFAULT_FOLDER_NAME = "GreenSoftVN Mp3Download";
    public static final String PREF_LOCATION_DOWNLOAD = "PREF_LOCATION_DOWNLOAD";
    public static final String fileExtensions[] = new String[]{
            ".aac", ".mp3", ".wav", ".ogg", ".midi", ".3gp", ".mp4", ".m4a", ".amr", ".flac"
    };
}

