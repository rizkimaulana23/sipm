package com.menyala.sipm.dto.infrastruktur;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public class AddPengecekanInfrastrukturDTO {
    private UUID infrastrukturID;
    private Date tanggal;
    private Long biaya;
    private String pelakuPengecekan;
}
