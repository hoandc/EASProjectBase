package evolable.asia.models.manager;

import javax.inject.Inject;

import evolable.asia.models.entities.User;
import evolable.asia.models.realm.UserRepository;
import io.realm.Realm;

/*
 * ******************************************************************************
 *  Copyright Ⓒ 2018. All rights reserved
 *  Author HoanDC. Create on 1/3/2018.
 * ******************************************************************************
 */
public class DatabaseManager {
    private final Realm realm;
    private UserRepository userRepository;

    @Inject
    public DatabaseManager(Realm realm) {
        this.realm = realm;
    }

    public User getUser(){
        if(userRepository == null){
            userRepository = new UserRepository(realm);
        }
        return userRepository.getUser();
    }

    public boolean saveUser(User user){
        if(userRepository == null){
            userRepository = new UserRepository(realm);
        }
        return userRepository.save(user);
    }

}
