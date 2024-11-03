package com.menyala.sipm.rest.service;

import com.menyala.sipm.rest.dto.response.BarTransaksiLokasiResponseDTO;
import com.menyala.sipm.rest.dto.response.LineTransaksiSemuaPasarResponseDTO;

public interface TransaksiRestService {
    BarTransaksiLokasiResponseDTO barTransaksiLokasi();
    LineTransaksiSemuaPasarResponseDTO lineTransaksiSemuaPasar();
}
