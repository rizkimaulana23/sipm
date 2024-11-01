package com.menyala.sipm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BarangPokok {
    @Id
    private UUID id;

    private String nama;

    private Integer stok;

    private Date kedaluwarsa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_toko", referencedColumnName = "id")
    private Toko toko;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_jenis_barang", referencedColumnName = "jenis")
    private JenisBarang jenisBarang;
}
