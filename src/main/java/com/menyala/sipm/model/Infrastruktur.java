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
public class Infrastruktur {

    @Id
    private UUID id;

    private String namaInfrastruktur;

    private String penanggungJawab;

    private String jenis;

    private Date tanggalPembangunan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pasar", referencedColumnName = "id")
    private Pasar pasar;

    @OneToMany(mappedBy = "infrastruktur", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<JadwalMaintenanceInfrastruktur> listJadwalMaintenanceInfrastruktur;

    @OneToMany(mappedBy = "infrastruktur", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<JadwalPengecekanInfrastruktur> listJadwalPengecekanInfrastruktur;
}
