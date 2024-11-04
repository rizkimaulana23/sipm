package com.menyala.sipm.repository;

import com.menyala.sipm.model.BarangPokok;
import com.menyala.sipm.model.JenisBarang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BarangPokokRepo extends JpaRepository<BarangPokok, UUID> {
    List<BarangPokok> findAllByJenisBarang(JenisBarang jenis);
}
