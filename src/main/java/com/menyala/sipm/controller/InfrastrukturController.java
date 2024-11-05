package com.menyala.sipm.controller;

import com.menyala.sipm.model.Infrastruktur;
import com.menyala.sipm.model.JadwalMaintenanceInfrastruktur;
import com.menyala.sipm.model.JadwalPengecekanInfrastruktur;
import com.menyala.sipm.model.Pasar;
import com.menyala.sipm.repository.InfrastrukturRepo;
import com.menyala.sipm.repository.PasarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
        List<Infrastruktur> listInfrastruktur = infrastrukturRepo.findAllByPasar(pasar);
        model.addAttribute("listInfrastruktur", listInfrastruktur);
        model.addAttribute("pasar", pasar);
        List<Date> listMaintenance = new ArrayList<>();
        List<Date> listPengecekan = new ArrayList<>();
        for (Infrastruktur i : listInfrastruktur) {
            listMaintenance.add(i.getListJadwalMaintenanceInfrastruktur().getLast().getTanggalMaintenance());
            listPengecekan.add(i.getListJadwalPengecekanInfrastruktur().getLast().getTanggal());
        }
        model.addAttribute("listMaintenance", listMaintenance);
        model.addAttribute("listPengecekan", listPengecekan);
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
}
