package com.menyala.sipm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    private Infrastruktur infrastruktur;
}
