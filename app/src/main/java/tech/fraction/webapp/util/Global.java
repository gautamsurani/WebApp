package tech.fraction.webapp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by theonetech25 on 8/31/2015.
 */
public class Global {

    private Context mContext;

    public Global(Context mCon) {
        this.mContext = mCon;
    }

    public synchronized boolean isNetworkAvailable() {
        boolean flag;

        if (checkNetworkAvailable()) {
            flag = true;

        } else {
            flag = false;
            Log.d("", "No network available!");
        }
        return flag;
    }


    private boolean checkNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

}
