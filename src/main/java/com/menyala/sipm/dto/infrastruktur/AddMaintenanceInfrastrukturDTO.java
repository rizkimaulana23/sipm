package com.menyala.sipm.dto.infrastruktur;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public class AddMaintenanceInfrastrukturDTO {
    private UUID infrastrukturID;
    private Date tanggal;
    private String deskripsi;
    private String pelakuMaintenance;
    private Long biaya;
}
