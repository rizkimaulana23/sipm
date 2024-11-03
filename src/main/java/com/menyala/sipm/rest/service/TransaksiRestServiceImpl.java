package com.menyala.sipm.rest.service;

import com.menyala.sipm.rest.dto.response.BarTransaksiLokasiResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class TransaksiRestServiceImpl implements TransaksiRestService {
    @Override
    public BarTransaksiLokasiResponseDTO barTransaksiLokasi() {
        BarTransaksiLokasiResponseDTO response = new BarTransaksiLokasiResponseDTO();
        return response;
    }
}
