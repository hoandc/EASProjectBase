package evolable.asia.models.entities;


import evolable.asia.constants.Constants;

public class ApiError {
    private int code = Constants.FAIL_CONNECT_CODE;
    private String message;

    public ApiError() {
    }

    public ApiError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
