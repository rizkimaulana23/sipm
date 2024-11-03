package com.menyala.sipm.rest.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class LineTransaksiTokoResponseDTO {
    private String namaToko;
    private String namaPasar;
    private List<Integer> data;
    private List<String> labels;
}
