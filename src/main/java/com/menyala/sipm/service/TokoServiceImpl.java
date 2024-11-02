package com.menyala.sipm.service;

import com.menyala.sipm.dto.Toko.AddBarangPokokDTO;
import com.menyala.sipm.dto.Toko.AddMaintenanceDTO;
import com.menyala.sipm.dto.Toko.AddTokoDTO;
import com.menyala.sipm.dto.infrastruktur.AddMaintenanceInfrastrukturDTO;
import com.menyala.sipm.dto.infrastruktur.AddPengecekanInfrastrukturDTO;
import com.menyala.sipm.model.*;
import com.menyala.sipm.repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class TokoServiceImpl implements TokoService{

    @Autowired
    private TokoRepo infrastrukturRepo;

    @Autowired
    private PasarRepo pasarRepo;

    @Autowired
    private JadwalMaintenanceTokoRepo jadwalMaintenanceInfrastrukturRepo;

    @Autowired
    private BarangPokok barangPokok;

    @Autowired
    private TransaksiRepo transaksiRepo;

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
    public Toko create(AddBarangPokokDTO dto) {
        Infrastruktur infrastruktur = new Infrastruktur();
        infrastruktur.setId(UUID.randomUUID());
        infrastruktur.setNamaInfrastruktur(dto.getNama());
        infrastruktur.setJenis(dto.getJenis());
        infrastruktur.setPenanggungJawab(dto.getPenanggungJawab());
        infrastruktur.setTanggalPembangunan(dto.getTanggalPembangunan());
        infrastruktur.setPasar(pasarRepo.findById(dto.getIdPasar()).orElse(null));
        return infrastrukturRepo.save(infrastruktur);
    }

    @Override
    public JadwalMaintenanceToko addMaintenance(AddMaintenanceDTO dto) {
        JadwalMaintenanceInfrastruktur jadwal = new JadwalMaintenanceInfrastruktur();
        jadwal.setId(UUID.randomUUID());
        jadwal.setInfrastruktur(infrastrukturRepo.findById(dto.getInfrastrukturID()).orElse(null));
        jadwal.setTanggalMaintenance(dto.getTanggal());
        jadwal.setPelakuMaintenance(dto.getPelakuMaintenance());
        jadwal.setBiaya(dto.getBiaya());
        jadwal.setDeskripsi(dto.getDeskripsi());
        return jadwalMaintenanceInfrastrukturRepo.save(jadwal);
    }

    @Override
    public tambahTokoDTO addToko(AddTokoDTO dto) {
        JadwalPengecekanInfrastruktur jadwal = new JadwalPengecekanInfrastruktur();
        jadwal.setId(UUID.randomUUID());
        jadwal.setInfrastruktur(infrastrukturRepo.findById(dto.getInfrastrukturID()).orElse(null));
        jadwal.setTanggal(dto.getTanggal());
        jadwal.setBiaya(dto.getBiaya());
        jadwal.setPelakuPengecekan(dto.getPelakuPengecekan());
        return jadwalPengecekanInfrastrukturRepo.save(jadwal);
    }
}
