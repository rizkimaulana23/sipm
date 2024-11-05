package com.menyala.sipm.repository;

import com.menyala.sipm.model.BackOrder;
import com.menyala.sipm.model.Pasar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BackOrderRepo extends JpaRepository<BackOrder, UUID> {
    List<BackOrder> findAllByPasar(Pasar pasar);

}
