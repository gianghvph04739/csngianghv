package com.promobileapp.chiasenhac.utils;

import android.content.Context;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class ToastUtils {
    public static void error(Context context, String mess) {
        Toasty.error(context, mess, Toast.LENGTH_SHORT, true).show();
    }

    public static void error(Context context, int resId) {
        Toasty.error(context, context.getString(resId), Toast.LENGTH_SHORT, true).show();
    }

    public static void success(Context context, String mess) {
        Toasty.success(context, mess, Toast.LENGTH_SHORT, true).show();
    }

    public static void success(Context context, int resId) {
        Toasty.success(context, context.getString(resId), Toast.LENGTH_SHORT, true).show();
    }

    public static void warning(Context context, String mess) {
        Toasty.warning(context, mess, Toast.LENGTH_SHORT, true).show();
    }

    public static void warning(Context context, int resId) {
        Toasty.warning(context, context.getString(resId), Toast.LENGTH_SHORT, true).show();
    }


}
