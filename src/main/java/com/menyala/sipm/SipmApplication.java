package com.menyala.sipm;

import com.github.javafaker.Faker;
import com.menyala.sipm.dto.BarangPokok.AddBarangPokokDTO;
import com.menyala.sipm.dto.Toko.AddTokoDTO;
import com.menyala.sipm.dto.infrastruktur.AddInfrastrukturDTO;
import com.menyala.sipm.dto.pasar.CreatePasarDTO;
import com.menyala.sipm.model.BarangPokok;
import com.menyala.sipm.model.Pasar;
import com.menyala.sipm.model.Toko;
import com.menyala.sipm.service.BarangPokokService;
import com.menyala.sipm.service.InfrastrukturService;
import com.menyala.sipm.service.PasarService;
import com.menyala.sipm.service.TokoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

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




	@Bean
	@Transactional
	CommandLineRunner run(BarangPokokService barangPokokService, TokoService tokoService) {
		return args -> {
			for (int i = 0; i < 100; i++) {

				var faker = new Faker(new Locale("in-ID"));

				AddTokoDTO addTokoDTO = new AddTokoDTO();

				addTokoDTO.setNamaToko("Toko "+faker.name());
				String namaKota = faker.address().cityName();
				String namaJalan = faker.address().streetName();
				addTokoDTO.setAlamatToko(namaJalan+" "+namaKota);

				String generatedNik = generateNik(faker);
				addTokoDTO.setNikPenjual(generatedNik);
				addTokoDTO.setNamaPenjual(faker.name().fullName());

				String generatedPhoneNumber = generatePhoneNumber(faker);
				addTokoDTO.setKontakPenjual(generatedPhoneNumber);

				// Create the Toko and persist it using the TokoService
				Toko toko = tokoService.createToko(addTokoDTO);

				// Generate a list of BarangPokok IDs to associate with this Toko
				// Generate a list of BarangPokok IDs to associate with this Toko
				List<UUID> listIdBarangPokok = new ArrayList<>();
				List<String> jenisBpList = barangPokokService.getJenisBp();  // Fetch the list of JenisBarang strings

				Random random = new Random();

				for (int j = 0; j < 5; j++) {  // Assuming each Toko has 5 linked BarangPokok items
					AddBarangPokokDTO addBarangPokokDTO = new AddBarangPokokDTO();
					addBarangPokokDTO.setNama(faker.commerce().productName());
					addBarangPokokDTO.setStok(faker.number().numberBetween(10, 100));
					addBarangPokokDTO.setTotalPenjual(faker.number().numberBetween(1, 10));
					addBarangPokokDTO.setTanggalKadaluwarsa(faker.date().future(365, TimeUnit.DAYS));


					String randomJenisBarang = jenisBpList.get(random.nextInt(jenisBpList.size()));
					addBarangPokokDTO.setIdJenisBarang(randomJenisBarang);

					// Create and save the BarangPokok, then add its ID to the list
					BarangPokok barangPokok = barangPokokService.create(addBarangPokokDTO);
					listIdBarangPokok.add(barangPokok.getId());

				}
				addTokoDTO.setListIdBarangPokok(listIdBarangPokok);
			}
		};
	}

	public static String generateNik(Faker faker) {

		String kodeWilayah = "647401";

		int day = faker.number().numberBetween(1, 31);
		int month = faker.number().numberBetween(1, 12);
		int year = faker.number().numberBetween(1950, 2022);

		boolean isFemale = faker.bool().bool();
		if (isFemale) {
			day += 40;
		}
		String tanggalLahir = String.format("%02d%02d%02d", day, month, year % 100);

		Random random = new Random();
		String nomorUnik = String.format("%04d", random.nextInt(10000));
		return kodeWilayah + tanggalLahir + nomorUnik;
	}

	public static String generatePhoneNumber(Faker faker) {
		// Awalan nomor telepon
		String prefix = "08";

		// Sisa digit (total 10 digit untuk mencapai 12 angka)
		StringBuilder phoneNumber = new StringBuilder(prefix);
		for (int i = 0; i < 10; i++) {
			phoneNumber.append(faker.number().digit());
		}

		return phoneNumber.toString();
	}


}
