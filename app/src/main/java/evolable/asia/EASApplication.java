package evolable.asia;

import android.app.Application;
import android.content.Context;

import evolable.asia.constants.Constants;
import evolable.asia.injection.components.ApplicationComponent;
import evolable.asia.injection.components.DaggerApplicationComponent;
import evolable.asia.injection.modules.ApplicationModule;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/*
 * ******************************************************************************
 *  Copyright Ⓒ 2018. All rights reserved
 *  Author HoanDC. Create on 1/3/2018.
 * ******************************************************************************
 */
public class EASApplication extends Application {

    private final Object lock = new Object();
    private ApplicationComponent applicationComponent;

    public static EASApplication get(Context context) {
        return (EASApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .schemaVersion(Constants.DATABASE_VERSION)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public ApplicationComponent getComponent() {
        if (applicationComponent == null) {
            synchronized (lock) {
                if (applicationComponent == null) {
                    applicationComponent = DaggerApplicationComponent.builder()
                            .applicationModule(new ApplicationModule(this))
                            .build();
                }
            }
        }
        return applicationComponent;
    }

}
