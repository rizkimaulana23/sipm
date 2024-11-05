package com.menyala.sipm.rest.controller;

import com.menyala.sipm.rest.dto.response.BarStokJenisBarangSemuaPasarResponseDTO;
import com.menyala.sipm.rest.dto.response.FailedBaseResponseDTO;
import com.menyala.sipm.rest.dto.response.LineChartStokBarangSemuaPasarResponseDTO;
import com.menyala.sipm.rest.dto.response.SuccessBaseResponseDTO;
import com.menyala.sipm.rest.service.StokRestService;
import com.menyala.sipm.rest.service.TransaksiRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/api/stok")
public class StokRestController {

    @Autowired
    private TransaksiRestService transaksiRestService;

    @Autowired
    private StokRestService stokRestService;

    @GetMapping("/jenis-barang")
    public ResponseEntity<?> barChartJenisBarangSemuaPasar() {
        try {
            BarStokJenisBarangSemuaPasarResponseDTO responseDTO = stokRestService.barChartJenisBarangSemuaPasar();
            var successBaseResponse = new SuccessBaseResponseDTO<BarStokJenisBarangSemuaPasarResponseDTO>();
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

    @GetMapping("/line")
    public ResponseEntity<?> lineChartStokBarang() {
        try {
            LineChartStokBarangSemuaPasarResponseDTO responseDTO = stokRestService.lineChartStokBarangSemuaPasar();
            var successBaseResponse = new SuccessBaseResponseDTO<LineChartStokBarangSemuaPasarResponseDTO>();
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
