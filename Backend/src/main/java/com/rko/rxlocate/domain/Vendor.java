package com.rko.rxlocate.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "vendors")
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String vendor;

    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL)
    private List<Drug> drugs;
}


