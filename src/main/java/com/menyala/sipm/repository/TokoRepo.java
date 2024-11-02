package com.menyala.sipm.repository;

import com.menyala.sipm.model.Toko;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TokoRepo extends JpaRepository<Toko, UUID> {
}
