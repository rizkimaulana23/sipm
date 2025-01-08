package com.menyala.sipm.service;

public interface StokService {
    float calculateAverageThisPeriod(String jenisBarang);
    float calculateAveragePreviousPeriod(String jenisBarang);
    String calculatePercentageDifferent(float averageCurrent, float averagePrevious);
}
