package com.promobileapp.chiasenhac.utils;

import android.util.Log;

public class ParseUtils {
    public static String subString(String mainString) {
        String endString = "";
        int endIndex = mainString.indexOf(")");
        int startIndex = mainString.indexOf("http");
        endString = mainString.substring(startIndex, endIndex);
        return endString;
    }
}
