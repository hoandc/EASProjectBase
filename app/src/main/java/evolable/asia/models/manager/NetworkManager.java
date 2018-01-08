package evolable.asia.models.manager;

import android.support.annotation.NonNull;

import java.io.File;

import javax.inject.Inject;

import evolable.asia.models.entities.User;
import evolable.asia.models.networking.NetworkService;
import evolable.asia.models.responses.AuthenticResponse;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/*
 * ******************************************************************************
 *  Copyright Ⓒ 2018. All rights reserved
 *  Author HoanDC. Create on 1/3/2018.
 * ******************************************************************************
 */
public class NetworkManager {

    public static final String MULTIPART_FORM_DATA = "multipart/form-data";
    private final NetworkService networkService;

    @Inject
    public NetworkManager(NetworkService networkService) {
        this.networkService = networkService;
    }

    public static RequestBody createRequestBody(@NonNull File file) {
        return RequestBody.create(
                MediaType.parse(MULTIPART_FORM_DATA), file);
    }

    private RequestBody createRequestBody(@NonNull Object object) {
        String value = String.valueOf(object);
        return RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), value);
    }

    public static RequestBody createFileRequestBody(@NonNull File file) {
        return RequestBody.create(
                MediaType.parse(MULTIPART_FORM_DATA), file);
    }

    public Observable<AuthenticResponse> login(User user){
       return networkService.login(user.getEmail(), user.getPassword());
    }

}
