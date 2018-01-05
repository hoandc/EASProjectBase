package evolable.asia.ui.splash;

import android.os.Handler;

import javax.inject.Inject;

import evolable.asia.models.DataManager;
import evolable.asia.models.entities.User;
import evolable.asia.ui.base.BasePresenter;
import retrofit2.Retrofit;

/*
 * ******************************************************************************
 *  Copyright Ⓒ 2018. All rights reserved
 *  Author HoanDC. Create on 1/5/2018.
 * ******************************************************************************
 */
public class SplashPresenter extends BasePresenter<SplashMvpView> {

    private final long DELAY_TIME = 2500;

    @Inject
    public SplashPresenter(Retrofit retrofit, DataManager dataManager) {
        super(retrofit, dataManager);
    }

    @Override
    public void initialView(SplashMvpView mvpView) {
        super.initialView(mvpView);
    }

    public void init(){
        Handler handler = new Handler();
        handler.postDelayed(() -> navigateScreen(), (DELAY_TIME));
    }

    private void navigateScreen(){
//        User user = dataManager.getDatabaseManager();
        getMvpView().navigateLogin();
    }

    @Override
    public void destroy() {
        super.destroy();
    }

}
