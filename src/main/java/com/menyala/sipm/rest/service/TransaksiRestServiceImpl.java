package com.menyala.sipm.rest.service;

import com.menyala.sipm.rest.dto.response.BarTransaksiLokasiResponseDTO;
import com.menyala.sipm.rest.dto.response.LineTransaksiSemuaPasarResponseDTO;
import com.menyala.sipm.rest.dto.response.LineTransaksiSuatuPasarResponseDTO;
import com.menyala.sipm.rest.dto.response.LineTransaksiTokoResponseDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransaksiRestServiceImpl implements TransaksiRestService {
    @Override
    public BarTransaksiLokasiResponseDTO barTransaksiLokasi() {
        BarTransaksiLokasiResponseDTO response = new BarTransaksiLokasiResponseDTO();
        return response;
    }

    @Override
    public LineTransaksiSemuaPasarResponseDTO lineTransaksiSemuaPasar() {
        LineTransaksiSemuaPasarResponseDTO response = new LineTransaksiSemuaPasarResponseDTO();
        return response;
    }

    @Override
    public LineTransaksiSuatuPasarResponseDTO lineTransaksiSuatuPasar(UUID id) {
        LineTransaksiSuatuPasarResponseDTO response = new LineTransaksiSuatuPasarResponseDTO();
        return response;
    }

    @Override
    public LineTransaksiTokoResponseDTO lineTransaksiToko(UUID id) {
        LineTransaksiTokoResponseDTO response = new LineTransaksiTokoResponseDTO();
        return response;
    }
}
