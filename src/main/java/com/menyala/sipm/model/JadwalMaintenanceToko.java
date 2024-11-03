package com.menyala.sipm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JadwalMaintenanceToko {
    @Id
    private UUID id;

    @NotNull
    private Date tanggal;

    @NotNull
    private String deskripsi;

    @NotNull
    private String pihakMaintenance;

    @NotNull
    private Long biayaMaintenance;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_toko", referencedColumnName = "id")
    private Toko toko;
}
