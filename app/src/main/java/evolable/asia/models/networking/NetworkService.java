/*
 * ******************************************************************************
 *  Copyright Ⓒ 2017. All rights reserved
 *  Author HoanDC. Create on 2017/03/20
 * ******************************************************************************
 */
package evolable.asia.models.networking;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import evolable.asia.BuildConfig;
import evolable.asia.models.convert.DateTypeDeserializer;
import evolable.asia.models.responses.AuthenticResponse;
import io.reactivex.Observable;
import io.realm.annotations.Ignore;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/*
 * ******************************************************************************
 *  Copyright Ⓒ 2018. All rights reserved
 *  Author HoanDC. Create on 1/3/2018.
 * ******************************************************************************
 */
public interface NetworkService {
    int TIMEOUT = 15;
    String HTTP_API_VERSION_HEADER_KEY = "HTTP-X-API-VERSION";
    String UER_AGENT_HEADER_KEY = "User-Agent";
    String UER_AGENT_HEADER_VALUE = "Android";
    String CONNECTION = "Connection";
    String CLOSE = "close";

    @FormUrlEncoded
    @POST("login")
    Observable<AuthenticResponse> login(@Field(value = "email", encoded = true) String email,
                                        @Field(value = "password", encoded = true) String password);

    @FormUrlEncoded
    @POST("login")
    Observable<AuthenticResponse> register(@Field(value = "email", encoded = true) String email,
                                           @Field(value = "password", encoded = true) String password);



    class Creator {

        public static Retrofit newRetrofitInstance() {
            OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
            okBuilder.readTimeout(TIMEOUT, TimeUnit.SECONDS);
            okBuilder.connectTimeout(TIMEOUT, TimeUnit.SECONDS);
            okBuilder.retryOnConnectionFailure(false);
            okBuilder.addInterceptor(chain -> {
                Request original = chain.request();
                Request.Builder builder = original.newBuilder();
                builder.header(HTTP_API_VERSION_HEADER_KEY, BuildConfig.VERSION_NAME)
                        .header(UER_AGENT_HEADER_KEY, UER_AGENT_HEADER_VALUE)
                        .header(CONNECTION, CLOSE)
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
}
