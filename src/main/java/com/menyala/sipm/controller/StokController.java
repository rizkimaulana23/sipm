package com.menyala.sipm.controller;

import com.menyala.sipm.repository.BarangPokokRepo;
import com.menyala.sipm.repository.JenisBarangRepo;
import com.menyala.sipm.repository.PasarRepo;
import com.menyala.sipm.service.BarangPokokService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/stok")
public class StokController {

    @Autowired
    private PasarRepo pasarRepo;

    @Autowired
    private BarangPokokRepo barangPokokRepo;

    @Autowired
    private JenisBarangRepo jenisBarangRepo;

    @Autowired
    private BarangPokokService barangPokokService;

    @GetMapping("")
    public String all(Model model) {
        model.addAttribute("listPasar", pasarRepo.findAll());
        List<Integer> listStokSetiapPasar = new ArrayList<>();
        return "stok/stok-all-pasar";
    }
}
