package com.app.relationship.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter
@Setter
@ToString @EqualsAndHashCode
@Entity @Table(name = "TBL_BOARD")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String boardTitle;
    private String boardContent;
//    private User userId;
}
