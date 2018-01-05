package evolable.asia.ui.splash;

import javax.inject.Inject;

import evolable.asia.R;
import evolable.asia.ui.base.BaseActivity;
import evolable.asia.ui.base.BaseActivityWithDialog;

/*
 * ******************************************************************************
 *  Copyright Ⓒ 2018. All rights reserved
 *  Author HoanDC. Create on 1/4/2018.
 * ******************************************************************************
 */
public class SplashActivity extends BaseActivityWithDialog implements SplashMvpView{

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
        presenter.init();
    }

    @Override
    public void navigateMainScreen() {

    }

    @Override
    public void navigateLogin() {

    }
}
