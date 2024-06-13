package com.rko.rxlocate.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Column(unique = true) @NotBlank
    private String phone;

    @NotBlank
    private int age;

    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER)
    private Set<Prescription> prescriptions;


}