package evolable.asia.ui.base;

import android.support.annotation.CallSuper;

import evolable.asia.models.DataManager;
import evolable.asia.models.entities.ApiError;
import evolable.asia.utils.exception.HttpUtil;
import retrofit2.Retrofit;

/*
 * ******************************************************************************
 *  Copyright Ⓒ 2018. All rights reserved
 *  Author HoanDC. Create on 1/3/2018.
 * ******************************************************************************
 */
public abstract class BasePresenter<V extends MvpView> implements Presenter<V> {

    private V mvpView;

    protected final Retrofit retrofit;
    protected final DataManager dataManager;

    public BasePresenter(Retrofit retrofit, DataManager dataManager) {
        this.retrofit = retrofit;
        this.dataManager = dataManager;
    }

    @CallSuper
    @Override
    public void initialView(V mvpView) {
        this.mvpView = mvpView;
    }

    @CallSuper
    @Override
    public void destroyView() {
        mvpView = null;
    }

    public final boolean isInitialView() {
        return mvpView != null;
    }

    public final V getMvpView() {
        return mvpView;
    }

    @CallSuper
    @Override
    public void destroy() {

    }

    public void notifyNoNetwork() {
        if (isInitialView()) {
            getMvpView().showNoNetworkAlert();
        }
    }

    public boolean isConnectToInternet() {
        return !isInitialView() || getMvpView().isConnectToInternet();
    }

    public ApiError getErrorFromHttp(Throwable error) {
        return HttpUtil.getError(error, retrofit);
    }

}
