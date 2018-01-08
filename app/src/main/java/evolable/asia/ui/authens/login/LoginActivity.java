package evolable.asia.ui.authens.login;

import android.view.View;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import evolable.asia.R;
import evolable.asia.models.entities.User;
import evolable.asia.ui.base.BaseActivityWithDialog;
import evolable.asia.ui.main.MainActivity;

public class LoginActivity extends BaseActivityWithDialog implements LoginMVPView {

    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.edt_password)
    EditText edtPassword;

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
        getActivityComponent().inject(this);
        presenter.initialView(this);
    }

    @OnClick({R.id.btn_login, R.id.tv_forgot_password, R.id.tv_register})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.btn_login:
                // TODO you should validate values at here.
                User user = new User(edtEmail.getText().toString(), edtPassword.getText().toString());
                presenter.login(user);
                break;
            case R.id.tv_forgot_password:
                presenter.forgotPassword();
                break;
            case R.id.tv_register:
                presenter.register();
                break;
            default:
                break;
        }
    }

    @Override
    public void navigateMain(User user) {
        // You can pass user data to main screen if need or get it from datamanager class.
        navigateToActivity(MainActivity.class);
        finish();
    }

    @Override
    public void navigateForgotPassword() {

    }

    @Override
    public void navigateRegister() {

    }

    @Override
    protected void onDestroy() {
        presenter.destroy();
        super.onDestroy();
    }
}
