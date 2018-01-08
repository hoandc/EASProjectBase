package evolable.asia.ui.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
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
 *  Author HoanDC. Create on 1/3/2018.
 * ******************************************************************************
 */
public abstract class BaseActivity extends AppCompatActivity implements MVPView {
    protected final String TAG = this.getClass().getSimpleName();
    private final String SLIDE_KEY = "slide_key";
    private boolean isSlide = false;
    protected Unbinder viewUnbind;

    @Inject
    Toast toast;
    private ActivityComponent activityComponent;
    private ActivityScopeComponent activityScopeComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(addContextView());
        if (bindView()) {
            viewUnbind = ButterKnife.bind(this);
        }
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            isSlide = bundle.getBoolean(SLIDE_KEY, false);
        }
        EASApplication.get(this).getComponent().inject(this);
        initial();
    }

    protected abstract boolean bindView();

    protected abstract int addContextView();

    protected abstract void initial();

    public void hideAllSystemUI() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View decorView = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
            decorView.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                    | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    private ActivityScopeComponent getActivityScopeComponent() {
        if (activityScopeComponent == null) {
            activityScopeComponent = DaggerActivityScopeComponent.builder()
                    .applicationComponent(EASApplication.get(this).getComponent())
                    .build();
        }
        return activityScopeComponent;
    }

    public final ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = getActivityScopeComponent()
                    .activityComponent(new ActivityModule(this));
        }
        return activityComponent;
    }

    @Override
    public void showNoNetworkAlert() {
        showMessage(R.string.error_not_connect_to_internet);
    }

    @Override
    public boolean isConnectToInternet() {
        return Utils.isConnectivityAvailable(this);
    }

    @MainThread
    @UiThread
    protected void showMessage(@StringRes int strRes) {
        showMessage(getString(strRes));
    }

    @MainThread
    @UiThread
    protected void showMessage(String message) {
        if (toast == null) {
            toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    @Override
    protected void onDestroy() {
        if (viewUnbind != null) {
            viewUnbind.unbind();
        }
        super.onDestroy();
    }

    // Start activity
    protected final void navigateToActivity(Class<? extends Activity> activity) {
        navigateToActivity(activity, 0);
    }

    protected final void navigateToActivitySlide(Class<? extends Activity> activity) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(SLIDE_KEY, true);
        navigateToActivitySlide(activity, bundle);
        overridePendingTransition(R.anim.right_out, R.anim.left_in);
    }

    protected final void navigateToActivity(@NonNull Class<? extends Activity> activity,
                                            @Nullable Bundle data) {
        navigateToActivity(activity, data, 0);
    }

    protected final void navigateToActivitySlide(@NonNull Class<? extends Activity> activity,
                                                 @Nullable Bundle data) {
        if (data != null && !data.containsKey(SLIDE_KEY)) {
            data.putBoolean(SLIDE_KEY, true);
        }
        navigateToActivity(activity, data, 0);
        overridePendingTransition(R.anim.right_out, R.anim.left_in);
    }

    protected final void navigateToActivity(@NonNull Class<? extends Activity> activity, int flag) {
        navigateToActivity(activity, null, flag);
    }

    protected void navigateToActivity(@NonNull Class<? extends Activity> activity,
                                      @Nullable Bundle data, int flag) {
        Intent intent = new Intent(this, activity);
        intent.addFlags(flag);
        if (data != null) {
            intent.putExtras(data);
        }
        startActivity(intent);
    }

    protected void navigateToActivityForResult(Class<? extends Activity> activity, int requestCode) {
        navigateToActivityForResult(activity, null, requestCode);
    }

    protected void navigateToActivityForResult(Class<? extends Activity> activity,
                                               @Nullable Bundle data, int requestCode) {
        Intent intent = new Intent(this, activity);
        if (data != null) {
            intent.putExtras(data);
        }
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (isSlide) {
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }
    }
}
