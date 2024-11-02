package com.menyala.sipm.dto.Toko;


import com.menyala.sipm.model.Toko;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class AddBarangPokokDTO {
    private List<UUID> listIdToko;
    private String jenis;
    private String namaBarang;
    private String idJenisBarang;
    private Integer totalPenjual;
    private Integer totalStok;
    private Date tanggalKadaluarsa;
}
