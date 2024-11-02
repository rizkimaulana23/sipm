package com.menyala.sipm.service;

import com.menyala.sipm.dto.Toko.AddBarangPokokDTO;
import com.menyala.sipm.dto.Toko.AddJenisBarangDTO;
import com.menyala.sipm.dto.Toko.AddMaintenanceDTO;
import com.menyala.sipm.dto.infrastruktur.AddInfrastrukturDTO;
import com.menyala.sipm.dto.infrastruktur.AddMaintenanceInfrastrukturDTO;
import com.menyala.sipm.dto.infrastruktur.AddPengecekanInfrastrukturDTO;
import com.menyala.sipm.model.*;

import java.util.List;

public interface TokoService {
    List<String> getJenisBp();

    BarangPokok create(AddBarangPokokDTO dto);
    JadwalMaintenanceToko addMaintenance(AddMaintenanceDTO dto);
    JenisBarang addjenisBarang(AddJenisBarangDTO dto);













}
