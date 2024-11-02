package com.menyala.sipm.service;

import com.menyala.sipm.dto.Toko.AddBarangPokokDTO;
import com.menyala.sipm.dto.Toko.AddJenisBarangDTO;
import com.menyala.sipm.dto.Toko.AddMaintenanceDTO;
import com.menyala.sipm.dto.Toko.AddTokoDTO;
import com.menyala.sipm.dto.infrastruktur.AddMaintenanceInfrastrukturDTO;
import com.menyala.sipm.dto.infrastruktur.AddPengecekanInfrastrukturDTO;
import com.menyala.sipm.model.*;
import com.menyala.sipm.repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TokoServiceImpl implements TokoService{

    @Autowired
    private TokoRepo tokoRepo;

    @Autowired
    private PasarRepo pasarRepo;

    @Autowired
    private JadwalMaintenanceTokoRepo jadwalMaintenanceInfrastrukturRepo;

    @Autowired
    private BarangPokok barangPokok;

    @Autowired
    private JenisBarang jenisBarang;

    @Autowired
    private BarangPokokRepo barangPokokRepo;


    @Autowired
    private TransaksiRepo transaksiRepo;

    @Autowired
    private JenisBarangRepo jenisBarangRepo;

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
    public Toko create(AddTokoDTO dto) {
        Toko toko = new Toko();
        toko.setId(UUID.randomUUID());  // Generate a new ID
        toko.setNamaToko(dto.getNamaToko());
        toko.setAlamatToko(dto.getAlamatToko());
        toko.setNikPenjual(dto.getNikPenjual());
        toko.setKontakPenjual(dto.getKontakPenjual());

        // Set Pasar entity from the repository
        Pasar pasar = pasarRepo.findById(dto.getIdPasar()).orElseThrow(() ->
                new IllegalArgumentException("Pasar not found with ID: " + dto.getIdPasar()));
        toko.setPasar(pasar);

        // Handle listBarangPokok if needed (to be implemented)
        // You may need to convert listIdBarangPokok to a List<BarangPokok> and set it in Toko

        return tokoRepo.save(toko);
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
        JenisBarang jenisBarang = jenisBarangRepo.findById(dto.getIdJenisBarang());

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


    @Override
    public JadwalMaintenanceToko addMaintenance(AddMaintenanceDTO dto){

    };

    @Override
    public JenisBarang addJenisBarang(AddJenisBarangDTO dto){

    };








}
