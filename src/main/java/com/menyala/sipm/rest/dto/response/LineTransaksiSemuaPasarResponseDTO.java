package com.menyala.sipm.rest.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class LineTransaksiSemuaPasarResponseDTO {
    private List<String> labels;
    private List<Long> data;
}
