package com.menyala.sipm.service;

import com.menyala.sipm.dto.infrastruktur.*;
import com.menyala.sipm.model.Infrastruktur;
import com.menyala.sipm.model.JadwalMaintenanceInfrastruktur;
import com.menyala.sipm.model.JadwalPengecekanInfrastruktur;

import java.util.List;
import java.util.UUID;

public interface InfrastrukturService {
    List<String> getJenis();
    Infrastruktur create(AddInfrastrukturDTO dto);
    JadwalMaintenanceInfrastruktur addMaintenance(AddMaintenanceInfrastrukturDTO dto);
    JadwalPengecekanInfrastruktur addPengecekan(AddPengecekanInfrastrukturDTO dto);
    JadwalMaintenanceInfrastruktur updateMaintenance(UpdateMaintenanceInfrastrukturDTO dto);
    JadwalPengecekanInfrastruktur updatePengecekan(UpdatePengecekanInfrastrukturDTO dto);
    void deletePengecekan(UUID pengecekanID);
    void deleteMaintenance(UUID maintenanceID);
}
