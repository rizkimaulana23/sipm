package com.menyala.sipm.dto.Toko;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
public class UpdateTableDTO extends AddTokoDTO {
    private UUID id;
}
