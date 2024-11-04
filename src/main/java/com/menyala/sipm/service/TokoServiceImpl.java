package com.menyala.sipm.service;

import com.menyala.sipm.dto.Toko.AddMaintenanceDTO;
import com.menyala.sipm.dto.Toko.AddTokoDTO;
import com.menyala.sipm.dto.Toko.AddTransaksiDTO;
import com.menyala.sipm.model.JadwalMaintenanceToko;
import com.menyala.sipm.model.Pasar;
import com.menyala.sipm.model.Toko;
import com.menyala.sipm.model.Transaksi;
import com.menyala.sipm.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokoServiceImpl implements TokoService{

    @Autowired
    private TokoRepo tokoRepo;

    @Autowired
    private PasarRepo pasarRepo;

    @Autowired
    private JadwalMaintenanceTokoRepo jadwalMaintenanceRepo;

    @Autowired
    private BarangPokokRepo barangPokokRepo;

    @Autowired
    private TransaksiRepo transaksiRepo;

    @Autowired
    private JenisBarangRepo jenisBarangRepo;


    @Override
    public Toko createToko(AddTokoDTO dto) {
        Toko toko = new Toko();
        toko.setId(UUID.randomUUID());
        toko.setNamaToko(dto.getNamaToko());
        toko.setAlamatToko(dto.getAlamatToko());
        toko.setNikPenjual(dto.getNikPenjual());
        toko.setKontakPenjual(dto.getKontakPenjual());

        Pasar pasar = pasarRepo.findById(dto.getIdPasar()).orElseThrow(() ->
                new IllegalArgumentException("Pasar not found with ID: " + dto.getIdPasar()));
        toko.setPasar(pasar);

//        pasar.getListToko().add(toko);

        return tokoRepo.save(toko);
    }

    @Override
    public Transaksi createTransaksi(AddTransaksiDTO dto){
        Transaksi transaksi = new Transaksi();
        transaksi.setIdTransaksi(UUID.randomUUID());
        transaksi.setPendapatanHarian(dto.getPendapatanHarian());
        transaksi.setToko(tokoRepo.findById(dto.getIdToko()).orElse(null));
        transaksi.setPendapatanHarian(dto.getPendapatanHarian());
        transaksi.setTanggalTransaksi(dto.getTanggalTransaksi());
        return transaksiRepo.save(transaksi);
    };

    @Override
    public JadwalMaintenanceToko addMaintenance(AddMaintenanceDTO dto){
        JadwalMaintenanceToko jadwalMaintenanceToko = new JadwalMaintenanceToko();
        jadwalMaintenanceToko.setId(UUID.randomUUID());
        jadwalMaintenanceToko.setDeskripsi(dto.getDeskripsiMaintenance());
        jadwalMaintenanceToko.setPihakMaintenance(dto.getPelakuMaintenance());
        jadwalMaintenanceToko.setBiayaMaintenance(dto.getBiayaMaintenance());
        jadwalMaintenanceToko.setTanggal(dto.getTanggal());

        Toko toko = tokoRepo.findById(dto.getIdToko()).orElse(null);
        jadwalMaintenanceToko.setToko(toko);
//        if (toko.getListJadwalMaintenanceToko() == null) {
//            toko.setListJadwalMaintenanceToko(new ArrayList<>());
//        }
//
//        toko.getListJadwalMaintenanceToko().add(jadwalMaintenanceToko);
        return jadwalMaintenanceRepo.save(jadwalMaintenanceToko);
    };
}
