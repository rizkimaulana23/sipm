package com.menyala.sipm.controller;

import com.menyala.sipm.model.Pasar;
import com.menyala.sipm.model.Toko;
import com.menyala.sipm.model.Transaksi;
import com.menyala.sipm.repository.PasarRepo;
import com.menyala.sipm.repository.TokoRepo;
import com.menyala.sipm.repository.TransaksiRepo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/transaksi")
public class TransaksiController {

    @Data
    @NoArgsConstructor
    static class AllTransaksiDTO {
        List<Pasar> listPasar;
        List<Long> listTransaksi;
    }

    @Autowired
    private TransaksiRepo transaksiRepo;

    @Autowired
    private PasarRepo pasarRepo;

    @Autowired
    private TokoRepo tokoRepo;

    @GetMapping("")
    public String allTransaksi(Model model) {
        List<Pasar> allPasar = pasarRepo.findAll();

        AllTransaksiDTO allTransaksiDTO = new AllTransaksiDTO();
        allTransaksiDTO.listPasar = allPasar;
        List<Long> listTransaksi = new ArrayList<Long>();

        for (Pasar pasar : allPasar) {
            Long total = 0L;
            for (Toko toko: tokoRepo.findAllByPasar(pasar)) {
                for (Transaksi transaksi: transaksiRepo.findAllByToko(toko)) {
                    total += transaksi.getPendapatanHarian();
                }
            }
            listTransaksi.add(total);
        }
        model.addAttribute("allPasar", allPasar);
        model.addAttribute("listTransaksi", listTransaksi);
        model.addAttribute("listTransaksiPasar", allTransaksiDTO);
        return "transaksi/all-transaksi";
    }

    @GetMapping("/{id}")
    public String detailTransaksiPasar(Model model, @PathVariable("id") UUID uuid) {
        Pasar pasar = pasarRepo.findById(uuid).orElse(null);
        List<Toko> tokoList = tokoRepo.findAllByPasar(pasar);
        model.addAttribute("listToko", tokoList);

        List<Long> listTotalTransaksi = new ArrayList<>();
        for (Toko toko: tokoList) {
            Long total = 0L;
            for (Transaksi transaksi: transaksiRepo.findAllByToko(toko)) {
                total += transaksi.getPendapatanHarian();
            }
            listTotalTransaksi.add(total);
        }
        model.addAttribute("listTotalTransaksi", listTotalTransaksi);
        model.addAttribute("uuid", uuid);
        return "transaksi/detail-transaksi";
    }

    @GetMapping("/{id}/toko")
    public String detailTransaksiToko(Model model, @PathVariable("id") UUID uuid) {
        Toko toko = tokoRepo.findById(uuid).orElse(null);
        List<Transaksi> transaksiList = transaksiRepo.findAllByToko(toko);
        model.addAttribute("listTransaksi", transaksiList);
        model.addAttribute("uuid", uuid);
        return "transaksi/detail-transaksi-toko";
    }
}
