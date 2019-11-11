package com.promobileapp.chiasenhac.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;


import com.promobileapp.chiasenhac.BuildConfig;
import com.promobileapp.chiasenhac.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileProviderApp extends FileProvider {
    public static final String AUDIO_TYPE = "audio/mpeg";

    public static Uri getUriForFile(@NonNull Context context, @NonNull String filePath) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return FileProvider.getUriForFile(context, BuildConfig.FILES_AUTHORITY, new File(filePath));
        }
        return Uri.parse("file://" + filePath);
    }

    public static void share(@NonNull Context context, @NonNull String path) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, getUriForFile(context, path));
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf(".")));
        shareIntent.setType(AUDIO_TYPE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }

        context.startActivity(Intent.createChooser(shareIntent, context.getString(R.string.share)));
    }

    public static void share(@NonNull Context context, @NonNull List<String> paths) {
        ArrayList<Uri> fileUris = new ArrayList<>(paths.size());
        for (String path : paths) {
            fileUris.add(getUriForFile(context, path));
        }
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
        shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, fileUris);
        shareIntent.setType(AUDIO_TYPE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        context.startActivity(Intent.createChooser(shareIntent, context.getString(R.string.share)));
    }

    public static void view(@NonNull Context context, @NonNull String path) {
        Intent viewIntent = new Intent(Intent.ACTION_VIEW);
        viewIntent.setDataAndType(getUriForFile(context, path), AUDIO_TYPE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            viewIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        context.startActivity(Intent.createChooser(viewIntent, context.getString(R.string.open_with)));
    }
}