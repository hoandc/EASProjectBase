package evolable.asia.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import com.afollestad.materialdialogs.MaterialDialog;

import evolable.asia.R;
import evolable.asia.utils.Utils;

/*
 * ******************************************************************************
 *  Copyright Ⓒ 2018. All rights reserved
 *  Author HoanDC. Create on 5/1/2018.
 * ******************************************************************************
 */
public abstract class BaseActivityWithDialog extends BaseActivity implements BaseScreenMvpView {

    protected MaterialDialog progressDialog, alertDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createProgressDialog();
        createAlertDialog();
    }


    @Override
    public void createProgressDialog() {
        progressDialog = new MaterialDialog.Builder(this)
                .content(R.string.waiting_message)
                .progress(true, 0)
                .build();
    }

    @Override
    public void showProgressDialog() {
        if (progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void createAlertDialog() {
        alertDialog = new MaterialDialog.Builder(this)
                .title(R.string.title_dialog)
                .positiveText(R.string.action_ok)
                .build();
    }

    @Override
    public void showAlertDialog(String errorMessage) {
        alertDialog.setContent(errorMessage);
        alertDialog.show();
    }

    @Override
    public void showAlertDialog(@StringRes int resourceId) {
        alertDialog.setContent(resourceId);
        alertDialog.show();
    }

    @Override
    public void showAlert(String message) {
        showMessage(message);
    }

    @Override
    public void showAlert(@StringRes int resourceId) {
        showMessage(resourceId);
    }

    @Override
    public void showNoNetworkAlert() {
        showAlertDialog(getString(R.string.error_not_connect_to_internet));
    }

    @Override
    public boolean isConnectToInternet() {
        return Utils.isConnectivityAvailable(this);
    }

    @Override
    protected void onDestroy() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
        super.onDestroy();
    }
}
