package evolable.asia.ui.authens.login;

import evolable.asia.models.entities.User;
import evolable.asia.ui.base.BaseScreenMVPView;

/*
 * ******************************************************************************
 *  Copyright Ⓒ 2018. All rights reserved
 *  Author HoanDC. Create on 1/5/2018.
 * ******************************************************************************
 */
public interface LoginMVPView extends BaseScreenMVPView {

    void navigateMain(User user);

    void navigateForgotPassword();

    void navigateRegister();

}
