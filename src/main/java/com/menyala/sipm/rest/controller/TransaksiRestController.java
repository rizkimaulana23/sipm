package com.menyala.sipm.rest.controller;

import com.menyala.sipm.rest.dto.response.BarTransaksiLokasiResponseDTO;
import com.menyala.sipm.rest.dto.response.FailedBaseResponseDTO;
import com.menyala.sipm.rest.dto.response.SuccessBaseResponseDTO;
import com.menyala.sipm.rest.service.TransaksiRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class TransaksiRestController {

    @Autowired
    private TransaksiRestService transaksiRestService;

    @GetMapping("/transaksi-lokasi")
    public ResponseEntity<?> LineChartTransaksiTiapHarinyaSemuaPasar() {
        try {
            BarTransaksiLokasiResponseDTO responseDTO = transaksiRestService.barTransaksiLokasi();
            var successBaseResponse = new SuccessBaseResponseDTO<BarTransaksiLokasiResponseDTO>();
            successBaseResponse.setMessage("Data berhasil diambil");
            successBaseResponse.setStatus(HttpStatus.OK.value());
            successBaseResponse.setData(responseDTO);
            return new ResponseEntity<>(successBaseResponse, HttpStatus.OK);
        } catch (Exception e) {
            var failedBaseResponse = new FailedBaseResponseDTO<String>();
            failedBaseResponse.setMessage(e.getMessage());
            failedBaseResponse.setTimestamp(new Date());
            failedBaseResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            failedBaseResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            failedBaseResponse.setData("Terjadi kesalahan di dalam server");
            return new ResponseEntity<>(failedBaseResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
