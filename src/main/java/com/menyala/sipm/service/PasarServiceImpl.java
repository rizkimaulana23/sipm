package com.menyala.sipm.service;

import com.menyala.sipm.dto.pasar.CreatePasarDTO;
import com.menyala.sipm.dto.pasar.UpdatePasarDTO;
import com.menyala.sipm.model.Pasar;
import com.menyala.sipm.repository.PasarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PasarServiceImpl implements PasarService {

    @Autowired
    private PasarRepo pasarRepo;

    @Override
    public Pasar getPasarById(UUID id) {
        return pasarRepo.findById(id).orElse(null);
    }

    @Override
    public Pasar create(CreatePasarDTO dto) {
        Pasar pasar = new Pasar();
        pasar.setId(UUID.randomUUID());
        pasar.setNama(dto.getNama());
        pasar.setAlamat(dto.getAlamat());
        pasar.setRetribusi(dto.getRetribusi());
        return pasarRepo.save(pasar);
    }

    @Override
    public Pasar update(UpdatePasarDTO dto) {
        Pasar pasar = getPasarById(dto.getId());
        pasar.setNama(dto.getNama());
        pasar.setAlamat(dto.getAlamat());
        pasar.setRetribusi(dto.getRetribusi());
        return pasarRepo.save(pasar);
    }
}
