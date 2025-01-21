package com.menyala.sipm.dto.infrastruktur;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class UpdateMaintenanceInfrastrukturDTO extends AddMaintenanceInfrastrukturDTO {
    private UUID id;
}
