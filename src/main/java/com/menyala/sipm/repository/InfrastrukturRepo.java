package com.menyala.sipm.repository;

import com.menyala.sipm.model.Infrastruktur;
import com.menyala.sipm.model.Pasar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InfrastrukturRepo extends JpaRepository<Infrastruktur, UUID> {
    List<Infrastruktur> findAllByPasar(Pasar pasar);
}
