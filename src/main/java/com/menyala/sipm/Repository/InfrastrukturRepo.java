package com.menyala.sipm.repository;

import com.menyala.sipm.model.Infrastruktur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InfrastrukturRepo extends JpaRepository<Infrastruktur, UUID> {
}
