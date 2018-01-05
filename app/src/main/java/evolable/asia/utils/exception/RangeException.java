/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. All rights reserved
 *  Author TrinhQuan. Create on 2016/8/26
 * ******************************************************************************
 */

package evolable.asia.utils.exception;

public class RangeException extends RuntimeException {
    public RangeException() {
    }

    public RangeException(String detailMessage) {
        super(detailMessage);
    }

    public RangeException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public RangeException(Throwable throwable) {
        this("Invalid range", throwable);
    }
}
