package com.menyala.sipm.service;

import com.menyala.sipm.dto.BarangPokok.AddBarangPokokDTO;
import com.menyala.sipm.dto.BarangPokok.AddJenisBarangDTO;
import com.menyala.sipm.dto.Toko.AddMaintenanceDTO;
import com.menyala.sipm.model.*;
import com.menyala.sipm.repository.BarangPokokRepo;
import com.menyala.sipm.repository.JenisBarangRepo;
import com.menyala.sipm.repository.TokoRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BarangPokokImpl implements BarangPokokService  {

    @Autowired
    BarangPokok barangPokok;

    @Autowired
    BarangPokokRepo barangPokokRepo;

    @Autowired
    Toko toko;

    @Autowired
    TokoRepo tokoRepo;

    @Autowired
    JenisBarang jenisBarang;

    @Autowired
    JenisBarangRepo jenisBarangRepo;


    @Override
    public List<String> getJenisBp() {
        return List.of(
                "beras",
                "kedelai",
                "cabe",
                "bawang-merah",
                "Gula",
                "Minyak-Goreng",
                "Tepung-Terigu",
                "Daging-Sapi",
                "Daging-Ayam-Ras",
                "Telur-Ayam-Ras",
                "Ikan-Segar"
        );
    }

    @Override
    public BarangPokok create(AddBarangPokokDTO dto) {
        BarangPokok barangPokok = new BarangPokok();
        barangPokok.setId(UUID.randomUUID());  // Generate a new ID
        barangPokok.setNama(dto.getNama());
        barangPokok.setStok(dto.getStok());
        barangPokok.setTotalPenjual(dto.getTotalPenjual()); // Update the method to match DTO
        barangPokok.setTanggalKadaluarsa(dto.getTanggalKadaluwarsa());

        // Set JenisBarang if needed
        jenisBarang = jenisBarangRepo.findById(dto.getIdJenisBarang());

        barangPokok.setJenisBarang(jenisBarang);

        // Handle listToko
        List<Toko> listToko = new ArrayList<>();
        for (UUID tokoId : dto.getListIdToko()) {
            Toko toko = tokoRepo.findById(tokoId).orElseThrow(() ->
                    new IllegalArgumentException("Toko not found with ID: " + tokoId));
            listToko.add(toko);
        }
        barangPokok.setListToko(listToko);

        return barangPokokRepo.save(barangPokok); // Ensure the repo is correctly referenced
    }

}
