package com.promobileapp.chiasenhac.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class ViewUtils {



    public static final int getHeightScreen(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static final int getWidthScreen(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
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


    public static Bitmap convertDrawableToBitmap(Context context, int icon_resource) {
        Bitmap icon = BitmapFactory.decodeResource(context.getResources(),
                icon_resource);
        return icon;
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static void ImageViewAnimatedChange(Context c, final ImageView v, final Bitmap new_image) {
        final Animation anim_out = AnimationUtils.loadAnimation(c, android.R.anim.fade_out);
        final Animation anim_in = AnimationUtils.loadAnimation(c, android.R.anim.fade_in);
        anim_out.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                v.setImageBitmap(new_image);
                anim_in.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                    }
                });
                v.startAnimation(anim_in);
            }
        });
        v.startAnimation(anim_out);
    }

    public static void rotateThumb(ImageView imgThumb) {
        RotateAnimation rotate = new RotateAnimation(
                0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );

        rotate.setDuration(30000);
        rotate.setRepeatCount(Animation.INFINITE);
        imgThumb.startAnimation(rotate);
    }

    public static  void clearAnimationThumb(ImageView imgThumb) {
        imgThumb.clearAnimation();
    }

//    public static void createView(Context context,VideoFromSearch videoFromSearch, View view) {
//        CircleImageView image;
//        image = new CircleImageView(context);
//        image.setX(view.getX());
//        image.setY(view.getY());
//
//        GlideApp.with(context).
//                load(videoFromSearch.thumbnail).
//                fitCenter().
//                into(image);
//
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewUtils.dpToPixels(50), ViewUtils.dpToPixels(50));
//        if (view.getY() == 0.0) {
//            params.setMargins(ViewUtils.getWidthScreen(context) - btnDownLoading.getWidth(),
//                    ViewUtils.dpToPixels(8),
//                    (ViewUtils.dpToPixels(8)),
//                    ViewUtils.dpToPixels(8));
//        } else {
//            params.setMargins(ViewUtils.getWidthScreen(context) - btnDownLoading.getWidth(),
//                    (int) (ViewUtils.dpToPixels(8) - view.getY()),
//                    (ViewUtils.dpToPixels(8)),
//                    ViewUtils.dpToPixels(8));
//        }
//        image.setLayoutParams(params);
//        root.addView(image, params);
//
//        Animation fadeOut = new AlphaAnimation(1, 0);
//        fadeOut.setInterpolator(new AccelerateInterpolator());
//        fadeOut.setStartOffset(0);
//        fadeOut.setDuration(500);
//
//        AnimationSet animation = new AnimationSet(false);
//        animation.addAnimation(fadeOut);
//        image.setAnimation(animation);
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                root.removeView(image);
//                YoYo.with(Techniques.Shake)
//                        .duration(500)
//                        .repeat(0)
//                        .playOn(findViewById(R.id.btn_downloading));
//                Intent intentQuee = new Intent(MainActivity.this, DownloadMp3Service.class);
//                intentQuee.setAction(AppConstants.ACTION_ADD_QUEE_DOWNLOAD);
//                intentQuee.putExtra(AppConstants.EXTRA_VIDEO, videoFromSearch);
//                startService(intentQuee);
//            }
//        }, 5000);
//
//    }

}
