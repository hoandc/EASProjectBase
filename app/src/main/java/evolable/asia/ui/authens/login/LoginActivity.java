package evolable.asia.ui.authens.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import evolable.asia.R;
import evolable.asia.ui.base.BaseActivityWithDialog;

public class LoginActivity extends BaseActivityWithDialog {

    @Inject
    LoginPresenter presenter;

    @Override
    protected boolean bindView() {
        return true;
    }

    @Override
    protected int addContextView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initial() {

    }

}
