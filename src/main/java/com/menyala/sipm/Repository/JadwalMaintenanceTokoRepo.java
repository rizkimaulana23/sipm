package com.menyala.sipm.Repository;

import com.menyala.sipm.model.BackOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JadwalMaintenanceTokoRepo extends JpaRepository<BackOrder, UUID> {
}
