package com.menyala.sipm.repository;

import com.menyala.sipm.model.BackOrder;
import com.menyala.sipm.model.JenisBarang;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JenisBarangRepo extends JpaRepository<JenisBarang, String> {
}
