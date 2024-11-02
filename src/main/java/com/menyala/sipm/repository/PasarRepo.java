package com.menyala.sipm.repository;

import com.menyala.sipm.model.BackOrder;
import com.menyala.sipm.model.Pasar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PasarRepo extends JpaRepository<Pasar, UUID> {
}
