package com.menyala.sipm.Repository;

import com.menyala.sipm.model.BackOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JenisBarangRepo extends JpaRepository<BackOrder, String> {
}