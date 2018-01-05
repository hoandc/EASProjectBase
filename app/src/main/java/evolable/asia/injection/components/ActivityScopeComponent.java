package evolable.asia.injection.components;

import dagger.Component;
import evolable.asia.injection.PerActivity;
import evolable.asia.injection.modules.ActivityModule;

@PerActivity
@Component(dependencies = ApplicationComponent.class)
public interface ActivityScopeComponent {

    ActivityComponent activityComponent(ActivityModule module);
}
