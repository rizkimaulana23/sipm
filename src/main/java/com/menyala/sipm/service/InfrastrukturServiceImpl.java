package com.menyala.sipm.service;

import com.menyala.sipm.dto.infrastruktur.AddInfrastrukturDTO;
import com.menyala.sipm.model.Infrastruktur;
import com.menyala.sipm.repository.InfrastrukturRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class InfrastrukturServiceImpl implements InfrastrukturService {

    @Autowired
    private InfrastrukturRepo infrastrukturRepo;

    @Override
    public Infrastruktur create(AddInfrastrukturDTO dto) {
        Infrastruktur infrastruktur = new Infrastruktur();
        infrastruktur.setId(UUID.randomUUID());
        infrastruktur.setNamaInfrastruktur("Toilet Laki-laki");
        infrastruktur.setJenis("Toilet");
        infrastruktur.setPenanggungJawab("Rizki Maulana");
        infrastruktur.setTanggalPembangunan(new Date());
        return infrastrukturRepo.save(infrastruktur);
    }
}
