package com.menyala.sipm.controller;

import com.menyala.sipm.dto.infrastruktur.AddMaintenanceInfrastrukturDTO;
import com.menyala.sipm.dto.infrastruktur.AddPengecekanInfrastrukturDTO;
import com.menyala.sipm.model.Infrastruktur;
import com.menyala.sipm.model.JadwalMaintenanceInfrastruktur;
import com.menyala.sipm.model.JadwalPengecekanInfrastruktur;
import com.menyala.sipm.model.Pasar;
import com.menyala.sipm.repository.BackOrderRepo;
import com.menyala.sipm.repository.InfrastrukturRepo;
import com.menyala.sipm.repository.PasarRepo;
import com.menyala.sipm.service.InfrastrukturService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/infrastruktur")
public class InfrastrukturController {

    @Autowired
    private InfrastrukturRepo infrastrukturRepo;

    @Autowired
    private PasarRepo pasarRepo;

    @Autowired
    private BackOrderRepo backOrderRepo;

    @Autowired
    private InfrastrukturService infrastrukturService;

    @GetMapping("")
    public String allPasar(Model model) {
        List<Pasar> listPasar = pasarRepo.findAll();
        List<Integer> infrastrukturCount = new ArrayList<>();
        for (Pasar pasar : listPasar) {
            infrastrukturCount.add(infrastrukturRepo.findAllByPasar(pasar).size());
        }
        model.addAttribute("infrastrukturCount", infrastrukturCount);
        model.addAttribute("listPasar", listPasar);
        return "infrastruktur/all-infrastruktur";
    }

    @GetMapping("/{id}")
    public String detailPasar(Model model, @PathVariable("id") UUID id) {
        Pasar pasar = pasarRepo.findById(id).orElse(null);
        if (pasar == null) {
            model.addAttribute("error", "Pasar tidak ditemukan");
            return "error";
        }

        List<Infrastruktur> listInfrastruktur = infrastrukturRepo.findAllByPasar(pasar);
        model.addAttribute("listInfrastruktur", listInfrastruktur);
        model.addAttribute("pasar", pasar);

        List<Date> listMaintenance = new ArrayList<>();
        List<Date> listPengecekan = new ArrayList<>();

        for (Infrastruktur i : listInfrastruktur) {
            // Handle list maintenance
            if (!i.getListJadwalMaintenanceInfrastruktur().isEmpty()) {
                listMaintenance.add(i.getListJadwalMaintenanceInfrastruktur().getLast().getTanggalMaintenance());
            } else {
                listMaintenance.add(null); // Atau skip penambahan jika tidak ingin nilai null
            }

            // Handle list pengecekan
            if (!i.getListJadwalPengecekanInfrastruktur().isEmpty()) {
                listPengecekan.add(i.getListJadwalPengecekanInfrastruktur().getLast().getTanggal());
            } else {
                listPengecekan.add(null); // Atau skip penambahan jika tidak ingin nilai null
            }
        }

        model.addAttribute("listMaintenance", listMaintenance);
        model.addAttribute("listPengecekan", listPengecekan);
        model.addAttribute("listBackOrder", backOrderRepo.findAllByPasar(pasar));
        return "infrastruktur/detail-infrastruktur";
    }


    @GetMapping("/detail/{id}")
    public String detailInfrastruktur(Model model, @PathVariable("id") UUID id) {
        Infrastruktur infrastruktur = infrastrukturRepo.findById(id).orElse(null);
        List<JadwalMaintenanceInfrastruktur> listMaintenance = infrastruktur.getListJadwalMaintenanceInfrastruktur();
        List<JadwalPengecekanInfrastruktur> listPengecekan = infrastruktur.getListJadwalPengecekanInfrastruktur();
        model.addAttribute("listMaintenance", listMaintenance);
        model.addAttribute("listPengecekan", listPengecekan);
        model.addAttribute("infrastruktur", infrastruktur);
        return "infrastruktur/detail-nama-infrastruktur";
    }


    @GetMapping("/detail/{id}/input-pengecekan")
    public String inputPengecekan(Model model, @PathVariable("id") UUID id) {
        Infrastruktur infrastruktur = infrastrukturRepo.findById(id).orElse(null);
        Pasar pasar = pasarRepo.findById(infrastruktur.getPasar().getId()).orElse(null);
        AddPengecekanInfrastrukturDTO dto = new AddPengecekanInfrastrukturDTO();
        dto.setInfrastrukturID(id);
        model.addAttribute("pasar", pasar);
        model.addAttribute("infrastruktur", infrastruktur);
        model.addAttribute("dto", dto);
        return "infrastruktur/input-pengecekan";
    }

    @PostMapping("/detail/{id}/input-pengecekan")
    public String inputPengecekan(Model model, @PathVariable("id") UUID id, @ModelAttribute AddPengecekanInfrastrukturDTO dto) {
        infrastrukturService.addPengecekan(dto);
        return "redirect:/infrastruktur/detail/" + id;
    }

    @GetMapping("/detail/{id}/input-maintenance")
    public String inputMaintenance(Model model, @PathVariable("id") UUID id) {
        Infrastruktur infrastruktur = infrastrukturRepo.findById(id).orElse(null);
        Pasar pasar = pasarRepo.findById(infrastruktur.getPasar().getId()).orElse(null);
        AddMaintenanceInfrastrukturDTO dto = new AddMaintenanceInfrastrukturDTO();
        dto.setInfrastrukturID(id);
        model.addAttribute("pasar", pasar);
        model.addAttribute("infrastruktur", infrastruktur);
        model.addAttribute("dto", dto);
        return "infrastruktur/input-maintenance";
    }

    @PostMapping("/detail/{id}/input-maintenance")
    public String inputMaintenance(@PathVariable("id") UUID id, @ModelAttribute AddMaintenanceInfrastrukturDTO dto) {
        infrastrukturService.addMaintenance(dto);
        return "redirect:/infrastruktur/detail/" + id;
    }


    @PostMapping("/detail/delete/{id1}/{id2}")
    public String deletePengecekan(@PathVariable UUID id2, @PathVariable UUID id1) {
        infrastrukturService.deletePengecekan(id1);
        return "redirect:/infrastruktur/detail/" + id2;
    }



}
