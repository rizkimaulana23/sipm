package com.menyala.sipm.service;

import com.menyala.sipm.model.BarangPokok;
import com.menyala.sipm.model.JenisBarang;
import com.menyala.sipm.repository.BarangPokokRepo;
import com.menyala.sipm.repository.JenisBarangRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class StokServiceImpl implements StokService {

    @Autowired
    private JenisBarangRepo jenisBarangRepo;

    @Autowired
    private BarangPokokRepo barangPokokRepo;

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


}
