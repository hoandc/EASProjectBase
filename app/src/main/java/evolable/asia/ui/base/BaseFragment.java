package evolable.asia.ui.base;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import evolable.asia.EASApplication;
import evolable.asia.R;
import evolable.asia.injection.components.ActivityComponent;
import evolable.asia.injection.components.ActivityScopeComponent;
import evolable.asia.injection.components.DaggerActivityScopeComponent;
import evolable.asia.injection.modules.ActivityModule;
import evolable.asia.utils.Utils;

/*
 * ******************************************************************************
 *  Copyright Ⓒ 2018. All rights reserved
 *  Author HoanDC. Create on 5/1/2018.
 * ******************************************************************************
 */
public abstract class BaseFragment extends Fragment implements MvpView {

    private final String SLIDE_KEY = "slide_key";
    protected BaseActivity activity;
    protected Unbinder viewUnbind;
    @Inject
    Toast toast;
    private ActivityComponent activityComponent;
    private ActivityScopeComponent activityScopeComponent;

    private ActivityScopeComponent getActivityScopeComponent() {
        if (activityScopeComponent == null) {
            activityScopeComponent = DaggerActivityScopeComponent.builder()
                    .applicationComponent(EASApplication.get(getActivity()).getComponent())
                    .build();
        }
        return activityScopeComponent;
    }

    public final ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = getActivityScopeComponent()
                    .activityComponent(new ActivityModule(getActivity()));
        }
        return activityComponent;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            activity = (BaseActivity) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(addContextView(), container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (bindView()) {
            viewUnbind = ButterKnife.bind(this, view);
        }
        EASApplication.get(getActivity()).getComponent().inject(this);

        initial();
    }

    protected abstract boolean bindView();

    protected abstract int addContextView();

    protected abstract void initial();

    @Override
    public void showNoNetworkAlert() {
        showMessage(R.string.error_not_connect_to_internet);
    }

    @MainThread
    @UiThread
    protected void showMessage(@StringRes int strRes) {
        showMessage(getString(strRes));
    }

    @MainThread
    @UiThread
    protected void showMessage(String message) {
        Toast toast = this.toast;
        if (toast != null) {
            toast.setText(message);
        }
        Context context = getContext();
        if (context != null) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        }
        if (toast != null) {
            toast.show();
        }
    }

    @Override
    public boolean isConnectToInternet() {
        return Utils.isConnectivityAvailable(getActivity());
    }

    @Override
    public void onDestroyView() {
        if (viewUnbind != null) viewUnbind.unbind();
        super.onDestroyView();
    }

    protected void navigateToActivityForResultSlide(Class<? extends Activity> activity, int requestCode) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(SLIDE_KEY, true);
        navigateToActivityForResult(activity, bundle, requestCode);
        this.activity.overridePendingTransition(R.anim.right_out, R.anim.left_in);
    }

    protected void navigateToActivityForResultSlide(Class<? extends Activity> activity,
                                                    @Nullable Bundle data, int requestCode) {
        Intent intent = new Intent(this.activity, activity);
        if (data != null) {
            intent.putExtras(data);
        }
        startActivityForResult(intent, requestCode);
        this.activity.overridePendingTransition(R.anim.right_out, R.anim.left_in);
    }

    protected void navigateToActivitySlide(Class<? extends Activity> activity) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(SLIDE_KEY, true);
        navigateToActivity(activity, bundle);
        this.activity.overridePendingTransition(R.anim.right_out, R.anim.left_in);
    }

    protected void navigateToActivitySlide(Class<? extends Activity> activity, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putBoolean(SLIDE_KEY, true);
        navigateToActivity(activity, bundle);
        this.activity.overridePendingTransition(R.anim.right_out, R.anim.left_in);
    }

    protected void navigateToActivityForResult(Class<? extends Activity> activity,
                                               @Nullable Bundle data, int requestCode) {
        Intent intent = new Intent(this.activity, activity);
        if (data != null) {
            intent.putExtras(data);
        }
        startActivityForResult(intent, requestCode);
    }

    protected void navigateToActivity(Class<? extends Activity> activity, @Nullable Bundle data) {
        Intent intent = new Intent(this.activity, activity);
        if (data != null) {
            intent.putExtras(data);
        }
        startActivity(intent);
    }

    protected void onBackPressed() {
        if (getActivity().getSupportFragmentManager().getBackStackEntryCount() <= 0) {
            getActivity().finish();
        } else {
            getActivity().getSupportFragmentManager().popBackStack();
        }
    }

}
