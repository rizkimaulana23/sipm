package com.menyala.sipm.dto.Toko;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
public class UpdateTokoDTO extends AddTokoDTO {
    private UUID id;
}
