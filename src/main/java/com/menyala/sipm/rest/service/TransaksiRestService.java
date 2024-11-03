package com.menyala.sipm.rest.service;

import com.menyala.sipm.rest.dto.response.BarTransaksiLokasiResponseDTO;
import com.menyala.sipm.rest.dto.response.LineTransaksiSemuaPasarResponseDTO;
import com.menyala.sipm.rest.dto.response.LineTransaksiSuatuPasarResponseDTO;
import com.menyala.sipm.rest.dto.response.LineTransaksiTokoResponseDTO;

import java.util.UUID;

public interface TransaksiRestService {
    BarTransaksiLokasiResponseDTO barTransaksiLokasi();
    LineTransaksiSemuaPasarResponseDTO lineTransaksiSemuaPasar();
    LineTransaksiSuatuPasarResponseDTO lineTransaksiSuatuPasar(UUID id);
    LineTransaksiTokoResponseDTO lineTransaksiToko(UUID id);
}
