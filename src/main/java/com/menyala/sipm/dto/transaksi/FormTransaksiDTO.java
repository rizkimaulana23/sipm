package com.menyala.sipm.dto.transaksi;

import com.menyala.sipm.model.Toko;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class FormTransaksiDTO {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tanggal;
    private List<Toko> listToko;
    private List<Long> listPendapatan;
}
