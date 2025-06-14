package com.app.relationship.domain.entity.id;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor @AllArgsConstructor
@Getter
@Setter @ToString
@EqualsAndHashCode
public class OrderId implements Serializable {
//    1. 반드시 기본 생성자
//    2. 반드시 HashCode(), Equals()가 재정의 되어있어야한다.
//    3. Serializable 구현, 직렬화 인터페이스

    private Long member;
    private Long product;
}
