package com.menyala.sipm.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PasarController {

    @GetMapping("/pasar")
    public String home() {
        return "pasar";
    }
}
