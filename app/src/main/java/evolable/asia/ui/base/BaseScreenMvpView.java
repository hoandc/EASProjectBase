package evolable.asia.ui.base;

import android.support.annotation.StringRes;

/*
 * ******************************************************************************
 *  Copyright Ⓒ 2018. All rights reserved
 *  Author HoanDC. Create on 1/3/2018.
 * ******************************************************************************
 */
public interface BaseScreenMvpView extends MvpView{

    void createProgressDialog();

    void createAlertDialog();

    void showProgressDialog();

    void showAlertDialog(@StringRes int resourceId);

    void showAlertDialog(String errorMessage);

    void dismissProgressDialog();

    void showAlert(String s);

    void showAlert(@StringRes int resourceId);

}
