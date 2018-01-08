package evolable.asia.utils.net;

import android.content.Context;
import android.provider.Settings.Secure;

import javax.inject.Inject;

import evolable.asia.injection.ApplicationContext;

/*
 * ******************************************************************************
 *  Copyright Ⓒ 2018. All rights reserved
 *  Author HoanDC. Create on 4/1/2018.
 * ******************************************************************************
 */
public class RequestUtil {

    private final @ApplicationContext
    Context context;

    @Inject
    public RequestUtil(@ApplicationContext Context context) {
        this.context = context;
    }

    public String getDeviceId() {
        return Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
    }

    public String getTimeStamp() {
        Long tsLong = System.currentTimeMillis() / 1000;
        return tsLong.toString();
    }

}
