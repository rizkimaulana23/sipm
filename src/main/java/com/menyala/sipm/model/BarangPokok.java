package com.menyala.sipm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
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
    private Integer totalPenjual;

    private Date tanggalKadaluarsa;

    @ManyToMany
    @JoinTable(
            name = "barangDiToko",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_toko")
    )
    List<Toko> listToko;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_jenis_barang", referencedColumnName = "jenis")
    private JenisBarang jenisBarang;
}
