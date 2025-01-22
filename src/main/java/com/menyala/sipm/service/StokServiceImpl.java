package com.menyala.sipm.service;

import com.menyala.sipm.dto.BarangPokok.AddBarangPokokDTO;
import com.menyala.sipm.dto.infrastruktur.AddMaintenanceInfrastrukturDTO;
import com.menyala.sipm.model.BarangPokok;
import com.menyala.sipm.model.JadwalMaintenanceInfrastruktur;
import com.menyala.sipm.model.JenisBarang;
import com.menyala.sipm.model.Toko;
import com.menyala.sipm.repository.BarangPokokRepo;
import com.menyala.sipm.repository.JenisBarangRepo;
import com.menyala.sipm.repository.TokoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class StokServiceImpl implements StokService {

    @Autowired
    private JenisBarangRepo jenisBarangRepo;

    @Autowired
    private BarangPokokRepo barangPokokRepo;

    @Autowired
    private TokoRepo tokoRepo;

    @Override
    public float calculateAverageThisPeriod(String jenisBarang) {
        JenisBarang jenisBarangObject = jenisBarangRepo.existsById(jenisBarang) ? jenisBarangRepo.findById(jenisBarang).get() : null;
        Calendar calendarSevenDaysBefore = Calendar.getInstance();
        calendarSevenDaysBefore.setTime(new Date());
        calendarSevenDaysBefore.add(Calendar.DATE, -7);

        Calendar calendar14DaysBefore = Calendar.getInstance();
        calendar14DaysBefore.setTime(new Date());
        calendar14DaysBefore.add(Calendar.DATE, -14);
        List<BarangPokok> listBarangPokok = barangPokokRepo
                                                .findAllByJenisBarangAndTanggalMasukAfter(jenisBarangObject, calendarSevenDaysBefore.getTime());

        float result = 0;
        int count = 0;
        for (BarangPokok bp : listBarangPokok) {
            result += bp.getStok();
            count++;
        }
        return result / count;
    }

    @Override
    public float calculateAveragePreviousPeriod(String jenisBarang) {
        JenisBarang jenisBarangObject = jenisBarangRepo.existsById(jenisBarang) ? jenisBarangRepo.findById(jenisBarang).get() : null;

        Calendar calendarSevenDaysBefore = Calendar.getInstance();
        calendarSevenDaysBefore.setTime(new Date());
        calendarSevenDaysBefore.add(Calendar.DATE, -7);

        Calendar calendar14DaysBefore = Calendar.getInstance();
        calendar14DaysBefore.setTime(new Date());
        calendar14DaysBefore.add(Calendar.DATE, -14);

        List<BarangPokok> listBarangPokok = barangPokokRepo
                .findAllByJenisBarangAndTanggalMasukBetween(jenisBarangObject, calendar14DaysBefore.getTime(), calendarSevenDaysBefore.getTime());

        float result = 0;
        int count = 0;
        for (BarangPokok bp : listBarangPokok) {
            result += bp.getStok();
            count++;
        }
        return result / count;
    }

    @Override
    public String calculatePercentageDifferent(float averageCurrent, float averagePrevious) {
        float result = (averageCurrent - averagePrevious) / averagePrevious;
        return String.format("%.2f%%", result * 100);
    }

    public BarangPokok addBarangPokok(AddBarangPokokDTO dto) {
        BarangPokok barang = new BarangPokok();
        barang.setId(UUID.randomUUID()); // Generate ID

        // Find and set JenisBarang
        JenisBarang jenisBarang = jenisBarangRepo.findById(dto.getIdJenisBarang())
                .orElseThrow(() -> new IllegalArgumentException("JenisBarang tidak ditemukan"));
        barang.setJenisBarang(jenisBarang);

        // Validate and set Toko list
        List<Toko> listToko = tokoRepo.findAllById(dto.getListIdToko());
        if (listToko.isEmpty()) {
            throw new IllegalArgumentException("Daftar Toko tidak valid atau kosong");
        }
        barang.setListToko(listToko);

        // Set other fields
        barang.setNama(dto.getNama());
        barang.setStok(dto.getStok());
        barang.setTanggalMasuk(dto.getTanggalMasuk());
        barang.setTanggalKadaluarsa(dto.getTanggalKadaluwarsa());
        barang.setTotalPenjual(dto.getTotalPenjual());

        // Validate dates
        if (barang.getTanggalMasuk() != null && barang.getTanggalKadaluarsa() != null &&
                barang.getTanggalMasuk().compareTo(barang.getTanggalKadaluarsa()) > 0) {
            throw new IllegalArgumentException("Tanggal masuk tidak boleh setelah tanggal kadaluarsa");
        }

        // Save entity using JPA
        return barangPokokRepo.save(barang);
    }



}



