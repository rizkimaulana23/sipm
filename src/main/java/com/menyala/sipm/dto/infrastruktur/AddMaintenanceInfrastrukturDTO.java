package com.menyala.sipm.dto.infrastruktur;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public class AddMaintenanceInfrastrukturDTO {
    private UUID infrastrukturID;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tanggal;
    private String deskripsi;
    private String pelakuMaintenance;
    private Long biaya;
}
