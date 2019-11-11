package com.promobileapp.chiasenhac.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileUtils {
    public static String getStorageSDDirectories(Context context) {
        String[] storageDirectories = null;
        String rawSecondaryStoragesStr = System.getenv("SECONDARY_STORAGE");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            List<String> results = new ArrayList<String>();
            File[] externalDirs = context.getExternalFilesDirs(null);
            for (File file : externalDirs) {
                String path = null;
                try {
                    path = file.getPath().split("/Android")[0];
                } catch (Exception e) {
                    e.printStackTrace();
                    path = null;
                }
                if (path != null) {
                    if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && Environment.isExternalStorageRemovable(file))
                            || rawSecondaryStoragesStr != null && rawSecondaryStoragesStr.contains(path)) {
                        results.add(path);
                    }
                }
            }
            storageDirectories = results.toArray(new String[0]);
        } else {
            final Set<String> rv = new HashSet<String>();

            if (!TextUtils.isEmpty(rawSecondaryStoragesStr)) {
                final String[] rawSecondaryStorages = rawSecondaryStoragesStr.split(File.pathSeparator);
                Collections.addAll(rv, rawSecondaryStorages);
            }
            storageDirectories = rv.toArray(new String[rv.size()]);
        }
        return storageDirectories.length == 0 ? "" : storageDirectories[0];
    }

    public static ArrayList<Uri> listAllFileFromThemeFolder(Context context) {
        ArrayList<Uri> lst = new ArrayList<>();
        File directory = AppUtils.getDownloadThemeFolder(context);
        File[] files = directory.listFiles();
        for (int i = 0; i < files.length; i++) {
            Uri uri = Uri.fromFile(files[i]);
            lst.add(uri);
        }
        return lst;
    }

    public static long getAvailableMemory() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return (availableBlocks * blockSize) / (1024 * 1024);
    }
}
