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
public class JadwalMaintenanceInfrastruktur {
    @Id
    private UUID id;

    private Date tanggalMaintenance;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_infrastruktur", referencedColumnName = "id")
    private Infrastruktur infrastruktur;
}
