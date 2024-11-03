package com.menyala.sipm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Toko {
    @Id
    private UUID id;

    private String namaToko;

    private String alamatToko;

    private String nikPenjual;

    private String kontakPenjual;

    @OneToMany(mappedBy = "toko", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BackOrder> listBackOrder;

    @ManyToMany(mappedBy = "listToko", fetch = FetchType.LAZY)
    private List<BarangPokok> listBarangPokok;

    @OneToMany(mappedBy = "toko", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<JadwalMaintenanceToko> listJadwalMaintenanceToko;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pasar", referencedColumnName = "id")
    private Pasar pasar;

    @OneToMany(mappedBy = "toko", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Transaksi> listTransaksi;
}
