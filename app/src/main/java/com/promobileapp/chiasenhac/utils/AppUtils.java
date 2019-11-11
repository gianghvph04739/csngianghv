package com.promobileapp.chiasenhac.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;

import androidx.annotation.NonNull;

import com.promobileapp.chiasenhac.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public final class AppUtils {
    public static final String YT_URL = "https://www.youtube.com/watch?v=";

    private AppUtils() {
    }

    public static boolean isOnline(@NonNull Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = null;
        if (cm != null) {
            netInfo = cm.getActiveNetworkInfo();
        }
        return netInfo != null && netInfo.isConnected();
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static String sha256(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (byte h : hash) {
                String hex = Integer.toHexString(0xff & h);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception e) {
            return "";
        }
    }

    public static String convertDuration(long dura) {
        long sec = (dura / 1000) % 60;
        long min = ((dura / 1000) / 60) % 60;
        long hour = ((dura / 1000) / 60) / 60;
        String s = "", m = "", h = "";
        if (sec < 10) {
            s = "0" + sec;
        } else {
            s = sec + "";
        }
        if (min < 10) {
            m = "0" + min;
        } else {
            m = min + "";
        }
        if (hour < 10) {
            h = "0" + hour;
        } else {
            h = hour + "";
        }
        String duration = "";
        if (hour == 0) {
            duration = m + ":" + s;

        } else {
            duration = h + ":" + m + ":" + s;
        }
        return duration;
    }


    public static int getHeightStatusBar(Context context) {
        // status bar height
        int statusBarHeight = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

    public static int dpToPixels(int dp) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics()));
    }

    public static int getDuration(@NonNull Context context, String path) {
        int duration = 0;

        try {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(path);
            String durationStr = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            duration = Integer.parseInt(durationStr);
        } catch (Exception e) {
            try {
                MediaPlayer mediaPlayer = MediaPlayer.create(context, Uri.parse("file://" + path));

                if (mediaPlayer != null) {
                    duration = mediaPlayer.getDuration();
                    mediaPlayer.release();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return duration;
    }

    public static String formatTimeDuration(long timeMillis) {
        if (timeMillis < 0) {
            timeMillis = 0;
        }

        long day = TimeUnit.MILLISECONDS.toDays(timeMillis);
        long hour = TimeUnit.MILLISECONDS.toHours(timeMillis) - TimeUnit.DAYS.toHours(day);
        long minute = TimeUnit.MILLISECONDS.toMinutes(timeMillis) - TimeUnit.DAYS.toMinutes(day) - TimeUnit.HOURS.toMinutes(hour);
        long second = TimeUnit.MILLISECONDS.toSeconds(timeMillis) - TimeUnit.DAYS.toSeconds(day) - TimeUnit.HOURS.toSeconds(hour) - TimeUnit.MINUTES.toSeconds(minute);

        if (day > 0) {
            return String.format(Locale.getDefault(), "%1$d:%2$02d:%3$02d:%4$02d", day, hour, minute, second);
        }

        if (hour > 0) {
            return String.format(Locale.getDefault(), "%1$d:%2$02d:%3$02d", hour, minute, second);
        }

        return String.format(Locale.getDefault(), "%1$d:%2$02d", minute, second);
    }

    public static String formatTime(String pattern, long timeInMillis) {
        try {
            return new SimpleDateFormat(pattern, Locale.getDefault()).format(timeInMillis);
        } catch (Exception e) {
            return pattern;
        }
    }

    public static String formatTime(String pattern, Calendar calendar) {
        return formatTime(pattern, calendar.getTimeInMillis());
    }

    public static String getTimeStamp(String pattern) {
        return formatTime(pattern, Calendar.getInstance());
    }

    public static void saveBitmap(Bitmap bitmap, File out) {
        try {
            if (!out.getParentFile().exists()) {
                out.getParentFile().mkdirs();
            }
            if (out.exists()) {
                out.delete();
            }
            FileOutputStream outputStream = new FileOutputStream(out);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 60, outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getRandomCoppyFile() {
        return new Random().nextInt(10000) + 1; // [0, 60] + 20 => [20, 80]
    }

    public static int getRandomDrawable() {
        final int min = 0;
        final int max = 7;
        final int random = new Random().nextInt((max - min) + 1) + min;
        return random;
    }

    public static double getRandomNumber() {
        double leftLimit = 1.0;
        double rightLimit = 15.0;
        double generatedDouble = leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);
        return (double) (Math.floor(generatedDouble * 10) / 10);
    }

    public static int getRandomNumber(int max) {
        int min = 0;
        Random r = new Random();
        int i1 = r.nextInt(max - min + 1) + min;
        return i1;
    }

    public static String getCurrentMillisecond() {
        Date date = new Date();
        //This method returns the time in millis
        long timeMilli = date.getTime();
        return String.valueOf(timeMilli);
    }

    public static void shareText(Context context, String subject, String text) {
        try {
            List<Intent> shareIntents = new ArrayList<>();
            List<ResolveInfo> resInfo = context.getPackageManager().queryIntentActivities(createShareIntent(subject, text), 0);

            if (resInfo.isEmpty()) {
                throw new Exception("No applications found");
            } else {
                for (ResolveInfo info : resInfo) {
                    Intent shareIntent = createShareIntent(subject, text);

                    if (!info.activityInfo.packageName.equalsIgnoreCase("com.google.android.apps.docs")) {
                        shareIntent.setPackage(info.activityInfo.packageName);
                        shareIntents.add(shareIntent);
                    }
                }

                context.startActivity(Intent.createChooser(shareIntents.remove(0), context.getString(R.string.share))
                        .putExtra(Intent.EXTRA_INITIAL_INTENTS, shareIntents.toArray(new Parcelable[]{})));
            }
        } catch (Exception e) {
            context.startActivity(Intent.createChooser(createShareIntent(subject, text), context.getString(R.string.share)));
        }
    }

    private static Intent createShareIntent(String subject, String text) {
        return new Intent(Intent.ACTION_SEND)
                .setType("text/plain")
                .putExtra(Intent.EXTRA_SUBJECT, subject)
                .putExtra(Intent.EXTRA_TEXT, text);
    }

    public static File getDownloadThemeFolder(@NonNull Context context) {
        File folder = new File(Environment.getExternalStorageDirectory(), context.getPackageName());
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return folder;
    }

    public static String getRelevanceLanguage() {
        return Locale.getDefault().getLanguage();
    }

    public static String outputPath(Context context) {
        String location = PreferenceUtils.getInstance(context).getString(AppConstants.PREF_LOCATION_DOWNLOAD);
        String path = "";
        if (!TextUtils.isEmpty(location)) {
            path = location;
        } else {
            path = Environment.getExternalStorageDirectory().toString() + File.separator + AppConstants.DEFAULT_FOLDER_NAME + "/";
        }
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return path;
    }

    public static void openAppInGooglePlay(Context context, String appPackageName) {
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }


    public static Bitmap convertByteToBitmap(byte[] image) {
        ByteArrayInputStream imageStream = new ByteArrayInputStream(image);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        return theImage;
    }

    public static void deleteFile(Context context, String path) {
        File fdelete = new File(path);
        if (fdelete.exists()) {
            if (fdelete.delete()) {
                Uri rootUri =
                        MediaStore.Audio.Media.getContentUriForPath(path);  // Change file types here
                context.getContentResolver().delete(rootUri,
                        MediaStore.MediaColumns.DATA + "=?", new String[]{path});
                context.deleteFile(fdelete.getName());
                ToastUtils.success(context, context.getString(R.string.delete_file_successfull));
            } else {
                try {
                    fdelete.getCanonicalFile().delete();
                    if (fdelete.exists()) {
                        Uri rootUri =
                                MediaStore.Audio.Media.getContentUriForPath(path);  // Change file types here
                        context.getContentResolver().delete(rootUri,
                                MediaStore.MediaColumns.DATA + "=?", new String[]{path});
                        context.deleteFile(fdelete.getName());
                        ToastUtils.success(context, context.getString(R.string.delete_file_successfull));
                    }
                } catch (IOException e) {
                    Log.e("Exeption", e.getMessage());
                    ToastUtils.error(context, context.getString(R.string.cant_delete_file));
                    e.printStackTrace();
                }
            }
        } else {
            ToastUtils.error(context, context.getString(R.string.txt_file_not_found));
        }
        Uri rootUri =
                MediaStore.Audio.Media.getContentUriForPath(path);  // Change file types here
        context.getContentResolver().delete(rootUri,
                MediaStore.MediaColumns.DATA + "=?", new String[]{path});
    }


    public static String getCurrentLanguage() {
        return Locale.getDefault().toString();
    }

}
