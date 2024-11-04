package com.menyala.sipm.repository;

import com.menyala.sipm.model.Pasar;
import com.menyala.sipm.model.Toko;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TokoRepo extends JpaRepository<Toko, UUID> {
    List<Toko> findAllByPasar(Pasar pasar);
}
