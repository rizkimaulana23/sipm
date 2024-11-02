package com.menyala.sipm.dto.infrastruktur;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class AddInfrastrukturDTO {
    private String nama;
    private String penanggungJawab;
    private String jenis;
    private Date tanggalPembangunan;
    private Date tanggalTerakhirKaliPengecekan;
    private String foto;
}
