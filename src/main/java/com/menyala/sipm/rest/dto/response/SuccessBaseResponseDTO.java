package com.menyala.sipm.rest.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SuccessBaseResponseDTO<T> {
    private int status;
    private String message;
    private T data;
}
