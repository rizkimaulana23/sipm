package com.menyala.sipm.service;

import com.menyala.sipm.dto.Toko.AddTransaksiDTO;
import com.menyala.sipm.model.Transaksi;
import com.menyala.sipm.repository.TokoRepo;
import com.menyala.sipm.repository.TransaksiRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class TransaksiServiceImpl implements TransaksiService{

    @Autowired
    private TransaksiRepo transaksiRepo;

    @Autowired
    private TokoRepo tokoRepo;

    @Override
    public boolean saveTransaksiService(HashMap<UUID, List<AddTransaksiDTO>> map) {
        for (UUID id : map.keySet()) {
            for (AddTransaksiDTO dto : map.get(id)) {
                Transaksi transaksi = new Transaksi();
                transaksi.setIdTransaksi(UUID.randomUUID());
                transaksi.setTanggalTransaksi(dto.getTanggalTransaksi());
                transaksi.setPendapatanHarian(dto.getPendapatanHarian());
                transaksi.setToko(tokoRepo.findById(dto.getIdToko()).orElse(null));
                transaksiRepo.save(transaksi);
            }
        }
        return true;
    }
}
