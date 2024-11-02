package com.menyala.sipm.dto.Toko;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class AddTokoDTO {

    private UUID id;  // Optionally, you might want to set the ID when creating a new Toko.
    private String namaToko;
    private String alamatToko;
    private String nikPenjual;
    private String namaPenjual;
    private String kontakPenjual;
    private UUID idPasar;  // Reference to the Pasar entity
    private List<UUID> listIdBarangPokok;  // Assuming this will be used to link to BarangPokok entities
    // Add any other relevant fields if necessary

}
