package evolable.asia.utils.exception;


import android.text.TextUtils;

import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.ConnectException;

import evolable.asia.constants.Constants;
import evolable.asia.models.entities.ApiError;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.HttpException;
import retrofit2.Retrofit;

public class HttpUtil {

    private final static String HEAD_MESSAGE_KEY = "Message";

    public static ApiError getError(Throwable e, Retrofit retrofit) {
        ApiError apiError = null;
        if (e instanceof HttpException) {
            apiError = new ApiError();
            HttpException httpException = (HttpException) e;
            apiError.setCode(httpException.code());

            if (TextUtils.isEmpty(httpException.response().headers().get(HEAD_MESSAGE_KEY))) {
                apiError.setMessage(httpException.response().headers().get(HEAD_MESSAGE_KEY));
            } else {
                ResponseBody body = httpException.response().errorBody();
                Converter<ResponseBody, ApiError> responseBodyObjectConverter
                        = retrofit.responseBodyConverter(ApiError.class, new Annotation[0]);
                try {
                    ApiError error = responseBodyObjectConverter.convert(body);
                    if (error != null) {
                        apiError.setMessage(error.getMessage());
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    apiError.setMessage(httpException.message());
                }
            }
        } else if (e instanceof UncaughtHttpException) {
            apiError = new ApiError();
            UncaughtHttpException httpException = (UncaughtHttpException) e;
            ResponseBody body = httpException.response().errorBody();
            Converter<ResponseBody, ApiError> responseBodyObjectConverter
                    = retrofit.responseBodyConverter(ApiError.class, new Annotation[0]);
            try {
                ApiError error = responseBodyObjectConverter.convert(body);
                if (error != null) {
                    apiError.setMessage(error.getMessage());
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                apiError.setMessage(httpException.message());
            }
        } else if (e instanceof ConnectException) {
            apiError = new ApiError(Constants.FAIL_CONNECT_CODE, Constants.FAIL_CONNECT_MESSAGE);
        } else if (e instanceof JsonSyntaxException) {
            apiError = new ApiError(Constants.JSON_PARSER_CODE, e.getMessage());
        } else {
            apiError = new ApiError(Constants.OTHER_CODE, e.getMessage());
        }
        return apiError;
    }
}
