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
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pasar {

    @Id
    private UUID id;

    private String nama;

    private Long retribusi;

    private String alamat;

    @OneToMany
    private List<Infrastruktur> listInfrastruktur;

    @OneToMany
    private List<BackOrder> listBackOrder;
}
