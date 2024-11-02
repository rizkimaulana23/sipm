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
    private List<UUID> listIdToko;      // To link BarangPokok to multiple Toko
    private String nama;                 // This should match the field name in BarangPokok
    private Integer stok;                // Total stock of the item
    private Integer totalPenjual;        // Total sellers for this item
    private Date tanggalKadaluwarsa;            // Expiry date of the item
    private String idJenisBarang;
}
