package com.menyala.sipm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "jenis_barang")
public class JenisBarang {

    @Id
    private String jenis;

    @OneToMany(mappedBy = "jenisBarang", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BarangPokok> listBarangPokok;

    @OneToMany(mappedBy = "jenisBarang", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BackOrder> listBackOrder;
}
