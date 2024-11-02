package com.menyala.sipm.service;

import com.menyala.sipm.dto.BarangPokok.AddBarangPokokDTO;
import com.menyala.sipm.dto.BarangPokok.AddJenisBarangDTO;
import com.menyala.sipm.dto.Toko.AddMaintenanceDTO;
import com.menyala.sipm.dto.Toko.AddTokoDTO;
import com.menyala.sipm.model.*;

import java.util.List;

public interface TokoService {

    Toko create(AddTokoDTO dto);
    JadwalMaintenanceToko addMaintenance(AddMaintenanceDTO dto);














}
