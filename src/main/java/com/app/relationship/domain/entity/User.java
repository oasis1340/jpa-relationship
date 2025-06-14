package com.app.relationship.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter
@Setter
@ToString @EqualsAndHashCode
@Entity @Table(name = "TBL_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userEmail;
    private String userPassword;
    private String userName;
    private Integer userAge;
}
