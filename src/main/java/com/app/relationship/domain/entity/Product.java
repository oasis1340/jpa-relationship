package com.app.relationship.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity @Table(name = "TBL_PRODUCT")
@SequenceGenerator(
        name = "SEQ_PRODUCT_GENERATOR",
        sequenceName = "SEQ_PRODUCT"
)
public class Product {

    @GeneratedValue(generator = "SEQ_PRODUCT_GENERATOR")
    @Id @EqualsAndHashCode.Include
    private Long id;
    private String productName;
    private Integer productPrice;
    private Integer productStock;

    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "product")
    private List<Order> orders = new ArrayList<Order>();
}
