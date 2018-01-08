package evolable.asia.ui.authens.login;

import javax.inject.Inject;

import evolable.asia.R;
import evolable.asia.constants.Constants;
import evolable.asia.models.manager.DataManager;
import evolable.asia.models.entities.ApiError;
import evolable.asia.models.entities.User;
import evolable.asia.ui.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/*
 * ******************************************************************************
 *  Copyright Ⓒ 2018. All rights reserved
 *  Author HoanDC. Create on 1/5/2018.
 * ******************************************************************************
 */
public class LoginPresenter extends BasePresenter<LoginMVPView> {

    @Inject
    public LoginPresenter(Retrofit retrofit, DataManager dataManager) {
        super(retrofit, dataManager);
    }

    public void login(User user) {
        dataManager.getNetworkManager().login(user).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    getMVPView().showProgressDialog();
                })
                .doOnComplete(() -> {
                    getMVPView().dismissProgressDialog();
                })
                .subscribe(response -> {
                    if (response != null) {
                        user.setToken(response.getToken());
                        dataManager.getDatabaseManager().saveUser(user);
                        getMVPView().navigateMain(user);
                    }
                }, throwable -> {
                    throwable.printStackTrace();
                    getMVPView().dismissProgressDialog();
                    ApiError apiError = getErrorFromHttp(throwable);
                    if (apiError.getCode() == Constants.HTTP_INTERNET_CONNECTION) {
                        getMVPView().showAlert(R.string.error_invalid_email);
                    } else {
                        getMVPView().showAlert(apiError.getMessage());
                    }
                });
    }

    public void register() {
        getMVPView().navigateRegister();
    }

    public void forgotPassword() {
        getMVPView().navigateForgotPassword();
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
