package com.app.relationship.domain.entity;

import com.app.relationship.domain.entity.id.OrderId;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity @Table(name = "TBL_ORDER")
@SequenceGenerator(
        name = "SEQ_ORDER_GENERATOR",
        sequenceName = "SEQ_ORDER"
)
@IdClass(OrderId.class)
public class Order {
    @Id @EqualsAndHashCode.Include
    @GeneratedValue(generator = "SEQ_ORDER_GENERATOR")
    private Long id;
    private Integer amount;

//    FK
    @Id @ManyToOne @JoinColumn(name = "MEMBER_ID")
    private Member member;
    @Id @ManyToOne @JoinColumn(name = "PRODUCT_ID")
    private Product product;
}
