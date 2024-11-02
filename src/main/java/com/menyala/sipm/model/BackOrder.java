package com.menyala.sipm.model;

import jakarta.persistence.*;
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



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pasar", referencedColumnName = "id")
    private Pasar pasar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_toko", referencedColumnName = "id")
    private Toko toko;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jenis", referencedColumnName = "id")
    private JenisBarang jenisBarang;

}
