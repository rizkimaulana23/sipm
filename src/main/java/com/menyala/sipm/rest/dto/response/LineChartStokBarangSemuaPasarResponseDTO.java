package com.menyala.sipm.rest.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class LineChartStokBarangSemuaPasarResponseDTO {
    private List<String> labelsDate;
    private List<Long> dataJumlahStok;
}
