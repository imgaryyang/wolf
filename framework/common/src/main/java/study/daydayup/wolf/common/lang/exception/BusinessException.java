package study.daydayup.wolf.common.lang.exception;

import lombok.Getter;

public class BusinessException extends BaseException {

    public BusinessException(String message) {
        super(1000, message);
    }

    public BusinessException(long code, String message) {
        super(code, message);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}