package com.menyala.sipm.Repository;

import com.menyala.sipm.model.BackOrder;
import com.menyala.sipm.model.Infrastruktur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InfrastrukturRepo extends JpaRepository<Infrastruktur, UUID> {
}
