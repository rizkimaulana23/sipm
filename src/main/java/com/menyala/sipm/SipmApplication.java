package com.menyala.sipm;

import com.github.javafaker.Faker;
import com.menyala.sipm.dto.infrastruktur.AddInfrastrukturDTO;
import com.menyala.sipm.dto.pasar.CreatePasarDTO;
import com.menyala.sipm.model.Pasar;
import com.menyala.sipm.service.InfrastrukturService;
import com.menyala.sipm.service.PasarService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
				Pasar pasar = pasarService.create(createPasarDTO);

				List<String> jenisInfrastruktur = infrastrukturService.getJenis();
				for (String jenis : jenisInfrastruktur) {
					AddInfrastrukturDTO addInfrastrukturDTO =  new AddInfrastrukturDTO();
					addInfrastrukturDTO.setJenis(jenis);
					addInfrastrukturDTO.setNama(jenis + " " + pasar.getNama());
					addInfrastrukturDTO.setPenanggungJawab(faker.name().fullName());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date fromDate = sdf.parse("2023-01-01");
					Date toDate = sdf.parse("2023-12-31");
					addInfrastrukturDTO.setTanggalPembangunan(faker.date().between(fromDate, toDate));
					addInfrastrukturDTO.setTanggalTerakhirKaliPengecekan(faker.date().between(fromDate, toDate));
					addInfrastrukturDTO.setIdPasar(pasar.getId());
					infrastrukturService.create(addInfrastrukturDTO);
				}
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
