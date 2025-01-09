package com.menyala.sipm.service;

import com.menyala.sipm.dto.Toko.AddTransaksiDTO;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public interface TransaksiService {
    boolean saveTransaksiService(HashMap<UUID, List<AddTransaksiDTO>> map);
}
