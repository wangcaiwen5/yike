package com.yike.login;

import android.util.Log;
import com.yike.arouter.Arouter;
import com.yike.arouter.IArouter;
import com.yike.login.ui.LoginActivity;

/**
 * Author：wangcaiwen
 * Date：2019-07-16
 * Description：1
 *
 */
public class ActivityUtils implements IArouter {
    private static final String TAG = "ActivityUtils";
    @Override
    public void putActivity() {
        Log.e(TAG, "putActivity: " );
        Arouter.getInstance().putActivity("login/login", LoginActivity.class);
    }
}
