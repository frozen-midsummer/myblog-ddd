package com.wjx.myblog.domain.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MyblogErrorCodeEnum {
    RECORD_NOT_FOUND(2000),
    RECORD_DUPLICATE(20001),
    INCORRECT_CREDENTIAL(20002),
    EMPTY_TOKEN(20003),
    ;

    private final int code;

    public int code() {
        return this.code;
    }
}
