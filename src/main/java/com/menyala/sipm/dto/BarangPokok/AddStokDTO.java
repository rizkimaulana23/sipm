package com.menyala.sipm.dto.BarangPokok;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class AddStokDTO {
    private UUID IdToko;      // To link BarangPokok to multiple Toko
    private String nama;                 // This should match the field name in BarangPokok
    private Integer stok;                // Total stock of the item
    private Integer totalPenjual;        // Total sellers for this item
    private Date tanggalKadaluwarsa;            // Expiry date of the item
    private Date tanggalMasuk;
    private String idJenisBarang;
}
