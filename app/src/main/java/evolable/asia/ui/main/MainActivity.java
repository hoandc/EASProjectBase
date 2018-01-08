package evolable.asia.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import evolable.asia.R;
import evolable.asia.ui.base.BaseActivityWithDialog;

/*
 * ******************************************************************************
 *  Copyright Ⓒ 2017. All rights reserved
 *  Author HoanDC. Create on 1/1/2018.
 * ******************************************************************************
 */
public class MainActivity extends BaseActivityWithDialog implements MainMVPView{

    @Inject
    MainPresenter presenter;

    @Override
    protected boolean bindView() {
        return true;
    }

    @Override
    protected int addContextView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initial() {
        getActivityComponent().inject(this);
        presenter.initialView(this);
    }

}
