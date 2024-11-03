package com.menyala.sipm.dto.Toko;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public class AddTransaksiDTO {
    private UUID idToko;
    private Long pendapatanHarian;
    private Date tanggalTransaksi;

}

