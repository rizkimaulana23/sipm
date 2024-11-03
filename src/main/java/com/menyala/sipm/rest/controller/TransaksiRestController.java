package com.menyala.sipm.rest.controller;

import com.menyala.sipm.rest.dto.response.*;
import com.menyala.sipm.rest.service.TransaksiRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api/transaksi")
public class TransaksiRestController {

    @Autowired
    private TransaksiRestService transaksiRestService;

    @GetMapping("/lokasi")
    public ResponseEntity<?> BarChartTransaksiLokasiSemuaPasar() {
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

    @GetMapping("")
    public ResponseEntity<?> LineChartTransaksiSemuaPasar() {
        try {
            LineTransaksiSemuaPasarResponseDTO responseDTO = transaksiRestService.lineTransaksiSemuaPasar();
            var successBaseResponse = new SuccessBaseResponseDTO<LineTransaksiSemuaPasarResponseDTO>();
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
            failedBaseResponse.setData("Terjadi kesalahan di dalam  server");
            return new ResponseEntity<>(failedBaseResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> lineTransaksiSuatuPasar(@PathVariable("id")UUID id) {
        try {
            LineTransaksiSuatuPasarResponseDTO responseDTO = transaksiRestService.lineTransaksiSuatuPasar(id);
            var successBaseResponse = new SuccessBaseResponseDTO<LineTransaksiSuatuPasarResponseDTO>();
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
            failedBaseResponse.setData("Terjadi kesalahan pada pasar tersebut");
            return new ResponseEntity<>(failedBaseResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/toko/{id}")
    public ResponseEntity<?> lineTransaksiToko(@PathVariable("id")UUID id) {
        try {
            LineTransaksiTokoResponseDTO responseDTO = transaksiRestService.lineTransaksiToko(id);
            var successBaseResponse = new SuccessBaseResponseDTO<LineTransaksiTokoResponseDTO>();
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
            failedBaseResponse.setData("Terjadi kesalahan pada toko tersebut");
            return new ResponseEntity<>(failedBaseResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
