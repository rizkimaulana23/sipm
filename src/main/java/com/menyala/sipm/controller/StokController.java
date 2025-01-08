package com.menyala.sipm.controller;

import com.menyala.sipm.model.BarangPokok;
import com.menyala.sipm.model.Pasar;
import com.menyala.sipm.model.Toko;
import com.menyala.sipm.repository.BarangPokokRepo;
import com.menyala.sipm.repository.PasarRepo;
import com.menyala.sipm.repository.TokoRepo;
import com.menyala.sipm.service.BarangPokokService;
import com.menyala.sipm.service.StokService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/stok")
public class StokController {

    @Autowired
    private PasarRepo pasarRepo;

    @Autowired
    private BarangPokokRepo barangPokokRepo;

    @Autowired
    private StokService stokService;

    @Autowired
    private TokoRepo tokoRepo;

    @Autowired
    private BarangPokokService barangPokokService;

    @GetMapping("")
    public String all(Model model) {
        model.addAttribute("listJenisBarang", barangPokokService.getJenisBp());
        model.addAttribute("listPasar", pasarRepo.findAll());
        model.addAttribute("stokService", stokService);
        List<Integer> listStokSetiapPasar = new ArrayList<>();
        return "stok/stok-all-pasar";
    }

    @GetMapping("/{id}")
    public String detailPasar(Model model, @PathVariable UUID id) {
        Pasar pasar = pasarRepo.findById(id).get();
        model.addAttribute("pasar", pasar);

        List<Toko> listToko = tokoRepo.findAllByPasar(pasar);
        model.addAttribute("listToko", listToko);

        HashMap<Toko, HashMap<String, Integer>> hashMap = new HashMap<>();
        for (Toko toko : listToko) {
            HashMap<String, Integer> temp = new HashMap<>();
            for (BarangPokok barangPokok: toko.getListBarangPokok()) {
                if (temp.containsKey(barangPokok.getJenisBarang().getJenis())) {
                    temp.put(barangPokok.getJenisBarang().getJenis(), temp.get(barangPokok.getJenisBarang().getJenis()) + barangPokok.getStok());
                } else {
                    temp.put(barangPokok.getJenisBarang().getJenis(), barangPokok.getStok());
                }
            }
            hashMap.put(toko, temp);
        }
        model.addAttribute("mapTokoJenisBarang", hashMap);
        return "stok/stok-detail-pasar";
    }

    @GetMapping("/toko/{id}")
    public String detailToko(Model model,@PathVariable() UUID id) {
        Toko toko = tokoRepo.findById(id).get();
        model.addAttribute("toko", toko);

        List<BarangPokok> listBarangPokok = toko.getListBarangPokok();

        HashMap<String, List<BarangPokok>> mapBarangPokok = new HashMap<>();
        for (BarangPokok barangPokok: listBarangPokok) {
            if(mapBarangPokok.containsKey(barangPokok.getJenisBarang().getJenis())) {
                List<BarangPokok> listTemp = mapBarangPokok.get(barangPokok.getJenisBarang().getJenis());
                listTemp.add(barangPokok);
                mapBarangPokok.put(barangPokok.getJenisBarang().getJenis(), listTemp);
            } else {
                List<BarangPokok> listTemp = new ArrayList<>();
                listTemp.add(barangPokok);
                mapBarangPokok.put(barangPokok.getJenisBarang().getJenis(), listTemp);
            }
        }

        List<String> listJenisBarang = new ArrayList<>(mapBarangPokok.keySet());
        model.addAttribute("listJenisBarang", listJenisBarang);
        model.addAttribute("mapBarangPokok", mapBarangPokok);
        return "stok/stok-detail-toko";
    }
}
