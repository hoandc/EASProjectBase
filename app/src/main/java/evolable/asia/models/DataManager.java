/*
 * ******************************************************************************
 *  Copyright Ⓒ 2017. All rights reserved
 *  Author HoanDC. Create on 29/03/2017.
 * ******************************************************************************
 */
package evolable.asia.models;

import javax.inject.Inject;

/*
 * ******************************************************************************
 *  Copyright Ⓒ 2018. All rights reserved
 *  Author HoanDC. Create on 1/3/2018.
 * ******************************************************************************
 */
public class DataManager {

    private NetworkManager networkManager;
    private DatabaseManager databaseManager;
    private PreferenceManager preferenceManager;

    @Inject
    public DataManager(NetworkManager networkManager, DatabaseManager databaseManager,
                       PreferenceManager preferenceManager) {
        this.networkManager = networkManager;
        this.databaseManager = databaseManager;
        this.preferenceManager = preferenceManager;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public NetworkManager getNetworkManager() {
        return networkManager;
    }

    public PreferenceManager getPreferenceManager() {
        return preferenceManager;
    }

}
