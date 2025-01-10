package com.menyala.sipm.service;

import com.menyala.sipm.dto.transaksi.FormTransaksiDTO;
import com.menyala.sipm.model.Toko;
import com.menyala.sipm.model.Transaksi;
import com.menyala.sipm.repository.TokoRepo;
import com.menyala.sipm.repository.TransaksiRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransaksiServiceImpl implements TransaksiService{

    @Autowired
    private TransaksiRepo transaksiRepo;

    @Autowired
    private TokoRepo tokoRepo;

    @Override
    public boolean saveTransaksiService(FormTransaksiDTO dto) {
        int counter = 0;
        for (Toko toko : dto.getListToko()) {
            Transaksi transaksi = new Transaksi();
            transaksi.setIdTransaksi(UUID.randomUUID());
            transaksi.setTanggalTransaksi(dto.getTanggal());
            transaksi.setPendapatanHarian(dto.getListPendapatan().get(counter));
            transaksi.setToko(tokoRepo.findById(toko.getId()).orElse(null));
            transaksiRepo.save(transaksi);
            counter++;
        }
        return true;
    }
}
