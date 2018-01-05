package evolable.asia.injection.components;

import dagger.Subcomponent;
import evolable.asia.injection.modules.ActivityModule;
import evolable.asia.ui.main.MainActivity;
import evolable.asia.ui.splash.SplashActivity;

@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SplashActivity activity);

    void inject(MainActivity activity);

}
