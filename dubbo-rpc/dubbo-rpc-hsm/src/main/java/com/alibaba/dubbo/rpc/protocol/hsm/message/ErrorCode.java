package com.alibaba.dubbo.rpc.protocol.hsm.message;

public enum ErrorCode {

    NOERROR("00");

    private String code;

    ErrorCode(final String code) {
        this.code = code;
    }

    public String getErrorCode() {
        return this.code;
    }

    public static ErrorCode create(final String i) {

        for (final ErrorCode errorCode : ErrorCode.values()) {
            if (errorCode.code.equals(i)) {
                return errorCode;
            }
        }
        throw new IllegalArgumentException("Invalid Error Code: " + i);
    }

    public String getErrorMessage(final String errorCode) {
        return null;
    }
}
