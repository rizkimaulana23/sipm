package com.menyala.sipm.repository;

import com.menyala.sipm.model.BarangPokok;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BarangPokokRepo extends JpaRepository<BarangPokok, UUID> {
}
