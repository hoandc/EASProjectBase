package evolable.asia.models;

import javax.inject.Inject;

import io.realm.Realm;

/*
 * ******************************************************************************
 *  Copyright Ⓒ 2018. All rights reserved
 *  Author HoanDC. Create on 1/3/2018.
 * ******************************************************************************
 */
public class DatabaseManager {
    private final Realm realm;

    @Inject
    public DatabaseManager(Realm realm) {
        this.realm = realm;
    }

}
