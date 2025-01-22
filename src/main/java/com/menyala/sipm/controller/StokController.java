package com.menyala.sipm.controller;

import com.menyala.sipm.dto.BarangPokok.AddBarangPokokDTO;
import com.menyala.sipm.dto.BarangPokok.AddStokDTO;
import com.menyala.sipm.dto.infrastruktur.AddMaintenanceInfrastrukturDTO;
import com.menyala.sipm.model.BarangPokok;
import com.menyala.sipm.model.Infrastruktur;
import com.menyala.sipm.model.Pasar;
import com.menyala.sipm.model.Toko;
import com.menyala.sipm.repository.PasarRepo;
import com.menyala.sipm.repository.TokoRepo;
import com.menyala.sipm.service.BarangPokokService;
import com.menyala.sipm.service.FormattingService;
import com.menyala.sipm.service.StokService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/stok")
public class StokController {

    @Autowired
    private PasarRepo pasarRepo;

    @Autowired
    private FormattingService formattingService;

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
        model.addAttribute("formattingService", formattingService);
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

    @GetMapping("/toko/{id}/input-stok")
    public String inputStok(Model model, @PathVariable("id") UUID id) {

        Toko toko = tokoRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Toko dengan ID " + id + " tidak ditemukan"));
        Pasar pasar = pasarRepo.findById(toko.getPasar().getId())
                .orElseThrow(() -> new IllegalArgumentException("Pasar terkait tidak ditemukan"));

        AddStokDTO barangdto = new AddStokDTO();
        barangdto.setIdToko(id);

        model.addAttribute("pasar", pasar);
        model.addAttribute("toko", toko);
        model.addAttribute("dto", barangdto);

        return "stok/stok-detail-toko";
    }

    @PostMapping("/toko/{id}/input-stok")
    public String inputStok(@PathVariable("id") UUID id, @ModelAttribute AddBarangPokokDTO dto) {
        if (dto.getNama() == null || dto.getNama().isEmpty()) {
            throw new IllegalArgumentException("Nama barang pokok tidak boleh kosong");
        }

        if (dto.getListIdToko() == null || !dto.getListIdToko().contains(id)) {
            dto.setListIdToko(List.of(id));
        }
        barangPokokService.create(dto);
        return "redirect:/stok/toko/" + id;
    }
}
