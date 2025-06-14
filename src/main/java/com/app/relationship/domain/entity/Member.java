package com.app.relationship.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SequenceGenerator(
        name = "SEQ_MEMBER_GENERATOR",
        sequenceName = "SEQ_MEMBER"
)
@Entity @Table(name = "TBL_MEMBER")
public class Member {

    @GeneratedValue(
            generator = "SEQ_MEMBER_GENERATOR",
            strategy = GenerationType.SEQUENCE
    )
    @Id @EqualsAndHashCode.Include
    private Long id;
    private String memberName;
    private String memberAddress;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<Order>();
}
