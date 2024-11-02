package com.menyala.sipm.service;

import com.menyala.sipm.dto.infrastruktur.AddInfrastrukturDTO;
import com.menyala.sipm.dto.infrastruktur.AddMaintenanceDTO;
import com.menyala.sipm.dto.infrastruktur.AddPengecekanInfrastrukturDTO;
import com.menyala.sipm.model.Infrastruktur;
import com.menyala.sipm.model.JadwalMaintenanceInfrastruktur;
import com.menyala.sipm.model.JadwalPengecekanInfrastruktur;

public interface InfrastrukturService {
    Infrastruktur create(AddInfrastrukturDTO dto);
    JadwalMaintenanceInfrastruktur addMaintenance(AddMaintenanceDTO dto);
    JadwalPengecekanInfrastruktur addPengecekan(AddPengecekanInfrastrukturDTO dto);
}
