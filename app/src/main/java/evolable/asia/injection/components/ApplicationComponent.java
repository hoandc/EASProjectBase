package evolable.asia.injection.components;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import javax.inject.Singleton;

import dagger.Component;
import evolable.asia.injection.ApplicationContext;
import evolable.asia.injection.modules.ApplicationModule;
import evolable.asia.models.manager.DataManager;
import evolable.asia.ui.base.BaseActivity;
import evolable.asia.ui.base.BaseFragment;
import io.realm.Realm;
import retrofit2.Retrofit;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();

    Application application();

    Retrofit retrofit();

    DataManager dataManager();

    Realm realm();

    Toast toast();

    void inject(BaseActivity baseActivity);

    void inject(BaseFragment fragment);
}
