package com.menyala.sipm.dto.Toko;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class AddTokoDTO {

    private List<UUID> listIdBarangPokok;
    private String namaToko;
    private String alamatToko;
    private String nikPenjual;
    private String namaPenjual;
    private String kontakPenjual;

}
