package com.menyala.sipm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "toko")
public class Toko {
    @Id
    private UUID id;

    private String namaToko;

    private String alamatToko;

    private String nikPenjual;

    private String kontakPenjual;

    @OneToMany(mappedBy = "toko", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BackOrder> listBackOrder;

    @OneToMany(mappedBy = "toko", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BarangPokok> listBarangPokok;

    @OneToMany(mappedBy = "toko", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<JadwalMaintenanceToko> listJadwalMaintenanceToko;
}
