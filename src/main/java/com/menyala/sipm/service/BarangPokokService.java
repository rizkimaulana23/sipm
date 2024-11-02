package com.menyala.sipm.service;

import com.menyala.sipm.dto.BarangPokok.AddBackOrderDTO;
import com.menyala.sipm.dto.BarangPokok.AddBarangPokokDTO;
import com.menyala.sipm.model.BackOrder;
import com.menyala.sipm.model.BarangPokok;

import java.util.List;

public interface BarangPokokService {
    BarangPokok create(AddBarangPokokDTO dto);
    List<String> getJenisBp();
    BackOrder createBackOrder(AddBackOrderDTO dto);


}