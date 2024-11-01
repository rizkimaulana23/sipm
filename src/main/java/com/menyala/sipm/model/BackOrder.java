package com.menyala.sipm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BackOrder {
    @Id
    private UUID id;
    private String nama;
    private String jenisBarang;
    private Integer kuantitas;
    @ManyToOne
    private Pasar pasar;
    @ManyToOne
    private Toko toko;
}
