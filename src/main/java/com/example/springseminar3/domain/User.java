package com.example.springseminar3.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 50, message = "Имя должно состоять из символов от 2 до 50!")
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email")
    private String email;

    @Min(value = 18, message = "Минимальный возраст 18 лет!")
    @Max(value = 100, message = "Максимальный возраст 100 лет!")
    @Column(name = "age", nullable = false)
    private int age;
}
