package com.menyala.sipm.service;

import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.Locale;

@Service
public class FormattingServiceImpl implements FormattingService {

    @Override
    public String formatRupiah(int amount) {
        NumberFormat formatter = NumberFormat.getInstance(new Locale("id", "ID"));
        return "Rp." + formatter.format(amount);
    }

    @Override
    public String formatStok(float amount) {
        return String.format("%.2f", amount);
    }


}
