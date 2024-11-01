package com.menyala.sipm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class JenisBarang {

    @Id
    private String jenis;
}
