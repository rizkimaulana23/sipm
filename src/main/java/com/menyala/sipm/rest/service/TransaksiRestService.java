package com.menyala.sipm.rest.service;

import com.menyala.sipm.rest.dto.response.*;

import java.util.UUID;

public interface TransaksiRestService {
    BarTransaksiLokasiResponseDTO barTransaksiLokasi();
    LineTransaksiSemuaPasarResponseDTO lineTransaksiSemuaPasar();
    LineTransaksiSuatuPasarResponseDTO lineTransaksiSuatuPasar(UUID id);
    LineTransaksiTokoResponseDTO lineTransaksiToko(UUID id);
    BarTransaksiHarianTokoSuatuPasarResponseDTO barTransaksiHarianTokoSuatuPasar(UUID id);
}
