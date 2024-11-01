package com.menyala.sipm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Toko {
    @Id
    private UUID id;

    private String namaToko;

    private String alamatToko;

    private String nikPenjual;

    private String kontakPenjual;

    @OneToMany
    private List<BackOrder> listBackOrder;

    @OneToMany
    private List<BarangPokok> listBarangPokok;
}
