package evolable.asia.ui.splash;

import javax.inject.Inject;

import evolable.asia.R;
import evolable.asia.ui.authens.login.LoginActivity;
import evolable.asia.ui.base.BaseActivityWithDialog;
import evolable.asia.ui.main.MainActivity;

/*
 * ******************************************************************************
 *  Copyright Ⓒ 2018. All rights reserved
 *  Author HoanDC. Create on 1/4/2018.
 * ******************************************************************************
 */
public class SplashActivity extends BaseActivityWithDialog implements SplashMVPView {

    @Inject
    SplashPresenter presenter;

    @Override
    protected boolean bindView() {
        return true;
    }

    @Override
    protected int addContextView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initial() {
        getActivityComponent().inject(this);
        presenter.initialView(this);
        presenter.start();
    }

    @Override
    public void navigateMainScreen() {
        navigateToActivity(MainActivity.class);
        finish();
    }

    @Override
    public void navigateLogin() {
        navigateToActivity(LoginActivity.class);
        finish();
    }
}
