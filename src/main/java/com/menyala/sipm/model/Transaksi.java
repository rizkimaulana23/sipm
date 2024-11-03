package com.menyala.sipm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaksi {
    @Id
    private UUID idTransaksi;
    private Long pendapatanHarian;
    private Date tanggalTransaksi;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_toko", referencedColumnName = "id")
    private Toko toko;

}
