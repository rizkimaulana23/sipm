package com.menyala.sipm.dto.pasar;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class UpdatePasarDTO extends CreatePasarDTO {
    private UUID id;
}
