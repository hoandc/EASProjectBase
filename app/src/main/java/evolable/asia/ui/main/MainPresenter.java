package evolable.asia.ui.main;

import javax.inject.Inject;

import evolable.asia.models.manager.DataManager;
import evolable.asia.ui.base.BasePresenter;
import retrofit2.Retrofit;

/*
 * ******************************************************************************
 *  Copyright Ⓒ 2018. All rights reserved
 *  Author HoanDC. Create on 1/10/2018.
 * ******************************************************************************
 */
public class MainPresenter extends BasePresenter<MainMVPView>{

    @Inject
    public MainPresenter(Retrofit retrofit, DataManager dataManager) {
        super(retrofit, dataManager);
    }

    @Override
    public void initialView(MainMVPView mvpView) {
        super.initialView(mvpView);
    }

    @Override
    public void destroyView() {
        super.destroyView();
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
