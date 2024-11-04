package com.menyala.sipm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/infrastruktur")
public class InfrastrukturController {

    @GetMapping("")
    public String allInfrastruktur(Model model) {
        return "infrastruktur/all-infrastruktur.html";;
    }
}
