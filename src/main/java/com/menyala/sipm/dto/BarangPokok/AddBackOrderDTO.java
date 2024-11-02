package com.menyala.sipm.dto.BarangPokok;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class AddBackOrderDTO {
    private UUID idToko;           // The ID of the Toko (store) associated with the back order
    private UUID idPasar;          // The ID of the Pasar (market) associated with the back order
    private String idJenisBarang;    // The ID of the JenisBarang (type of item) associated with the back order
    private String nama;
}
