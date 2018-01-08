package evolable.asia.models.networking;

import evolable.asia.models.responses.AuthenticResponse;
import io.reactivex.Observable;
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

    @FormUrlEncoded
    @POST("login")
    Observable<AuthenticResponse> login(@Field(value = "email", encoded = true) String email,
                                        @Field(value = "password", encoded = true) String password);

    @FormUrlEncoded
    @POST("login")
    Observable<AuthenticResponse> register(@Field(value = "email", encoded = true) String email,
                                           @Field(value = "password", encoded = true) String password);

}
