package com.menyala.sipm.service;

import com.menyala.sipm.dto.BarangPokok.AddBackOrderDTO;
import com.menyala.sipm.dto.BarangPokok.AddBarangPokokDTO;
import com.menyala.sipm.dto.BarangPokok.AddJenisBarangDTO;
import com.menyala.sipm.model.BackOrder;
import com.menyala.sipm.model.BarangPokok;
import com.menyala.sipm.model.JenisBarang;

import java.util.List;
import java.util.UUID;

public interface BarangPokokService {
    BarangPokok create(AddBarangPokokDTO dto);
    List<String> getJenisBp();
    List<String> getJenisBarangFromToko(UUID idToko);
    BackOrder createBackOrder(AddBackOrderDTO dto);
    JenisBarang createJenisBarang(AddJenisBarangDTO dto);

}
