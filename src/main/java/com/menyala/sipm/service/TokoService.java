package com.menyala.sipm.service;

import com.menyala.sipm.dto.BarangPokok.AddBarangPokokDTO;
import com.menyala.sipm.dto.BarangPokok.AddJenisBarangDTO;
import com.menyala.sipm.dto.Toko.AddMaintenanceDTO;
import com.menyala.sipm.dto.Toko.AddTokoDTO;
import com.menyala.sipm.dto.Toko.AddTransaksiDTO;
import com.menyala.sipm.model.*;

import java.util.List;

public interface TokoService {
    Transaksi createTransaksi(AddTransaksiDTO dto);
    Toko createToko(AddTokoDTO dto);
    JadwalMaintenanceToko addMaintenance(AddMaintenanceDTO dto);



}
