package com.app.roman.mob_dev.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.inputmethod.InputMethodManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class CommonLib {

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        try {
            imm.hideSoftInputFromWindow(activity.getWindow().getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Bitmap getBitmapFromDisk(String url, Context context) {

        Bitmap defautBitmap = null;
        try {
            String filename = constructFileName(url);
            File filePath = new File(context.getCacheDir(), filename);

            if (filePath.exists() && filePath.isFile() && !filePath.isDirectory()) {
                FileInputStream fi;
                BitmapFactory.Options opts = new BitmapFactory.Options();
                opts.inPreferredConfig = Bitmap.Config.RGB_565;
                fi = new FileInputStream(filePath);
                defautBitmap = BitmapFactory.decodeStream(fi, null, opts);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();

        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }

        return defautBitmap;
    }

    private static String constructFileName(String url) {
        return url.replaceAll("/", "_");
    }

    public static void addBitmapToDisk(String url, Bitmap bmp, Context context) {
        writeBitmapToDisk(url, bmp, context, Bitmap.CompressFormat.PNG);
    }

    private static void writeBitmapToDisk(String url, Bitmap bmp, Context context, Bitmap.CompressFormat format) {
        FileOutputStream fos;
        String fileName = constructFileName(url);
        try {
            if (bmp != null) {
                fos = new FileOutputStream(new File(context.getCacheDir(), fileName));
                bmp.compress(format, 75, fos);
                fos.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean getNetworkState(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean returnValue = false;
        if (null != activeNetwork) {
            returnValue = true;
        } else
            returnValue = false;
        return returnValue;
    }
}
