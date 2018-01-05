package evolable.asia.ui.base;

/*
 * ******************************************************************************
 *  Copyright Ⓒ 2018. All rights reserved
 *  Author HoanDC. Create on 1/3/2018.
 * ******************************************************************************
 */
public interface Presenter<V extends MvpView> {

    void initialView(V mvpView);

    void destroyView();

    void destroy();
}
