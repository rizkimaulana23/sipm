package com.menyala.sipm.repository;

import com.menyala.sipm.model.Pasar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PasarRepo extends JpaRepository<Pasar, UUID> {
    List<Pasar> findAllByKota(String kota);


}
