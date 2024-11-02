package com.menyala.sipm.repository;

import com.menyala.sipm.model.BackOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JadwalPengecekanInfrastrukturRepo extends JpaRepository<BackOrder, UUID> {
}
