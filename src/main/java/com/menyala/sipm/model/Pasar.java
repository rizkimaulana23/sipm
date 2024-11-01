package com.menyala.sipm.model;

import jakarta.persistence.*;
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

    @OneToMany(mappedBy = "pasar", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Infrastruktur> listInfrastruktur;

    @OneToMany(mappedBy = "pasar", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BackOrder> listBackOrder;
}
