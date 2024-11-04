package com.menyala.sipm.rest.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BarStokJenisBarangSemuaPasarResponseDTO {
    private List<String> labelsJenisBarang;
    private List<Integer> dataBanyakStok;
}
