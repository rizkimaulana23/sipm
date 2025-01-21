package com.menyala.sipm.dto.infrastruktur;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class UpdatePengecekanInfrastrukturDTO extends AddPengecekanInfrastrukturDTO{
    private UUID id;
}
