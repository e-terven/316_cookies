package com.katia.spring.cookie.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter

public class User {

    private Long id;
    private String name;
    private String lastName;
    private Byte age;
}
