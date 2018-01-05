package evolable.asia.injection.modules;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import evolable.asia.injection.ActivityContext;

@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    public Activity provideActivity() {
        return activity;
    }

    @Provides
    @ActivityContext
    Context provideActivityContext() {
        return activity;
    }
}
