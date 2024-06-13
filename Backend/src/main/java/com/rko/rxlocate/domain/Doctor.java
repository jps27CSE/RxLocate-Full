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
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @NotBlank
    private String name;

    @Column(name = "bmdc", unique = true)
    @NotBlank(message = "BMDC no is mandatory")
    private String bmdcId;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER)
    private Set<Prescription> prescriptions;


}