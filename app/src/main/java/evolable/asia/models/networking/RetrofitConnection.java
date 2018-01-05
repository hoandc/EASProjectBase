package evolable.asia.models.networking;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import evolable.asia.BuildConfig;
import evolable.asia.constants.Constants;
import evolable.asia.models.convert.DateTypeDeserializer;
import evolable.asia.utils.net.RequestUtil;
import io.realm.annotations.Ignore;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 * ******************************************************************************
 *  Copyright Ⓒ 2018. All rights reserved
 *  Author HoanDC. Create on 1/4/2018.
 * ******************************************************************************
 */
public class RetrofitConnection {

    @Inject
    RequestUtil requestUtil;

    @Inject
    public RetrofitConnection(RequestUtil requestUtil) {
        this.requestUtil = requestUtil;
    }

    public Retrofit newRetrofitInstance() {
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        okBuilder.readTimeout(Constants.TIMEOUT, TimeUnit.SECONDS);
        okBuilder.connectTimeout(Constants.TIMEOUT, TimeUnit.SECONDS);
        okBuilder.retryOnConnectionFailure(false);
        okBuilder.addInterceptor(chain -> {
            Request original = chain.request();
            Request.Builder builder = original.newBuilder();
            builder.header(Constants.HTTP_API_VERSION_HEADER_KEY, BuildConfig.VERSION_NAME)
                    .header(Constants.UER_AGENT_HEADER_KEY, Constants.UER_AGENT_HEADER_VALUE)
                    .header(Constants.CONNECTION, Constants.CLOSE)
                    .header("Content-type", "application/x-www-form-urlencoded;charset=UTF-8")
                    .method(original.method(), original.body());

            return chain.proceed(builder.build());
        });
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okBuilder.addInterceptor(interceptor);
        }

        Gson gson = new GsonBuilder()
                .addDeserializationExclusionStrategy(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getAnnotation(Ignore.class) != null;
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .registerTypeAdapter(Date.class, new DateTypeDeserializer())
                .create();

        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okBuilder.build())
                .build();
    }
}
