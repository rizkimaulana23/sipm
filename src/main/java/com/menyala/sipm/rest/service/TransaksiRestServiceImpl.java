package com.menyala.sipm.rest.service;

import com.menyala.sipm.model.Pasar;
import com.menyala.sipm.model.Toko;
import com.menyala.sipm.model.Transaksi;
import com.menyala.sipm.repository.PasarRepo;
import com.menyala.sipm.repository.TokoRepo;
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
    private TokoRepo tokoRepo;

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
                dateLongMap.put(dateString, transaksi.getPendapatanHarian());
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
        response.setNamaPasar(pasarRepo.findById(id).get().getNama());
        Map<String, Long> dateLongMap = new HashMap<>();
        for (Toko toko: tokoRepo.findAllByPasar(pasarRepo.findById(id).get())) {
            for(Transaksi transaksi : transaksiRepo.findAllByToko(toko)) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = dateFormat.format(transaksi.getTanggalTransaksi());
                if (!dateLongMap.containsKey(dateString)) {
                    dateLongMap.put(dateString, transaksi.getPendapatanHarian());
                } else {
                    dateLongMap.put(dateString, dateLongMap.get(dateString) +  transaksi.getPendapatanHarian());
                }
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
    public LineTransaksiTokoResponseDTO lineTransaksiToko(UUID id) {
        LineTransaksiTokoResponseDTO response = new LineTransaksiTokoResponseDTO();
        Toko toko = tokoRepo.findById(id).get();
        Pasar pasar = pasarRepo.findById(toko.getPasar().getId()).get();

        response.setNamaToko(toko.getNamaToko());
        response.setNamaPasar(pasar.getNama());

        Map<String, Long> dateLongMap = new HashMap<>();
        for(Transaksi transaksi : transaksiRepo.findAllByToko(toko)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = dateFormat.format(transaksi.getTanggalTransaksi());
            if (!dateLongMap.containsKey(dateString)) {
                dateLongMap.put(dateString, transaksi.getPendapatanHarian());
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
    public BarTransaksiHarianTokoSuatuPasarResponseDTO barTransaksiHarianTokoSuatuPasar(UUID id) {
        BarTransaksiHarianTokoSuatuPasarResponseDTO response  = new BarTransaksiHarianTokoSuatuPasarResponseDTO();
        return response;
    }
}
