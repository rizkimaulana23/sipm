package com.menyala.sipm;

import com.github.javafaker.Faker;
import com.menyala.sipm.dto.BarangPokok.AddBarangPokokDTO;
import com.menyala.sipm.dto.BarangPokok.AddJenisBarangDTO;
import com.menyala.sipm.dto.Toko.AddMaintenanceDTO;
import com.menyala.sipm.dto.Toko.AddTokoDTO;
import com.menyala.sipm.dto.Toko.AddTransaksiDTO;
import com.menyala.sipm.dto.infrastruktur.AddInfrastrukturDTO;
import com.menyala.sipm.dto.infrastruktur.AddMaintenanceInfrastrukturDTO;
import com.menyala.sipm.dto.infrastruktur.AddPengecekanInfrastrukturDTO;
import com.menyala.sipm.dto.pasar.CreatePasarDTO;
import com.menyala.sipm.model.Infrastruktur;
import com.menyala.sipm.model.JenisBarang;
import com.menyala.sipm.model.Pasar;
import com.menyala.sipm.model.Toko;
import com.menyala.sipm.service.BarangPokokService;
import com.menyala.sipm.service.InfrastrukturService;
import com.menyala.sipm.service.PasarService;
import com.menyala.sipm.service.TokoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@Slf4j
public class SipmApplication {

