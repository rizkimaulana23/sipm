package com.menyala.sipm.repository;

import com.menyala.sipm.model.BarangPokok;
import com.menyala.sipm.model.JenisBarang;
import com.menyala.sipm.model.Pasar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface BarangPokokRepo extends JpaRepository<BarangPokok, UUID> {
    List<BarangPokok> findAllByJenisBarang(JenisBarang jenis);
    List<BarangPokok> findAllByJenisBarangAndTanggalMasukAfter(JenisBarang jenisBarang, Date tanggalMasuk);
    List<BarangPokok> findAllByJenisBarangAndTanggalMasukBetween(JenisBarang jenisBarang, Date min, Date max);

    @Modifying
    @Query(value = "INSERT INTO BarangPokok (nama, stok, id_jenis_barang, tanggal_kadaluwarsa, tanggal_masuk, pasar_id) " +
            "VALUES (:nama, :stok, :idJenisBarang, :tanggalKadaluwarsa, :tanggalMasuk, :pasarId)",
            nativeQuery = true)
    int insertBarang(
            @Param("nama") String nama,
            @Param("stok") Integer stok,
            @Param("idJenisBarang") String idJenisBarang,
            @Param("tanggalKadaluwarsa") Date tanggalKadaluwarsa,
            @Param("tanggalMasuk") Date tanggalMasuk,
            @Param("pasarId") Long pasarId
    );

}
