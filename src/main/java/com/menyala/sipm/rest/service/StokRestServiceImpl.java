package com.menyala.sipm.rest.service;

import com.menyala.sipm.model.BarangPokok;
import com.menyala.sipm.model.JenisBarang;
import com.menyala.sipm.repository.BarangPokokRepo;
import com.menyala.sipm.repository.JenisBarangRepo;
import com.menyala.sipm.rest.dto.response.BarStokJenisBarangSemuaPasarResponseDTO;
import com.menyala.sipm.rest.dto.response.LineChartStokBarangSemuaPasarResponseDTO;
import com.menyala.sipm.service.BarangPokokService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StokRestServiceImpl implements StokRestService {

    @Autowired
    private BarangPokokService barangPokokService;

    @Autowired
    private BarangPokokRepo barangPokokRepo;

    @Autowired
    private JenisBarangRepo jenisBarangRepo;

    @Override
    public BarStokJenisBarangSemuaPasarResponseDTO barChartJenisBarangSemuaPasar() {
        BarStokJenisBarangSemuaPasarResponseDTO responseDTO = new BarStokJenisBarangSemuaPasarResponseDTO();
        List<String> jenisBarang = barangPokokService.getJenisBp();

        Map<String, Integer> mapStokJenisBarang = new HashMap<>();
        for (String jenis : jenisBarang) {
            JenisBarang jb = jenisBarangRepo.findById(jenis).get();
            for (BarangPokok barangPokok: barangPokokRepo.findAllByJenisBarang(jb)) {
                if (!mapStokJenisBarang.containsKey(jenis)) {
                    mapStokJenisBarang.put(jenis, barangPokok.getStok());
                } else {
                    mapStokJenisBarang.put(jenis, mapStokJenisBarang.get(jenis) + barangPokok.getStok());
                }
            }
        }

        List<String> jenisList = new ArrayList<>();
        List<Integer> stokList = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : mapStokJenisBarang.entrySet()) {
            jenisList.add(entry.getKey());
            stokList.add(entry.getValue());
        }
        responseDTO.setLabelsJenisBarang(jenisList);
        responseDTO.setDataBanyakStok(stokList);
        return responseDTO;
    }

    @Override
    public LineChartStokBarangSemuaPasarResponseDTO lineChartStokBarangSemuaPasar() {
        LineChartStokBarangSemuaPasarResponseDTO responseDTO = new LineChartStokBarangSemuaPasarResponseDTO();

        Map<String, Integer> mapTanggalStokJenisBarang = new HashMap<>();

        return responseDTO;
    }
}
