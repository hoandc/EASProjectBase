package evolable.asia.models;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import evolable.asia.injection.ApplicationContext;
import evolable.asia.models.prefs.PrefConst;
/*
 * ******************************************************************************
 *  Copyright Ⓒ 2018. All rights reserved
 *  Author HoanDC. Create on 1/3/2018.
 * ******************************************************************************
 */

@Singleton
public class PreferenceManager {
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

    @Inject
    public PreferenceManager(@ApplicationContext Context context) {
        sharedPreferences = context.getSharedPreferences(PrefConst.PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setLogin(boolean isLogin){
        editor.putBoolean(PrefConst.IS_LOGIN, isLogin);
        editor.apply();
    }

    public boolean isLogin() {
        return sharedPreferences.getBoolean(PrefConst.IS_LOGIN, false);
    }

}
