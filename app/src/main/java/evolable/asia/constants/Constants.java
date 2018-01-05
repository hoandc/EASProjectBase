package evolable.asia.constants;

/*
 * ******************************************************************************
 *  Copyright Ⓒ 2017. All rights reserved
 *  Author HoanDC. Create on 1/3/2018.
 * ******************************************************************************
 */
public interface Constants {
    // Database
    long DATABASE_VERSION = 0;

    //Net
    int TIMEOUT = 15;
    String HTTP_API_VERSION_HEADER_KEY = "HTTP-X-API-VERSION";
    String UER_AGENT_HEADER_KEY = "User-Agent";
    String UER_AGENT_HEADER_VALUE = "Android";
    String CONNECTION = "Connection";
    String CLOSE = "close";

    int FAIL_CONNECT_CODE = -1;
    int JSON_PARSER_CODE = -10;
    int OTHER_CODE = -20;

    // HTTP Code Status
    int HTTP_AUTHENTICATION = 401;



    // message default
    String FAIL_CONNECT_MESSAGE = "The application can't connect to server";

}
