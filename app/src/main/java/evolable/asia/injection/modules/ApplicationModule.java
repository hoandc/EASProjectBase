package evolable.asia.injection.modules;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import evolable.asia.injection.ApplicationContext;
import evolable.asia.models.networking.NetworkService;
import evolable.asia.models.networking.RetrofitConnection;
import evolable.asia.utils.net.RequestUtil;
import io.realm.Realm;
import retrofit2.Retrofit;

@Module
public class ApplicationModule {
    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    @ApplicationContext
    Context provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    Toast provideToast(@ApplicationContext Context context) {
        return Toast.makeText(context, "", Toast.LENGTH_LONG);
    }

    @Provides
    @Singleton
    NetworkService networkService(Retrofit retrofit){
        return retrofit.create(NetworkService.class);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofitInstance(RequestUtil requestUtil) {
        return new RetrofitConnection(requestUtil).newRetrofitInstance();
    }

    @Provides
    @Singleton
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }

}
