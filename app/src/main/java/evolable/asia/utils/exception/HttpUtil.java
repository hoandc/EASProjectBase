package evolable.asia.utils.exception;

import android.text.TextUtils;

import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.ConnectException;
import java.net.UnknownHostException;

import evolable.asia.constants.Constants;
import evolable.asia.models.entities.ApiError;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.HttpException;
import retrofit2.Retrofit;

public class HttpUtil {

    private final static String HEAD_MESSAGE_KEY = "Message";

    public static ApiError getError(Throwable throwable, Retrofit retrofit) {
        ApiError apiError = null;
        if (throwable instanceof HttpException) {
            apiError = new ApiError();
            HttpException httpException = (HttpException) throwable;
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
        } else if (throwable instanceof UncaughtHttpException) {
            apiError = new ApiError();
            UncaughtHttpException httpException = (UncaughtHttpException) throwable;
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
        } else if (throwable instanceof ConnectException) {
            apiError = new ApiError(Constants.FAIL_CONNECT_CODE, Constants.FAIL_CONNECT_MESSAGE);
        } else if (throwable instanceof JsonSyntaxException) {
            apiError = new ApiError(Constants.JSON_PARSER_CODE, throwable.getMessage());
        } else if (throwable instanceof UnknownHostException) {
            apiError = new ApiError(Constants.HTTP_INTERNET_CONNECTION, Constants.FAIL_CONNECT_MESSAGE);
        } else {
            apiError = new ApiError(Constants.OTHER_CODE, throwable.getMessage());
        }
        return apiError;
    }
}
