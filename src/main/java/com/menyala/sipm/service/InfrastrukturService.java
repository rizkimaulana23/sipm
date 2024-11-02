package com.menyala.sipm.service;

import com.menyala.sipm.dto.infrastruktur.AddInfrastrukturDTO;
import com.menyala.sipm.model.Infrastruktur;

public interface InfrastrukturService {
    Infrastruktur create(AddInfrastrukturDTO dto);
}
