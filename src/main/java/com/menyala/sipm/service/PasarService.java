package com.menyala.sipm.service;

import com.menyala.sipm.dto.pasar.CreatePasarDTO;
import com.menyala.sipm.dto.pasar.UpdatePasarDTO;
import com.menyala.sipm.model.Pasar;

import java.util.UUID;

public interface PasarService {
    Pasar getPasarById(UUID id);
    Pasar create(CreatePasarDTO dto);
    Pasar update(UpdatePasarDTO dto);
}
