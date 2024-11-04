package com.menyala.sipm.repository;

import com.menyala.sipm.model.Toko;
import com.menyala.sipm.model.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransaksiRepo extends JpaRepository<Transaksi, UUID> {
    List<Transaksi> findAllByToko(Toko toko);
}
