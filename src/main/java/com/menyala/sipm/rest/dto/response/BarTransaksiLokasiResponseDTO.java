package com.menyala.sipm.rest.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BarTransaksiLokasiResponseDTO {
    private List<Integer> data;
    private List<String> labels;
}
