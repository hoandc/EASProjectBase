package evolable.asia.models.realm;

import evolable.asia.models.entities.User;
import io.realm.Realm;
import io.realm.RealmQuery;

/*
 * ******************************************************************************
 *  Copyright Ⓒ 2018. All rights reserved
 *  Author HoanDC. Create on 1/8/2018.
 * ******************************************************************************
 */
public class UserRepository extends Repository {

    public UserRepository(Realm realm) {
        super(realm);
    }

    private RealmQuery<User> getQuery() {
        return getRealmInstance().where(User.class);
    }

    public User getUser() {
        Realm realm = getRealmInstance();
        realm.beginTransaction();
        try {
            User user = null;
            RealmQuery<User> realmQuery = getQuery();
            if (realmQuery.count() > 0) {
                user = realmQuery.findFirst();
            }
            realm.commitTransaction();
            return user;
        } catch (Exception e) {
            realm.cancelTransaction();
            e.printStackTrace();
        }
        return null;
    }

    public boolean save(User user) {
        if (user == null) {
            return false;
        }
        Realm realm = getRealmInstance();
        realm.beginTransaction();
        try {
            realm.delete(User.class);
            realm.insert(user);
            realm.commitTransaction();
            return true;
        } catch (Exception e) {
            realm.cancelTransaction();
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteUser() {
        Realm realm = getRealmInstance();
        realm.beginTransaction();
        try {
            realm.delete(User.class);
            realm.commitTransaction();
            return true;
        } catch (Exception e) {
            realm.cancelTransaction();
            e.printStackTrace();
        }
        return false;
    }
}
