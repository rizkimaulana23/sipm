package com.menyala.sipm.service;

import com.menyala.sipm.dto.BarangPokok.AddBarangPokokDTO;
import com.menyala.sipm.dto.BarangPokok.AddJenisBarangDTO;
import com.menyala.sipm.dto.Toko.AddMaintenanceDTO;
import com.menyala.sipm.dto.Toko.AddTokoDTO;
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
    public JadwalMaintenanceToko addMaintenance(AddMaintenanceDTO dto){

    };

    @Override
    public JenisBarang addJenisBarang(AddJenisBarangDTO dto){

    };








}
