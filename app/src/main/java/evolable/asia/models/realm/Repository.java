package evolable.asia.models.realm;

import io.realm.Realm;

/*
 * ******************************************************************************
 *  Copyright Ⓒ 2018. All rights reserved
 *  Author HoanDC. Create on 1/8/2018.
 * ******************************************************************************
 */
public class Repository {
    private final Realm realm;

    public Repository(Realm realm) {
        this.realm = realm;
    }

    protected Realm getRealmInstance() {
        return realm;
    }
}
