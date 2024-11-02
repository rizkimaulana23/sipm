package com.menyala.sipm.service;

import com.menyala.sipm.dto.BarangPokok.AddBackOrderDTO;
import com.menyala.sipm.dto.BarangPokok.AddBarangPokokDTO;
import com.menyala.sipm.model.*;
import com.menyala.sipm.repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    PasarRepo pasarRepo;

    @Autowired
    BackOrderRepo backOrderRepo;

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
        barangPokok.setId(UUID.randomUUID());
        barangPokok.setNama(dto.getNama());
        barangPokok.setStok(dto.getStok());
        barangPokok.setTotalPenjual(dto.getTotalPenjual());
        barangPokok.setTanggalKadaluarsa(dto.getTanggalKadaluwarsa());

        JenisBarang jenisBarang = jenisBarangRepo.findById(dto.getIdJenisBarang()).orElse(null);

        barangPokok.setJenisBarang(jenisBarang);

        List<Toko> listToko = new ArrayList<>();
        for (UUID tokoId : dto.getListIdToko()) {
            Toko toko = tokoRepo.findById(tokoId).orElseThrow(() ->
                    new IllegalArgumentException("Toko not found with ID: " + tokoId));
            listToko.add(toko);
        }
        barangPokok.setListToko(listToko);

        return barangPokokRepo.save(barangPokok);
    }

    @Override
    public BackOrder createBackOrder(AddBackOrderDTO dto) {
        BackOrder backOrder = new BackOrder();
        backOrder.setId(UUID.randomUUID());
        backOrder.setNama(dto.getNama());

        JenisBarang jenisBarang = jenisBarangRepo.findById(dto.getIdJenisBarang())
                .orElseThrow(() -> new IllegalArgumentException("JenisBarang not found with ID: " + dto.getIdJenisBarang()));
        backOrder.setJenisBarang(jenisBarang);

        Pasar pasar = pasarRepo.findById(dto.getIdPasar())
                .orElseThrow(() -> new IllegalArgumentException("Pasar not found with ID: " + dto.getIdPasar()));
        backOrder.setPasar(pasar);

        Toko toko = tokoRepo.findById(dto.getIdToko())
                .orElseThrow(() -> new IllegalArgumentException("Toko not found with ID: " + dto.getIdToko()));
        backOrder.setToko(toko);

        return backOrderRepo.save(backOrder);
    }






}
