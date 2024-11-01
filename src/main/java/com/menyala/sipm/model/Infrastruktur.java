package com.menyala.sipm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Infrastruktur {

    @Id
    private UUID id;

    private String namaInfrastruktur;

    private String penanggungJawab;

    private String jenis;

    private Date tanggalPembangunan;

    @ManyToOne
    private Toko toko;

    @OneToMany
    private List<JadwalMaintenanceInfrastruktur> listJadwalMaintenanceInfrastruktur;

    @OneToMany
    private List<JadwalPengecekanInfrastruktur> listJadwalPengecekanInfrastruktur;
}
