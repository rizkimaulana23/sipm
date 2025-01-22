package com.menyala.sipm.service;

import com.menyala.sipm.dto.BarangPokok.AddBarangPokokDTO;
import com.menyala.sipm.dto.infrastruktur.AddPengecekanInfrastrukturDTO;
import com.menyala.sipm.model.BarangPokok;
import com.menyala.sipm.model.JadwalPengecekanInfrastruktur;

public interface StokService {
    float calculateAverageThisPeriod(String jenisBarang);
    float calculateAveragePreviousPeriod(String jenisBarang);
    String calculatePercentageDifferent(float averageCurrent, float averagePrevious);
    BarangPokok addBarangPokok(AddBarangPokokDTO dto);
}
