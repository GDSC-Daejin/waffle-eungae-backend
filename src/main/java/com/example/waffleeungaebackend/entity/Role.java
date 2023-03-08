package com.example.waffleeungaebackend.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    GUEST("ROLE_GUEST,", "게스트"),
    USER("ROLE_USER", "사용자");
    //관리잔는 추후에 생성하도록 예정

    private final String key;
    private final String title;
}
