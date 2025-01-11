package com.menyala.sipm.controller;

import com.menyala.sipm.dto.transaksi.FormTransaksiDTO;
import com.menyala.sipm.model.Pasar;
import com.menyala.sipm.model.Toko;
import com.menyala.sipm.model.Transaksi;
import com.menyala.sipm.repository.PasarRepo;
import com.menyala.sipm.repository.TokoRepo;
import com.menyala.sipm.repository.TransaksiRepo;
import com.menyala.sipm.service.TransaksiService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
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
    private TransaksiService transaksiService;

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
        model.addAttribute("pasar", pasar);
        return "transaksi/detail-transaksi";
    }

    @GetMapping("/{id}/toko")
    public String detailTransaksiToko(Model model, @PathVariable("id") UUID uuid) {
        Toko toko = tokoRepo.findById(uuid).orElse(null);
        List<Transaksi> transaksiList = transaksiRepo.findAllByToko(toko);
        model.addAttribute("listTransaksi", transaksiList);
        model.addAttribute("uuid", uuid);
        model.addAttribute("toko", toko);
        return "transaksi/detail-transaksi-toko";
    }

    @GetMapping("/{id}/input-transaksi")
    public String inputTransaksi(Model model, @PathVariable("id") UUID uuid) {
        Pasar pasar = pasarRepo.findById(uuid).orElse(null);
        FormTransaksiDTO dto = new FormTransaksiDTO();
        dto.setTanggal(new Date());
        dto.setListToko(new ArrayList<>());
        dto.setListPendapatan(new ArrayList<>());
        model.addAttribute("dto", dto);
        model.addAttribute("pasar", pasar);
        model.addAttribute("listToko", tokoRepo.findAllByPasar(pasar));
        return "transaksi/form-transaksi";
    }

    @PostMapping(value = "/{id}/input-transaksi", params = {"add"})
    public String addNewTokoInputTransaksi(Model model, @PathVariable("id") UUID uuid,
                                           @ModelAttribute FormTransaksiDTO dto,
                                           @RequestParam("idToko") UUID idToko) {
        if(dto.getListToko() == null) {
            dto.setListToko(new ArrayList<>());
            dto.setListPendapatan(new ArrayList<>());
        }

        if(!checkTokoDuplicate(idToko, dto.getListToko())) {
            dto.getListToko().add(tokoRepo.findById(idToko).orElse(null));
            dto.getListPendapatan().add(0L);
        }

        model.addAttribute("dto", dto);

        Pasar pasar = pasarRepo.findById(uuid).orElse(null);
        model.addAttribute("pasar", pasar);
        model.addAttribute("listToko", tokoRepo.findAllByPasar(pasar));
        return "transaksi/form-transaksi";
    }

    @PostMapping(value = "/{id}/input-transaksi", params = {"delete"})
    public String deleteTokoInputTransaksi (Model model, @PathVariable("id") UUID uuid,
                                            @ModelAttribute("dto") FormTransaksiDTO dto,
                                            @RequestParam("delete") int row ) {
        dto.getListToko().remove(row);
        dto.getListPendapatan().remove(row);
        Pasar pasar = pasarRepo.findById(uuid).orElse(null);
        model.addAttribute("pasar", pasar);
        model.addAttribute("listToko", tokoRepo.findAllByPasar(pasar));
        model.addAttribute("dto", dto);
        return "transaksi/form-transaksi";
    }


    @PostMapping("/{id}/input-transaksi")
    public String submitInputTransaksi(@PathVariable("id") UUID uuid,
                                       @ModelAttribute FormTransaksiDTO dto) {
        transaksiService.saveTransaksiService(dto);
        return "redirect:/transaksi/" + uuid;
    }

    private boolean checkTokoDuplicate(UUID uuid, List<Toko> list) {
        for (Toko toko: list) {
            if (toko.getId().toString().equals(uuid.toString())) return true;
        }
        return false;
    }
}
