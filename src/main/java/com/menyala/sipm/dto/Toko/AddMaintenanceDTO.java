package com.menyala.sipm.dto.Toko;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public class AddMaintenanceDTO {
    private UUID idToko;
    private Date tanggal;
    private String deskripsiMaintenance;
    private String pelakuMaintenance;
}
