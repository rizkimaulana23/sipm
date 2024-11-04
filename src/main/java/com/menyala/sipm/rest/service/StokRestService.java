package com.menyala.sipm.rest.service;

import com.menyala.sipm.rest.dto.response.BarStokJenisBarangSemuaPasarResponseDTO;
import com.menyala.sipm.rest.dto.response.LineChartStokBarangSemuaPasarResponseDTO;

public interface StokRestService {
    BarStokJenisBarangSemuaPasarResponseDTO barChartJenisBarangSemuaPasar();
    LineChartStokBarangSemuaPasarResponseDTO lineChartStokBarangSemuaPasar();
}