	public static void main(String[] args) {
		SpringApplication.run(SipmApplication.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner run(PasarService pasarService, InfrastrukturService infrastrukturService, BarangPokokService barangPokokService, TokoService tokoService) {
		return args -> {
			for (int i = 0; i < 10; i++) {

				var faker = new Faker(new Locale("in-ID"));

				CreatePasarDTO createPasarDTO = new CreatePasarDTO();
				String namaKota = faker.address().cityName();
				String namaJalan = faker.address().streetName();

				String location = Math.random() < 0.5 ? namaKota : namaJalan;

				createPasarDTO.setNama("Pasar " + location);
				List<String> listKota = List.of("Jakarta Utara","Jakarta Pusat", "Jakarta Timur", "Jakarta Barat", "Jakarta Selatan");
				Random random = new Random();
				createPasarDTO.setKota(listKota.get(random.nextInt(listKota.size())));
				createPasarDTO.setRetribusi(retributionGenerator());
				createPasarDTO.setAlamat(faker.address().fullAddress());
				Pasar pasar = pasarService.create(createPasarDTO);

				List<UUID> tokoList = new ArrayList<>();

				for (int z = 0; z < 15; z++) { // Tambahkan 1-5 toko

					AddTokoDTO tokoDTO  = new AddTokoDTO();
					tokoDTO.setId(UUID.randomUUID());
					tokoDTO.setNamaToko(faker.company().name());
					tokoDTO.setAlamatToko(faker.address().fullAddress());
					tokoDTO.setNikPenjual(faker.idNumber().valid());
					tokoDTO.setKontakPenjual(faker.phoneNumber().cellPhone());
					tokoDTO.setIdPasar(pasar.getId());
					Toko toko = tokoService.createToko(tokoDTO);

					tokoList.add(toko.getId());

					for (int u = 0; u < faker.number().numberBetween(2, 5); u++) {
						AddTransaksiDTO transaksi = new AddTransaksiDTO();
						transaksi.setIdToko(toko.getId());
						transaksi.setPendapatanHarian(faker.number().numberBetween(1000L, 10000L));
						transaksi.setTanggalTransaksi(faker.date().past(14, TimeUnit.DAYS));
						tokoService.createTransaksi(transaksi);
//						listTransaksi.add(transaksi);
					}

//					for (int p = 0; p < faker.number().numberBetween(2, 5); p++) {
//						AddBackOrderDTO backOrder = new AddBackOrderDTO();
//						backOrder.setIdToko(toko.getId());
//						backOrder.setIdPasar(toko.getPasar().getId());
//						backOrder.setIdJenisBarang("beras");
//						backOrder.setNama(faker.commerce().productName());
//						barangPokokService.createBackOrder(backOrder);
////						listBackOrder.add(backOrder);
//					}

					for (int m = 0; m < faker.number().numberBetween(2, 5); m++) {
						AddMaintenanceDTO maintenance = new AddMaintenanceDTO();
						maintenance.setIdToko(toko.getId());
						maintenance.setTanggal(new Date());
						maintenance.setDeskripsiMaintenance(faker.lorem().sentence());
						maintenance.setPelakuMaintenance(faker.name().fullName());
						maintenance.setBiayaMaintenance(faker.number().numberBetween(5000L, 20000L));
						tokoService.addMaintenance(maintenance);
//						listMaintenance.add(maintenance);
					}
				}

				List<String> jenisBarangList = barangPokokService.getJenisBp();
				for (String jenisB : jenisBarangList) {

					AddJenisBarangDTO addJenisBarangDTO = new AddJenisBarangDTO();
					addJenisBarangDTO.setJenis(jenisB);

					JenisBarang jenisBarang = barangPokokService.createJenisBarang(addJenisBarangDTO);

					for (int a = 0; a < 5; a++) {
						AddBarangPokokDTO barangPokok = new AddBarangPokokDTO();

						barangPokok.setIdJenisBarang(String.valueOf(jenisBarang));
						barangPokok.setNama(jenisB + " " + faker.food().ingredient());
						barangPokok.setStok(faker.number().numberBetween(1, 100)); // Stok antara 1 dan 100
						barangPokok.setTotalPenjual(faker.number().numberBetween(1, 50)); // Total penjual antara 1 dan 50
						barangPokok.setTanggalKadaluwarsa(new Date(System.currentTimeMillis() + faker.number().numberBetween(1, 365) * 24 * 60 * 60 * 1000L));
						barangPokok.setListIdToko(tokoList);
						barangPokokService.create(barangPokok);
					}
				}


				List<String> jenisInfrastruktur = infrastrukturService.getJenis();
				for (String jenis : jenisInfrastruktur) {
					// Buat dan isi objek AddInfrastrukturDTO
					AddInfrastrukturDTO addInfrastrukturDTO = new AddInfrastrukturDTO();
					addInfrastrukturDTO.setJenis(jenis);
					addInfrastrukturDTO.setNama(jenis + " " + pasar.getNama());
					addInfrastrukturDTO.setPenanggungJawab(faker.name().fullName());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date fromDate = sdf.parse("2023-01-01");
					Date toDate = sdf.parse("2023-12-31");
					addInfrastrukturDTO.setTanggalPembangunan(faker.date().between(fromDate, toDate));
					addInfrastrukturDTO.setTanggalTerakhirKaliPengecekan(faker.date().between(fromDate, toDate));
					addInfrastrukturDTO.setIdPasar(pasar.getId());

					// Simpan infrastruktur
					Infrastruktur infrastruktur =  infrastrukturService.create(addInfrastrukturDTO);

					// Tambahkan beberapa jadwal pengecekan infrastruktur
					for (int g = 0; g < faker.number().numberBetween(1, 3); g++) { // Misal 1-3 jadwal pengecekan
						AddPengecekanInfrastrukturDTO pengecekanDTO = new AddPengecekanInfrastrukturDTO();
						pengecekanDTO.setInfrastrukturID(infrastruktur.getId()); // Ambil ID infrastruktur
						pengecekanDTO.setTanggal(faker.date().between(fromDate, toDate)); // Tanggal acak
						pengecekanDTO.setBiaya(faker.number().numberBetween(1000L, 5000L)); // Biaya acak
						pengecekanDTO.setPelakuPengecekan(faker.name().fullName()); // Nama pelaku pengecekan
						var cek =  infrastrukturService.addPengecekan(pengecekanDTO);
						log.info("KONTOL");
						log.info(cek.getInfrastruktur().getId().toString());
					}

					// Tambahkan beberapa jadwal maintenance infrastruktur
					for (int n = 0; n < faker.number().numberBetween(1, 3); n++) { // Misal 1-3 jadwal maintenance
						AddMaintenanceInfrastrukturDTO maintenanceDTO = new AddMaintenanceInfrastrukturDTO();
						maintenanceDTO.setInfrastrukturID(infrastruktur.getId()); // Ambil ID infrastruktur
						maintenanceDTO.setTanggal(faker.date().between(fromDate, toDate)); // Tanggal acak
						maintenanceDTO.setDeskripsi(faker.lorem().sentence()); // Deskripsi acak
						maintenanceDTO.setPelakuMaintenance(faker.name().fullName()); // Nama pelaku maintenance
						maintenanceDTO.setBiaya(faker.number().numberBetween(2000L, 10000L)); // Biaya acak
						// Simpan jadwal maintenance
						infrastrukturService.addMaintenance(maintenanceDTO);
					}
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




//	@Bean
//	@Transactional
//	CommandLineRunner run(BarangPokokService barangPokokService, TokoService tokoService) {
//		return args -> {
//			for (int i = 0; i < 100; i++) {
//
//				var faker = new Faker(new Locale("in-ID"));
//
//				AddTokoDTO addTokoDTO = new AddTokoDTO();
//
//				addTokoDTO.setNamaToko("Toko "+faker.name());
//				String namaKota = faker.address().cityName();
//				String namaJalan = faker.address().streetName();
//				addTokoDTO.setAlamatToko(namaJalan+" "+namaKota);
//
//				String generatedNik = generateNik(faker);
//				addTokoDTO.setNikPenjual(generatedNik);
//				addTokoDTO.setNamaPenjual(faker.name().fullName());
//
//				String generatedPhoneNumber = generatePhoneNumber(faker);
//				addTokoDTO.setKontakPenjual(generatedPhoneNumber);
//
//				// Create the Toko and persist it using the TokoService
//				Toko toko = tokoService.createToko(addTokoDTO);
//
//				// Generate a list of BarangPokok IDs to associate with this Toko
//				// Generate a list of BarangPokok IDs to associate with this Toko
//				List<UUID> listIdBarangPokok = new ArrayList<>();
//				List<String> jenisBpList = barangPokokService.getJenisBp();  // Fetch the list of JenisBarang strings
//
//				Random random = new Random();
//
//				for (int j = 0; j < 5; j++) {  // Assuming each Toko has 5 linked BarangPokok items
//					AddBarangPokokDTO addBarangPokokDTO = new AddBarangPokokDTO();
//					addBarangPokokDTO.setNama(faker.commerce().productName());
//					addBarangPokokDTO.setStok(faker.number().numberBetween(10, 100));
//					addBarangPokokDTO.setTotalPenjual(faker.number().numberBetween(1, 10));
//					addBarangPokokDTO.setTanggalKadaluwarsa(faker.date().future(365, TimeUnit.DAYS));
//
//
//					String randomJenisBarang = jenisBpList.get(random.nextInt(jenisBpList.size()));
//					addBarangPokokDTO.setIdJenisBarang(randomJenisBarang);
//
//					// Create and save the BarangPokok, then add its ID to the list
//					BarangPokok barangPokok = barangPokokService.create(addBarangPokokDTO);
//					listIdBarangPokok.add(barangPokok.getId());
//
//				}
//				addTokoDTO.setListIdBarangPokok(listIdBarangPokok);
//			}
//		};
//	}

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
