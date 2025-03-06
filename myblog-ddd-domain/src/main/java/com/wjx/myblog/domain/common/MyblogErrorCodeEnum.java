package com.wjx.myblog.domain.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MyblogErrorCodeEnum {
    USER_NOT_FOUND(2000),
    ;

    private final int code;

    public int code() {
        return this.code;
    }
}
