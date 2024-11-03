package com.menyala.sipm.repository;

import com.menyala.sipm.model.JadwalMaintenanceToko;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JadwalMaintenanceTokoRepo extends JpaRepository<JadwalMaintenanceToko, UUID> {
}
