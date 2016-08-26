package org.spmakings.examshell.Application;

import android.app.Application;
import android.content.SharedPreferences;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.spmakings.examshell.Models.UserDetails;

/**
 * Created by apple on 17/07/16.
 */
public class ECellApplication extends MultiDexApplication {

    private SharedPreferences userData = null;
    UserDetails user = null;
    private RequestQueue mRequestQueue;
    private static ECellApplication mInstance;
    public final String TAG = "ECellApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }


    public UserDetails getUserData() {
        if (userData == null) {
            userData = getSharedPreferences("ECellApplication", MODE_PRIVATE);
        }
        if (user == null) {
            user = new UserDetails();
        }
        user.setUserEmail(userData.getString("uEmail", ""));
        user.setUserName(userData.getString("uName", ""));
        user.setUserID(userData.getString("uID", ""));

        return user;
    }

    public void setUserData(final String uName, final String uEmail, final String uID) {
        if (userData == null) {
            userData = getSharedPreferences("ECellApplication", MODE_PRIVATE);
        }
        SharedPreferences.Editor edit = userData.edit();
        edit.putString("uEmail", uEmail);
        edit.putString("uName", uName);
        edit.putString("uID", uID);
        edit.commit();
    }

    public void clearUserData() {
        if (userData == null) {
            userData = getSharedPreferences("ECellApplication", MODE_PRIVATE);
        }
        SharedPreferences.Editor edit = userData.edit();
        edit.clear();
        edit.commit();
    }


    public static synchronized ECellApplication getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

}
