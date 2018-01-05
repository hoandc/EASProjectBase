package evolable.asia.ui.authens.login;

import javax.inject.Inject;

import evolable.asia.models.DataManager;
import evolable.asia.ui.base.BasePresenter;
import retrofit2.Retrofit;

/*
 * ******************************************************************************
 *  Copyright Ⓒ 2018. All rights reserved
 *  Author HoanDC. Create on 1/5/2018.
 * ******************************************************************************
 */
public class LoginPresenter extends BasePresenter<LoginMvpView> {

    @Inject
    public LoginPresenter(Retrofit retrofit, DataManager dataManager) {
        super(retrofit, dataManager);
    }
}
