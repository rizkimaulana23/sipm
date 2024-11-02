package com.menyala.sipm.service;

import com.menyala.sipm.dto.infrastruktur.AddInfrastrukturDTO;
import com.menyala.sipm.dto.infrastruktur.AddMaintenanceDTO;
import com.menyala.sipm.dto.infrastruktur.AddPengecekanInfrastrukturDTO;
import com.menyala.sipm.model.Infrastruktur;
import com.menyala.sipm.model.JadwalMaintenanceInfrastruktur;
import com.menyala.sipm.model.JadwalPengecekanInfrastruktur;
import com.menyala.sipm.repository.InfrastrukturRepo;
import com.menyala.sipm.repository.JadwalMaintenanceInfrastrukturRepo;
import com.menyala.sipm.repository.JadwalPengecekanInfrastrukturRepo;
import com.menyala.sipm.repository.PasarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InfrastrukturServiceImpl implements InfrastrukturService {

    @Autowired
    private InfrastrukturRepo infrastrukturRepo;

    @Autowired
    private PasarRepo pasarRepo;

    @Autowired
    private JadwalMaintenanceInfrastrukturRepo jadwalMaintenanceInfrastrukturRepo;

    @Autowired
    private JadwalPengecekanInfrastrukturRepo jadwalPengecekanInfrastrukturRepo;

    @Override
    public List<String> getJenis() {
        return List.of(
                "kantor-pengelola",
                "ruang-serbaguna",
                "toilet",
                "tempat-ibadah",
                "papan-informasi",
                "pos-ukur-ulang",
                "sarana-kesehatan",
                "pos-keamanan",
                "drainease",
                "akses-transportasi",
                "tps-sementara",
                "gudang",
                "area-bongkar-muat",
                "tempat-parkir",
                "area-penghijauan",
                "hidran",
                "instalasi-air-listrik",
                "telokomunikasi",
                "si-harga-stok",
                "papan-harga-harian"
        );
    }

    @Override
    public Infrastruktur create(AddInfrastrukturDTO dto) {
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
    public JadwalMaintenanceInfrastruktur addMaintenance(AddMaintenanceDTO dto) {
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
    public JadwalPengecekanInfrastruktur addPengecekan(AddPengecekanInfrastrukturDTO dto) {
        JadwalPengecekanInfrastruktur jadwal = new JadwalPengecekanInfrastruktur();
        jadwal.setId(UUID.randomUUID());
        jadwal.setInfrastruktur(infrastrukturRepo.findById(dto.getInfrastrukturID()).orElse(null));
        jadwal.setTanggal(dto.getTanggal());
        jadwal.setBiaya(dto.getBiaya());
        jadwal.setPelakuPengecekan(dto.getPelakuPengecekan());
        return jadwalPengecekanInfrastrukturRepo.save(jadwal);
    }
}
