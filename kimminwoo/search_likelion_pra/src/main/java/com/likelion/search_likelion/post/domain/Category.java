package com.likelion.search_likelion.post.domain;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.likelion.search_likelion.exception.CustomException;
import com.likelion.search_likelion.exception.status.ErrorStatus;

public enum Category {
    CAFE("카페"),
    RESTARANT("음식점"),
    SUPERMARKET("마트");

    private final String label;

    Category(String label) { //enum의 생성자 역할, 상수의 라벨이 적용
        this.label = label;
    }

    @JsonCreator
    public static Category from(String input) {
        // input이 nill이거나 빈 문자열이면 nill 반환
        if (input == null || input.trim().isEmpty()) {
            return null;
        }
        // input 문자열 양 끝에 있는 공백을 제거한 새로운 문자열 저장
        String trimmed = input.trim();

        for (Category c : values()) {
            if(c.name().equalsIgnoreCase(trimmed) || c.label.equalsIgnoreCase(trimmed)) {
                return c;
            } //Enum상수의 이름과 trimmed 문자열을 대소문자 구분없이 비교
        }
        // 잘못된 입력은 예외로 처리
        throw new CustomException(ErrorStatus.INVALID_PARAMETER,
                ErrorStatus.INVALID_PARAMETER.getCode());
    }
    // 한글 라벨로 응답
    @JsonValue
    public String toJson() {
        return this.label;
    }
}