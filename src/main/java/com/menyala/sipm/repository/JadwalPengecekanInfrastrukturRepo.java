package com.menyala.sipm.repository;

import com.menyala.sipm.model.BackOrder;
import com.menyala.sipm.model.JadwalPengecekanInfrastruktur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JadwalPengecekanInfrastrukturRepo extends JpaRepository<JadwalPengecekanInfrastruktur, UUID> {
}
