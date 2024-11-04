package com.menyala.sipm.rest.service;

import com.menyala.sipm.model.Transaksi;
import com.menyala.sipm.repository.PasarRepo;
import com.menyala.sipm.repository.TransaksiRepo;
import com.menyala.sipm.rest.dto.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TransaksiRestServiceImpl implements TransaksiRestService {

    @Autowired
    private PasarRepo pasarRepo;

    @Autowired
    private TransaksiRepo transaksiRepo;

    @Override
    public BarTransaksiLokasiResponseDTO barTransaksiLokasi() {
        BarTransaksiLokasiResponseDTO response = new BarTransaksiLokasiResponseDTO();
        return response;
    }

    @Override
    public LineTransaksiSemuaPasarResponseDTO lineTransaksiSemuaPasar() {
        LineTransaksiSemuaPasarResponseDTO response = new LineTransaksiSemuaPasarResponseDTO();

        Map<String, Long> dateLongMap = new HashMap<>();

        for(Transaksi transaksi : transaksiRepo.findAll()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = dateFormat.format(transaksi.getTanggalTransaksi());
            if (!dateLongMap.containsKey(dateString)) {
                dateLongMap.put(dateString, 0L);
            } else {
                dateLongMap.put(dateString, dateLongMap.get(dateString) +  transaksi.getPendapatanHarian());
            }
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<String> sortedDates = new ArrayList<>();
        List<Long> correspondingLongs = new ArrayList<>();

        dateLongMap.entrySet().stream()
                .sorted(Comparator.comparing(entry -> {
                    try {
                        return dateFormat.parse(entry.getKey());
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }))
                .forEachOrdered(entry -> {
                    sortedDates.add(entry.getKey());
                    correspondingLongs.add(entry.getValue());
                });

        response.setData(correspondingLongs);
        response.setLabels(sortedDates);
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

    @Override
    public BarTransaksiHarianTokoSuatuPasarResponseDTO barTransaksiHarianTokoSuatuPasar(UUID id) {
        BarTransaksiHarianTokoSuatuPasarResponseDTO response  = new BarTransaksiHarianTokoSuatuPasarResponseDTO();
        return response;
    }
}
