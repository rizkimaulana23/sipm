package com.menyala.sipm;

import com.github.javafaker.Faker;
import com.menyala.sipm.dto.pasar.CreatePasarDTO;
import com.menyala.sipm.service.InfrastrukturService;
import com.menyala.sipm.service.PasarService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.Random;

@SpringBootApplication
public class SipmApplication {

	public static void main(String[] args) {
		SpringApplication.run(SipmApplication.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner run(PasarService pasarService, InfrastrukturService infrastrukturService) {
		return args -> {
			for (int i = 0; i < 100; i++) {
				var faker = new Faker(new Locale("in-ID"));

				CreatePasarDTO createPasarDTO = new CreatePasarDTO();
				String namaKota = faker.address().cityName();
				String namaJalan = faker.address().streetName();

				String location = Math.random() < 0.5 ? namaKota : namaJalan;

				createPasarDTO.setNama("Pasar " + location);
				createPasarDTO.setRetribusi(retributionGenerator());
				createPasarDTO.setAlamat(faker.address().fullAddress());
				pasarService.create(createPasarDTO);
			}
		};
	}

	private Long retributionGenerator() {
		long min = 1700;
		long max = 2500;
		long step = 100;

		int numberOfSteps = (int) ((max - min) / step+1);

		Random random = new Random();

		int randomIndex = random.nextInt(numberOfSteps);

		return min + (randomIndex * step);
	}
}
