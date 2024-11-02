package com.menyala.sipm.dto.pasar;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatePasarDTO {
    private String nama;
    private Long retribusi;
    private String alamat;
}
